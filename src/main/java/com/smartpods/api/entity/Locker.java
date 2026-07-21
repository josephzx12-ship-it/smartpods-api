package com.smartpods.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lockers")
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TamanoLocker tamano;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoLocker estado;

    public Locker() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public TamanoLocker getTamano() { return tamano; }
    public void setTamano(TamanoLocker tamano) { this.tamano = tamano; }

    public EstadoLocker getEstado() { return estado; }
    public void setEstado(EstadoLocker estado) { this.estado = estado; }
}