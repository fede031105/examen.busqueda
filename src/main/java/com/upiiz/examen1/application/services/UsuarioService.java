package com.upiiz.examen1.application.services;

import com.upiiz.examen1.domain.model.Usuario;
import com.upiiz.examen1.domain.ports.BuscarUsuariosUseCase;
import com.upiiz.examen1.domain.ports.RegistrarUsuarioUseCase;
import com.upiiz.examen1.domain.ports.UsuarioRepositoryPort;
import java.util.List;

public class UsuarioService implements BuscarUsuariosUseCase, RegistrarUsuarioUseCase {
    private final UsuarioRepositoryPort repositoryPort;

    public UsuarioService(UsuarioRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public void registrar(Usuario usuario) {
        if (repositoryPort.existePorCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }
        if (repositoryPort.existePorUsuario(usuario.getUsuario())) {
            throw new RuntimeException("El nombre de usuario ya está registrado.");
        }
        repositoryPort.guardar(usuario);
    }

    @Override
    public List<Usuario> buscar(String texto) {
        if (texto == null || texto.length() < 3) {
            throw new IllegalArgumentException("La búsqueda debe contener al menos 3 caracteres.");
        }
        return repositoryPort.buscarPorTexto(texto);
    }
}