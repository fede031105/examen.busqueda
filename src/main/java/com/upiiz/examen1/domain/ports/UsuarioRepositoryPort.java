package com.upiiz.examen1.domain.ports;

import com.upiiz.examen1.domain.model.Usuario;
import java.util.List;

public interface UsuarioRepositoryPort {
    Usuario guardar(Usuario usuario);
    List<Usuario> buscarPorTexto(String texto);
    boolean existePorCorreo(String correo);
    boolean existePorUsuario(String usuario);
}