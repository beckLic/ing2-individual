package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.enums.Grado;

@Data
public class MateriaDTO {
    private Long id;

    @NotBlank(message = "El nombre de la materia es obligatorio")
    private String nombre;

    @NotNull(message = "Debe seleccionar un grado")
    private Grado grado;

    @NotNull(message = "Debe asignar un profesor a la materia")
    private Long profesorId;

    @NotNull(message = "Debe asignar un aula a la materia")
    private Long aulaId;
}