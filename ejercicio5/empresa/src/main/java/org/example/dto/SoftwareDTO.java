package org.example.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SoftwareDTO extends ProductoDTO {
    private String claveLicencia;
    private String version;
    private int vigenciaLicenciaMeses;
    private String enlaceDescarga;
}
