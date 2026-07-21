package com.smartpods.api.dto;

import java.time.LocalDateTime;

public class PedidoResponseDTO {
    private Long id;
    private String numeroPedido;
    private String producto;
    private String estado;
    private String lockerCodigo;
    private String tamano;
    private String qrData;
    private String nombreUsuario;
    private LocalDateTime fechaCreacion;

    public PedidoResponseDTO(Long id, String numeroPedido, String producto, String estado,
                              String lockerCodigo, String tamano, String qrData, String nombreUsuario,
                              LocalDateTime fechaCreacion) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.producto = producto;
        this.estado = estado;
        this.lockerCodigo = lockerCodigo;
        this.tamano = tamano;
        this.qrData = qrData;
        this.nombreUsuario = nombreUsuario;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() { return id; }
    public String getNumeroPedido() { return numeroPedido; }
    public String getProducto() { return producto; }
    public String getEstado() { return estado; }
    public String getLockerCodigo() { return lockerCodigo; }
    public String getTamano() { return tamano; }
    public String getQrData() { return qrData; }
    public String getNombreUsuario() { return nombreUsuario; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
}