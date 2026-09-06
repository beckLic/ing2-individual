package org.example.mapper;

import org.example.dto.PromocionDTO;
import org.example.entity.Promocion;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PromocionMapper {

    // Capa: Mapper
    // Extraemos solo el nombre de los productos involucrados para evitar traer la entidad Producto completa.
    public PromocionDTO toDTO(Promocion entity) {
        if (entity == null) return null;

        return PromocionDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .porcentajeDescuento(entity.getPorcentajeDescuento())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .activo(entity.getActivo())
                .productosInvolucrados(entity.getProductos() != null ?
                        entity.getProductos().stream().map(p -> p.getNombre()).collect(Collectors.toList()) : null)
                .build();
    }
}
