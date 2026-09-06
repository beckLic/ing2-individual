package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "hardware")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hardware extends Producto {
    private String numeroSerie;
    private int garantiaMeses;

    // @ElementCollection es ideal para colecciones de tipos básicos (String) sin crear una entidad entera adicional.
    @ElementCollection
    @CollectionTable(name = "hardware_especificaciones", joinColumns = @JoinColumn(name = "hardware_id"))
    @Column(name = "especificacion")
    private List<String> especificaciones;
}