package com.smartpods.api.repository;

import com.smartpods.api.entity.EstadoPedido;
import com.smartpods.api.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuarioId(Long usuarioId);

    List<Pedido> findByEstadoNot(EstadoPedido estado);

    Optional<Pedido> findByQrData(String qrData);

    List<Pedido> findByEstado(EstadoPedido estado);

    long countByEstadoAndFechaCreacionAfter(EstadoPedido estado, LocalDateTime fecha);
}