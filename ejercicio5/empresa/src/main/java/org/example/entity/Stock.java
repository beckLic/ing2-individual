package org.example.entity;


import org.example.enums.TipoMovimiento;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "templates/stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "detalle_id", unique = true)
    private Detalle detalle;

    // EnumType.STRING guarda "ENTRADA" o "SALIDA" (legible) en lugar de 0 o 1
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;

    private int cantActual;
    private int umbralReposicion;
    private int cantMax;
}
