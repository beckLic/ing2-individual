package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "vigencias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vigencia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Composición: 1 Producto tiene N Vigencias (histórico de precios).
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto prod;

    private double precio;

    @Temporal(TemporalType.DATE)
    private Date desde;

    @Temporal(TemporalType.DATE)
    private Date hasta;
}