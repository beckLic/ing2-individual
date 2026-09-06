package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.enums.Sexo;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ProfesorDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Debe ser un correo válido")
    private String mail; // Viaja al Usuario, no al Profesor

    @NotNull(message = "El DNI es obligatorio")
    private Integer dni;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotNull(message = "El sexo es obligatorio")
    private Sexo sexo;

    // Esta anotación es CLAVE para que Spring entienda el <input type="date"> de HTML
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
}