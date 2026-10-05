package com.upiiz.examen1.adapters.out.persistence;

import com.upiiz.examen1.domain.model.Usuario;
import com.upiiz.examen1.domain.ports.UsuarioRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository jpaRepository;

    public UsuarioPersistenceAdapter(UsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre(usuario.getNombre());
        entity.setApellidoPaterno(usuario.getApellidoPaterno());
        entity.setApellidoMaterno(usuario.getApellidoMaterno());
        entity.setCorreo(usuario.getCorreo());
        entity.setUsuario(usuario.getUsuario());
        entity.setPassword(usuario.getPassword());
        entity.setFechaNacimiento(usuario.getFechaNacimiento());

        UsuarioEntity guardado = jpaRepository.save(entity);
        usuario.setId(guardado.getId());
        return usuario;
    }

    @Override
    public List<Usuario> buscarPorTexto(String texto) {
        return jpaRepository.buscarParcial(texto).stream()
                .map(this::mapearAModelo)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return jpaRepository.existsByCorreo(correo);
    }

    @Override
    public boolean existePorUsuario(String usuario) {
        return jpaRepository.existsByUsuario(usuario);
    }

    private Usuario mapearAModelo(UsuarioEntity entity) {
        Usuario u = new Usuario();
        u.setId(entity.getId());
        u.setNombre(entity.getNombre());
        u.setApellidoPaterno(entity.getApellidoPaterno());
        u.setApellidoMaterno(entity.getApellidoMaterno());
        u.setCorreo(entity.getCorreo());
        u.setUsuario(entity.getUsuario());
        u.setPassword(entity.getPassword());
        u.setFechaNacimiento(entity.getFechaNacimiento());
        return u;
    }
}