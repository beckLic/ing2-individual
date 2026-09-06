package org.example.dto;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VigenciaDTO {
    private String id;
    private String productoId;
    private String productoNombre; // Dato extraído para facilitar el renderizado en Thymeleaf
    private double precio;
    private Date desde;
    private Date hasta;
}
