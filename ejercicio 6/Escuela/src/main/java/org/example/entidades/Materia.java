package org.example.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Grado;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Audited
@SQLDelete(sql = "UPDATE materia SET activo = false WHERE id=?")
@SQLRestriction("activo = true")
public class Materia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Grado grado;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    @NotAudited
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    @NotAudited
    private Aula aula;

    @Column(nullable = false)
    private boolean activo = true;
}
