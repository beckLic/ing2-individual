package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import org.example.enums.Sexo;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

// DTO para registrar Profesores
@Data
public class ProfesorRegistroDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    @NotNull(message = "El DNI es obligatorio")
    private Integer dni;
    @NotBlank(message = "El título es obligatorio")
    private String titulo;
    @NotNull(message = "El sexo es obligatorio")
    private Sexo sexo;
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Email(message = "Debe ser un mail válido")
    private String email;
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
}
