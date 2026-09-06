package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordenes_compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenDeCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    private double totalPagado;
    private boolean eliminado;

    @ManyToOne
    @JoinColumn(name = "proveedor_cuit", nullable = false)
    private Proveedor proveedor;

    // Composición estricta: CascadeType.ALL y orphanRemoval aseguran que al borrar
    // la orden, se borren sus detalles dependientes.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orden_id")
    private List<Detalle> detalles;
}