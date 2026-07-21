package com.smartpods.api.dto;

public class ResolverIncidenciaDTO {
    private String decision; // "APROBAR", "RECHAZAR" o "TIENDA"

    public ResolverIncidenciaDTO() {}

    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }
}