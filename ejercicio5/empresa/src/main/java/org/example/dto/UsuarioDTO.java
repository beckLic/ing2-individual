package org.example.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private String id;
    private String username;
    private String nombre;
    // nnunca se incluye el password en el DTO de salida hacia la vista.
}
