package com.smartpods.api.dto;

public class LoginResponseDTO {

    private String token;
    private String nombre;
    private String rol;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String nombre, String rol) {
        this.token = token;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }
}