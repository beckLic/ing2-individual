package org.example.dto;

import org.example.enums.TipoMovimiento;
import lombok.*;
import org.example.enums.TipoMovimiento;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDTO {
    private String id;
    private String detalleId;
    private TipoMovimiento tipo;
    private Date fechaMovimiento;
    private int cantActual;
    private int umbralReposicion;
    private int cantMax;
}
