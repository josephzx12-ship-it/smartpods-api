package com.smartpods.api.service;

import com.smartpods.api.dto.*;
import com.smartpods.api.entity.*;
import com.smartpods.api.repository.LockerRepository;
import com.smartpods.api.repository.PedidoRepository;
import com.smartpods.api.repository.UsuarioRepository;
import com.smartpods.api.util.QrCodeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final LockerRepository lockerRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                          LockerRepository lockerRepository,
                          UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.lockerRepository = lockerRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public PedidoResponseDTO crearPedido(PedidoCrearDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Locker locker = lockerRepository.findFirstByEstadoAndTamano(EstadoLocker.DISPONIBLE, dto.getTamano())
                .orElseThrow(() -> new RuntimeException("No hay lockers disponibles de tamaño " + dto.getTamano()));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setProducto(dto.getProducto());
        pedido.setLocker(locker);
        pedido.setEstado(EstadoPedido.PENDIENTE);
        pedido.setFechaCreacion(LocalDateTime.now());

        pedido = pedidoRepository.save(pedido);

        String numeroPedido = "PED-" + String.format("%05d", pedido.getId());
        String qrData = numeroPedido + "-POD-" + locker.getCodigo() + "-UNLOCK";

        pedido.setNumeroPedido(numeroPedido);
        pedido.setQrData(qrData);
        pedido.setEstado(EstadoPedido.ASIGNADO);

        locker.setEstado(EstadoLocker.OCUPADO);
        lockerRepository.save(locker);

        pedido = pedidoRepository.save(pedido);

        return convertirADTO(pedido);
    }

    public List<PedidoResponseDTO> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId)
                .stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public List<PedidoResponseDTO> listarActivos() {
        return pedidoRepository.findByEstadoNot(EstadoPedido.RETIRADO)
                .stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public byte[] generarImagenQr(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        try {
            return QrCodeUtil.generarQR(pedido.getQrData(), 300, 300);
        } catch (Exception e) {
            throw new RuntimeException("Error generando el QR", e);
        }
    }

    public EscaneoResponseDTO procesarEscaneo(String qrData) {

        Optional<Pedido> pedidoOpt = pedidoRepository.findByQrData(qrData);

        if (pedidoOpt.isEmpty()) {
            return new EscaneoResponseDTO(false, "QR inválido o no reconocido", null);
        }

        Pedido pedido = pedidoOpt.get();

        if (pedido.getEstado() == EstadoPedido.RETIRADO) {
            return new EscaneoResponseDTO(false, "Este pedido ya fue retirado", pedido.getLocker().getCodigo());
        }

        pedido.setEstado(EstadoPedido.RETIRADO);
        pedidoRepository.save(pedido);

        Locker locker = pedido.getLocker();
        locker.setEstado(EstadoLocker.DISPONIBLE);
        lockerRepository.save(locker);

        return new EscaneoResponseDTO(true, "Acceso concedido", locker.getCodigo());
    }

    public EscaneoResponseDTO cancelarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (pedido.getEstado() == EstadoPedido.RETIRADO) {
            return new EscaneoResponseDTO(false, "Este pedido ya fue retirado, no se puede cancelar", null);
        }
        if (pedido.getEstado() == EstadoPedido.CANCELADO) {
            return new EscaneoResponseDTO(false, "Este pedido ya estaba cancelado", null);
        }

        pedido.setEstado(EstadoPedido.CANCELADO);
        pedidoRepository.save(pedido);

        Locker locker = pedido.getLocker();
        locker.setEstado(EstadoLocker.DISPONIBLE);
        lockerRepository.save(locker);

        return new EscaneoResponseDTO(true, "Pedido cancelado, locker liberado", locker.getCodigo());
    }

    public DevolucionResponseDTO solicitarDevolucion(Long pedidoId, SolicitarDevolucionDTO dto) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (pedido.getEstado() != EstadoPedido.RETIRADO) {
            throw new RuntimeException("Solo se puede solicitar devolución de pedidos ya retirados");
        }

        double azar = Math.random();
        String resultado;
        if (azar < 0.70) {
            resultado = "VERDE";
        } else if (azar < 0.90) {
            resultado = "AMARILLO";
        } else {
            resultado = "ROJO";
        }

        pedido.setMotivoDevolucion(dto.getMotivo());
        pedido.setEvidenciaFoto(dto.getFotoBase64());
        pedido.setResultadoSimulado(resultado);
        pedido.setEvidenciaPod(generarFotoPodSimulada(resultado));

        boolean aprobadoAuto = resultado.equals("VERDE");
        pedido.setEstado(aprobadoAuto ? EstadoPedido.DEVOLUCION_APROBADA : EstadoPedido.EN_DEVOLUCION);
        pedidoRepository.save(pedido);

        String mensaje = aprobadoAuto
                ? "Producto validado correctamente. Tu reembolso/cambio fue aprobado automáticamente."
                : "Tu producto quedó en control de calidad. Un operador revisará tu solicitud.";

        return new DevolucionResponseDTO(aprobadoAuto, resultado, pedido.getEstado().name(), mensaje);
    }

    public List<IncidenciaResponseDTO> listarIncidencias() {
        return pedidoRepository.findByEstado(EstadoPedido.EN_DEVOLUCION)
                .stream()
                .map(p -> new IncidenciaResponseDTO(
                        p.getId(), p.getNumeroPedido(), p.getUsuario().getNombre(), p.getProducto(),
                        p.getMotivoDevolucion(), p.getEvidenciaFoto(), p.getEvidenciaPod(),
                        p.getResultadoSimulado(), p.getEstado().name()
                ))
                .collect(Collectors.toList());
    }

    public String resolverIncidencia(Long pedidoId, boolean aprobar) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (pedido.getEstado() != EstadoPedido.EN_DEVOLUCION) {
            throw new RuntimeException("Este pedido no tiene una incidencia pendiente");
        }

        pedido.setEstado(aprobar ? EstadoPedido.DEVOLUCION_APROBADA : EstadoPedido.DEVOLUCION_RECHAZADA);
        pedidoRepository.save(pedido);

        return aprobar ? "Devolución aprobada manualmente" : "Devolución rechazada";
    }

    public DashboardDTO obtenerDashboard() {
        long totalLockers = lockerRepository.count();
        long ocupados = lockerRepository.findByEstado(EstadoLocker.OCUPADO).size();
        long retirosHoy = pedidoRepository.countByEstadoAndFechaCreacionAfter(
                EstadoPedido.RETIRADO, LocalDateTime.now().toLocalDate().atStartOfDay());

        double porcentaje = totalLockers == 0 ? 0 : (ocupados * 100.0) / totalLockers;

        return new DashboardDTO(totalLockers, ocupados, porcentaje, retirosHoy, listarActivos());
    }

    private PedidoResponseDTO convertirADTO(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getNumeroPedido(),
                pedido.getProducto(),
                pedido.getEstado().name(),
                pedido.getLocker().getCodigo(),
                pedido.getLocker().getTamano().name(),
                pedido.getQrData(),
                pedido.getUsuario().getNombre(),
                pedido.getFechaCreacion()
        );
    }

    private String generarFotoPodSimulada(String resultado) {
        String color = resultado.equals("VERDE") ? "#10b981" : resultado.equals("AMARILLO") ? "#f59e0b" : "#ef4444";
        String texto = resultado.equals("VERDE") ? "PESO OK - SIN ANOMALIAS" : resultado.equals("AMARILLO") ? "DISCREPANCIA DE PESO" : "ETIQUETA NO DETECTADA";
        String svg = "<svg xmlns='http://www.w3.org/2000/svg' width='300' height='200'>" +
                "<rect width='300' height='200' fill='#1a1d23'/>" +
                "<rect x='10' y='10' width='280' height='180' fill='none' stroke='" + color + "' stroke-width='2'/>" +
                "<circle cx='270' cy='25' r='4' fill='" + color + "'/>" +
                "<text x='150' y='95' fill='white' font-family='monospace' font-size='11' text-anchor='middle'>CAMARA INTERNA - SMART POD</text>" +
                "<text x='150' y='115' fill='" + color + "' font-family='monospace' font-size='10' text-anchor='middle'>" + texto + "</text>" +
                "</svg>";
        return "data:image/svg+xml;base64," + java.util.Base64.getEncoder().encodeToString(svg.getBytes());
    }
}