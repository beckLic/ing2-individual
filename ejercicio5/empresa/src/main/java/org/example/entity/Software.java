package org.example.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "software")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Software extends Producto {
    private String claveLicencia;
    private String version;
    private int vigenciaLicenciaMeses;
    private String enlaceDescarga;
}