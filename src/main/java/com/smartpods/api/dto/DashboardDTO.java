package com.smartpods.api.dto;

import java.util.List;

public class DashboardDTO {
    private long totalLockers;
    private long lockersOcupados;
    private double porcentajeCapacidad;
    private long retirosHoy;
    private List<PedidoResponseDTO> pedidosActivos;

    public DashboardDTO(long totalLockers, long lockersOcupados, double porcentajeCapacidad,
                         long retirosHoy, List<PedidoResponseDTO> pedidosActivos) {
        this.totalLockers = totalLockers;
        this.lockersOcupados = lockersOcupados;
        this.porcentajeCapacidad = porcentajeCapacidad;
        this.retirosHoy = retirosHoy;
        this.pedidosActivos = pedidosActivos;
    }

    public long getTotalLockers() { return totalLockers; }
    public long getLockersOcupados() { return lockersOcupados; }
    public double getPorcentajeCapacidad() { return porcentajeCapacidad; }
    public long getRetirosHoy() { return retirosHoy; }
    public List<PedidoResponseDTO> getPedidosActivos() { return pedidosActivos; }
}