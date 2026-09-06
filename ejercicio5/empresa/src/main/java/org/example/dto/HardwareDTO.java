package org.example.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HardwareDTO extends ProductoDTO {
    private String numeroSerie;
    private int garantiaMeses;
    private List<String> especificaciones;
}
