package com.smartpods.api.controller;

import com.smartpods.api.dto.DashboardDTO;
import com.smartpods.api.service.PedidoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final PedidoService pedidoService;

    public AdminController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/dashboard")
    public DashboardDTO dashboard() {
        return pedidoService.obtenerDashboard();
    }
}