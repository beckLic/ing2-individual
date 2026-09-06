package org.example.mapper;


import org.example.dto.VigenciaDTO;
import org.example.entity.Vigencia;
import org.springframework.stereotype.Component;

@Component
public class VigenciaMapper {

    // Capa: Mapper
    // Aplanamos la relación con Producto, guardando solo el ID y el Nombre para la vista.
    public VigenciaDTO toDTO(Vigencia entity) {
        if (entity == null) return null;

        return VigenciaDTO.builder()
                .id(entity.getId())
                .productoId(entity.getProd() != null ? entity.getProd().getId() : null)
                .productoNombre(entity.getProd() != null ? entity.getProd().getNombre() : null)
                .precio(entity.getPrecio())
                .desde(entity.getDesde())
                .hasta(entity.getHasta())
                .build();
    }
}
