package com.smartpods.api.controller;

import com.smartpods.api.dto.EscaneoRequestDTO;
import com.smartpods.api.dto.EscaneoResponseDTO;
import com.smartpods.api.service.PedidoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulador")
@CrossOrigin(origins = "*")
public class SimuladorController {

    private final PedidoService pedidoService;

    public SimuladorController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/escanear")
    public EscaneoResponseDTO escanear(@RequestBody EscaneoRequestDTO dto) {
        return pedidoService.procesarEscaneo(dto.getQrData());
    }
}