package com.smartpods.api.dto;

import com.smartpods.api.entity.TamanoLocker;

public class PedidoCrearDTO {
    private Long usuarioId;
    private String producto;
    private TamanoLocker tamano;
    private String categoria;

    public PedidoCrearDTO() {}

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public TamanoLocker getTamano() { return tamano; }
    public void setTamano(TamanoLocker tamano) { this.tamano = tamano; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}