package com.smartpods.api.controller;

import com.smartpods.api.dto.DashboardDTO;
import com.smartpods.api.dto.IncidenciaResponseDTO;
import com.smartpods.api.dto.PedidoResponseDTO;
import com.smartpods.api.dto.ResolverIncidenciaDTO;
import com.smartpods.api.service.PedidoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final PedidoService pedidoService;

    @PersistenceContext
    private EntityManager entityManager;

    public AdminController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/dashboard")
    public DashboardDTO dashboard() {
        return pedidoService.obtenerDashboard();
    }

    @GetMapping("/incidencias")
    public List<IncidenciaResponseDTO> incidencias() {
        return pedidoService.listarIncidencias();
    }

    @GetMapping("/pedidos-todos")
    public List<PedidoResponseDTO> pedidosTodos() {
        return pedidoService.listarTodos();
    }

    @PostMapping("/incidencias/{id}/resolver")
    public ResponseEntity<?> resolverIncidencia(@PathVariable Long id, @RequestBody ResolverIncidenciaDTO dto) {
        try {
            String mensaje = pedidoService.resolverIncidencia(id, dto.isAprobar());
            return ResponseEntity.ok(mensaje);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/fix-constraint")
    @Transactional
    public String fixConstraint() {
        entityManager.createNativeQuery(
                "ALTER TABLE pedidos DROP CONSTRAINT IF EXISTS pedidos_estado_check"
        ).executeUpdate();
        return "Restricción eliminada correctamente";
    }
}