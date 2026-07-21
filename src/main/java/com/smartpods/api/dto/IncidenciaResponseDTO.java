package com.smartpods.api.dto;

public class IncidenciaResponseDTO {
    private Long id;
    private String numeroPedido;
    private String nombreUsuario;
    private String producto;
    private String motivoDevolucion;
    private String evidenciaFoto;
    private String evidenciaPod;
    private String resultadoSimulado;
    private String estado;

    public IncidenciaResponseDTO(Long id, String numeroPedido, String nombreUsuario, String producto,
                                  String motivoDevolucion, String evidenciaFoto, String evidenciaPod,
                                  String resultadoSimulado, String estado) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.nombreUsuario = nombreUsuario;
        this.producto = producto;
        this.motivoDevolucion = motivoDevolucion;
        this.evidenciaFoto = evidenciaFoto;
        this.evidenciaPod = evidenciaPod;
        this.resultadoSimulado = resultadoSimulado;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public String getNumeroPedido() { return numeroPedido; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getProducto() { return producto; }
    public String getMotivoDevolucion() { return motivoDevolucion; }
    public String getEvidenciaFoto() { return evidenciaFoto; }
    public String getEvidenciaPod() { return evidenciaPod; }
    public String getResultadoSimulado() { return resultadoSimulado; }
    public String getEstado() { return estado; }
}