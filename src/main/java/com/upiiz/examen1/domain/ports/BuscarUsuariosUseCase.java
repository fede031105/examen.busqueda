package com.upiiz.examen1.domain.ports;

import com.upiiz.examen1.domain.model.Usuario;
import java.util.List;

public interface BuscarUsuariosUseCase {
    List<Usuario> buscar(String texto);
}
//perr