package com.upiiz.examen1.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query("SELECT u FROM UsuarioEntity u WHERE u.nombre LIKE %:texto% OR u.apellidoPaterno LIKE %:texto% OR u.apellidoMaterno LIKE %:texto% OR u.usuario LIKE %:texto%")
    List<UsuarioEntity> buscarParcial(@Param("texto") String texto);

    boolean existsByCorreo(String correo);
    boolean existsByUsuario(String usuario);
}