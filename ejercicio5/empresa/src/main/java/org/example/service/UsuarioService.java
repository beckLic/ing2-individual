package org.example.service;

import org.example.dto.UsuarioDTO;
import org.example.dto.UsuarioLoginDTO;

public interface UsuarioService {
    UsuarioDTO login(UsuarioLoginDTO loginDto);
}
