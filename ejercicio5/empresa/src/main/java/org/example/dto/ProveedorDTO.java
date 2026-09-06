package org.example.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorDTO {
    private Long cuit;
    private String razonSocial;
    // Lista plana para la vista, rompe la dependencia circular con Producto
    private List<String> nombresProductos;
}