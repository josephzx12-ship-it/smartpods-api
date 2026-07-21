package com.smartpods.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroPedido;

    @Column(nullable = false)
    private String producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "locker_id", nullable = false)
    private Locker locker;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado;

    @Column(name = "qr_data")
    private String qrData;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "motivo_devolucion")
    private String motivoDevolucion; // "DEVOLUCION" o "CAMBIO"

    @Column(name = "evidencia_foto", columnDefinition = "TEXT")
    private String evidenciaFoto; // imagen en base64

    @Column(name = "resultado_simulado")
    private String resultadoSimulado; // "VERDE", "AMARILLO" o "ROJO"

    @Column(name = "evidencia_pod", columnDefinition = "TEXT")
    private String evidenciaPod;

    public String getEvidenciaPod() { return evidenciaPod; }
    public void setEvidenciaPod(String evidenciaPod) { this.evidenciaPod = evidenciaPod; }

    public Pedido() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroPedido() { return numeroPedido; }
    public void setNumeroPedido(String numeroPedido) { this.numeroPedido = numeroPedido; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Locker getLocker() { return locker; }
    public void setLocker(Locker locker) { this.locker = locker; }

    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }

    public String getQrData() { return qrData; }
    public void setQrData(String qrData) { this.qrData = qrData; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getMotivoDevolucion() { return motivoDevolucion; }
    public void setMotivoDevolucion(String motivoDevolucion) { this.motivoDevolucion = motivoDevolucion; }

    public String getEvidenciaFoto() { return evidenciaFoto; }
    public void setEvidenciaFoto(String evidenciaFoto) { this.evidenciaFoto = evidenciaFoto; }

    public String getResultadoSimulado() { return resultadoSimulado; }
    public void setResultadoSimulado(String resultadoSimulado) { this.resultadoSimulado = resultadoSimulado; }
}