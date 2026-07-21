package com.smartpods.api.dto;

public class DevolucionResponseDTO {
    private boolean aprobadoAutomaticamente;
    private String resultado;
    private String estado;
    private String mensaje;

    public DevolucionResponseDTO(boolean aprobadoAutomaticamente, String resultado, String estado, String mensaje) {
        this.aprobadoAutomaticamente = aprobadoAutomaticamente;
        this.resultado = resultado;
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public boolean isAprobadoAutomaticamente() { return aprobadoAutomaticamente; }
    public String getResultado() { return resultado; }
    public String getEstado() { return estado; }
    public String getMensaje() { return mensaje; }
}