package org.example.mapper;

import org.example.dto.UsuarioDTO;
import org.example.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    // Convierte de Entidad a DTO para enviar a la vista
    public UsuarioDTO toDTO(Usuario entity) {
        if (entity == null) return null;
        return UsuarioDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .nombre(entity.getNombre())
                .build();
    }
}