package org.example.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
// 1. Intercepta el deleteById() y hace un UPDATE en su lugar
@SQLDelete(sql = "UPDATE persona SET activo = false WHERE id=?")
// 2. Filtra automáticamente todos los SELECT para ignorar los inactivos
@SQLRestriction("activo = true")
public abstract class Persona {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    // ATENCIÓN: Quitamos el unique = true (explicación abajo)
    @Column(nullable = false)
    private int dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    // 3. Nuevo campo para controlar el estado
    @Column(nullable = false)
    private boolean activo = true;
}