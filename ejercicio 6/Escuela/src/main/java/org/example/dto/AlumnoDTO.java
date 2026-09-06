package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.enums.Grado;

@Data
public class AlumnoDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El mail es obligatorio")
    private String mail;

    @NotNull(message = "El DNI es obligatorio")
    private Integer dni;

    @NotNull(message = "El legajo es obligatorio")
    private Integer legajo;

    @NotNull(message = "Debe seleccionar un grado")
    private Grado grado;
}