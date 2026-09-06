package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    private int cantidad;
    private double subtotal;
    private boolean eliminado;

    // Relación 1 a 1 con Stock
    @OneToOne(mappedBy = "detalle")
    private Stock stock;
}