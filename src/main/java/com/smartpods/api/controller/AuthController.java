package com.smartpods.api.controller;

import com.smartpods.api.dto.LoginDTO;
import com.smartpods.api.dto.LoginResponseDTO;
import com.smartpods.api.entity.Usuario;
import com.smartpods.api.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        Usuario usuario = usuarioService.login(
                loginDTO.getCorreo(),
                loginDTO.getPassword());

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Correo o contraseña incorrectos");
        }

        LoginResponseDTO response = new LoginResponseDTO(
                usuario.getId(),
                "DEMO-TOKEN-123456",
                usuario.getNombre(),
                usuario.getRol().name()
        );

        return ResponseEntity.ok(response);
    }

}