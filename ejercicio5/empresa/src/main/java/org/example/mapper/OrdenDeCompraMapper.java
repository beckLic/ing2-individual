package org.example.mapper;

import org.example.dto.DetalleDTO;
import org.example.dto.OrdenDeCompraDTO;
import org.example.entity.OrdenDeCompra;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrdenDeCompraMapper {

    // Convierte la entidad a DTO extrayendo datos de relaciones (Proveedor y Detalles)
    public OrdenDeCompraDTO toDTO(OrdenDeCompra entity) {
        if (entity == null) return null;

        return OrdenDeCompraDTO.builder()
                .id(entity.getId())
                .fecha(entity.getFecha())
                .totalPagado(entity.getTotalPagado())
                .eliminado(entity.isEliminado())
                // Aplanamos al proveedor (Lazy Loading resuelto aquí)
                .proveedorCuit(entity.getProveedor() != null ? entity.getProveedor().getCuit() : null)
                .proveedorRazonSocial(entity.getProveedor() != null ? entity.getProveedor().getRazonSocial() : null)
                // Mapeamos la colección de detalles
                .detalles(entity.getDetalles() != null ? entity.getDetalles().stream()
                        .map(d -> DetalleDTO.builder()
                                .id(d.getId())
                                .productoId(d.getProducto() != null ? d.getProducto().getId() : null)
                                .productoNombre(d.getProducto() != null ? d.getProducto().getNombre() : null)
                                .cantidad(d.getCantidad())
                                .subtotal(d.getSubtotal())
                                .eliminado(d.isEliminado())
                                .build())
                        .collect(Collectors.toList()) : null)
                .build();
    }
}
