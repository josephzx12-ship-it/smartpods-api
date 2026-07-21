package com.smartpods.api.dto;

import com.smartpods.api.entity.TamanoLocker;

public class PedidoCrearDTO {
    private Long usuarioId;
    private String producto;
    private TamanoLocker tamano;

    public PedidoCrearDTO() {}

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public TamanoLocker getTamano() { return tamano; }
    public void setTamano(TamanoLocker tamano) { this.tamano = tamano; }
}