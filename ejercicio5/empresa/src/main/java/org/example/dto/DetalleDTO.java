package org.example.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleDTO {
    private String id;
    private String productoId;
    private String productoNombre;
    private int cantidad;
    private double subtotal;
    private boolean eliminado;
}
