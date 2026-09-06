package org.example.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.enums.Sexo;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Audited
public class Profesor extends Persona {
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "profesor")
    @NotAudited
    private List<Materia> materias = new ArrayList<>();
}