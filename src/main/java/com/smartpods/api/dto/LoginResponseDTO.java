package com.smartpods.api.dto;

public class LoginResponseDTO {

    private Long id;
    private String token;
    private String nombre;
    private String rol;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(Long id, String token, String nombre, String rol) {
        this.id = id;
        this.token = token;
        this.nombre = nombre;
        this.rol = rol;
    }

    public Long getId() { return id; }
    public String getToken() { return token; }
    public String getNombre() { return nombre; }
    public String getRol() { return rol; }
}