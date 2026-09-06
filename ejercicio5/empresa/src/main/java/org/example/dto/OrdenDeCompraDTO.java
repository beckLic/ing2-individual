package org.example.dto;

import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenDeCompraDTO {
    private String id;
    private Date fecha;
    private double totalPagado;
    private boolean eliminado;

    // Aplanamos datos del Proveedor para no enviar el objeto entero
    private Long proveedorCuit;
    private String proveedorRazonSocial;

    private List<DetalleDTO> detalles;
}