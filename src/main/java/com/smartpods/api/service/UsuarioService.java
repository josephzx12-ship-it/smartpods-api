package com.smartpods.api.service;

import com.smartpods.api.entity.Usuario;
import com.smartpods.api.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            BCryptPasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrar(Usuario usuario) {

        usuario.setPassword(
                passwordEncoder.encode(usuario.getPassword())
        );

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario login(String correo, String password) {

    Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);

    if (usuarioOpt.isEmpty()) {
        return null;
    }

    Usuario usuario = usuarioOpt.get();

    if (!passwordEncoder.matches(password, usuario.getPassword())) {
        return null;
    }

    return usuario;
    }
}