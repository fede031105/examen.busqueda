package com.upiiz.examen1.adapters.in.web;

import com.upiiz.examen1.application.services.UsuarioService;
import com.upiiz.examen1.domain.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody UsuarioRegistroDTO dto) {
        try {
            Usuario u = new Usuario();
            u.setNombre(dto.getNombre());
            u.setApellidoPaterno(dto.getApellidoPaterno());
            u.setApellidoMaterno(dto.getApellidoMaterno());
            u.setCorreo(dto.getCorreo());
            u.setUsuario(dto.getUsuario());
            u.setPassword(dto.getPassword());
            u.setFechaNacimiento(dto.getFechaNacimiento());

            usuarioService.registrar(u);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(@RequestParam String texto) {
        try {
            List<Usuario> usuarios = usuarioService.buscar(texto);
            List<UsuarioResponseDTO> respuesta = usuarios.stream()
                    .map(u -> new UsuarioResponseDTO(u.getId(), u.getNombre(), u.getApellidoPaterno(), u.getApellidoMaterno(), u.getUsuario()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}