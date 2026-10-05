package com.upiiz.examen1.config;

import com.upiiz.examen1.application.services.UsuarioService;
import com.upiiz.examen1.domain.ports.UsuarioRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DominioConfig {

    @Bean
    public UsuarioService usuarioService(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new UsuarioService(usuarioRepositoryPort);
    }
}