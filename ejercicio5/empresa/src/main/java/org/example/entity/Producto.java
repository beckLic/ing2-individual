package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Capa: Entity
 * @Inheritance(strategy = InheritanceType.JOINED): Elegimos JOINED para que Producto, Software y Hardware
 * tengan sus propias tablas relacionadas por un ID. Es más normalizado y evita columnas nulas masivas
 * que tendríamos con SINGLE_TABLE.
 */
@Entity
@Table(name = "productos")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nombre;
    private String marca;

    // Bandera para baja lógica (soft delete)
    private boolean eliminado;

    // Relación inversa con Promocion
    @ManyToMany(mappedBy = "productos")
    private List<Promocion> promociones;

    // Relación inversa con Proveedor
    @ManyToMany(mappedBy = "productos")
    private List<Proveedor> proveedores;
}