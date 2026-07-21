package com.smartpods.api.dto;

public class EscaneoResponseDTO {
    private boolean exito;
    private String mensaje;
    private String lockerCodigo;

    public EscaneoResponseDTO(boolean exito, String mensaje, String lockerCodigo) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.lockerCodigo = lockerCodigo;
    }

    public boolean isExito() { return exito; }
    public String getMensaje() { return mensaje; }
    public String getLockerCodigo() { return lockerCodigo; }
}