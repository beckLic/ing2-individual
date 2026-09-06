package org.example.mapper;

import org.example.dto.ProveedorDTO;
import org.example.entity.Proveedor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProveedorMapper {

    // Capa: Mapper
    // Rompe el ciclo bidireccional Proveedor <-> Producto.
    // Mapea la lista de entidades Producto a una simple lista de Strings (nombres).
    public ProveedorDTO toDTO(Proveedor entity) {
        if (entity == null) return null;

        return ProveedorDTO.builder()
                .cuit(entity.getCuit())
                .razonSocial(entity.getRazonSocial())
                .nombresProductos(entity.getProductos() != null ?
                        entity.getProductos().stream().map(p -> p.getNombre()).collect(Collectors.toList()) : null)
                .build();
    }
}