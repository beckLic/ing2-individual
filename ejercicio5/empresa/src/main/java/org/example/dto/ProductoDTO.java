package org.example.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ProductoDTO {
    private String id;
    private String nombre;
    private String marca;
    private boolean eliminado;

    // Para mostrar en la vista en qué promociones/proveedores está,
    // sin traer las entidades completas.
    private List<String> promocionesNombres;
}