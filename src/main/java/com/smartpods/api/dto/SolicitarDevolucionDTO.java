package com.smartpods.api.dto;

public class SolicitarDevolucionDTO {
    private String motivo; // "DEVOLUCION" o "CAMBIO"
    private String fotoBase64;

    public SolicitarDevolucionDTO() {}

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getFotoBase64() { return fotoBase64; }
    public void setFotoBase64(String fotoBase64) { this.fotoBase64 = fotoBase64; }
}