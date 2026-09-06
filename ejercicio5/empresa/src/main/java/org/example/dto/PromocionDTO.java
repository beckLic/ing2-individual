package org.example.dto;

import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromocionDTO {
    private String id;
    private String nombre;
    private double porcentajeDescuento;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean activo;

    // Evitamos el ciclo referencial guardando solo nombres o IDs
    private List<String> productosInvolucrados;
}