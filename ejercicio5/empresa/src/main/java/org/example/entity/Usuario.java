package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Capa: Entity (Modelo de Dominio)
 * Representa la tabla 'usuarios' en la base de datos.
 */
@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nombre;
}