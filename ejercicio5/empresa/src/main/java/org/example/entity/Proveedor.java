package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedor {
    // Nota didáctica: El diagrama indica 'int cuit', pero los CUITs en Argentina (11 dígitos) superan
    // el valor máximo de int (2.147.483.647). Se utiliza Long para evitar desbordamiento.
    @Id
    private Long cuit;

    private String razonSocial;

    @ManyToMany
    @JoinTable(
            name = "proveedor_producto",
            joinColumns = @JoinColumn(name = "proveedor_cuit"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;
}