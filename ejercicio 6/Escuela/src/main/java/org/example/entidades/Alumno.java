package org.example.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.enums.Grado;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Audited
public class Alumno extends Persona {

    @Enumerated(EnumType.STRING)
    private Grado grado;

    @Column(nullable = false)
    private int legajo;

    @ManyToMany
    @JoinTable(name = "alumno_materia",
            joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id"))
    @NotAudited
    private List<Materia> materias = new ArrayList<>();
}