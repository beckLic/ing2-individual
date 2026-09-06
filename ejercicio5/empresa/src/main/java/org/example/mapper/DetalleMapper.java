package org.example.mapper;

import org.example.dto.DetalleDTO;
import org.example.entity.Detalle;
import org.springframework.stereotype.Component;

@Component
public class DetalleMapper {

    // Capa: Mapper
    // Aunque OrdenDeCompraMapper ya extraía detalles internamente,
    // es buena práctica tener el mapper independiente por si listamos detalles sueltos en alguna vista.
    public DetalleDTO toDTO(Detalle entity) {
        if (entity == null) return null;

        return DetalleDTO.builder()
                .id(entity.getId())
                .productoId(entity.getProducto() != null ? entity.getProducto().getId() : null)
                .productoNombre(entity.getProducto() != null ? entity.getProducto().getNombre() : null)
                .cantidad(entity.getCantidad())
                .subtotal(entity.getSubtotal())
                .eliminado(entity.isEliminado())
                .build();
    }
}
