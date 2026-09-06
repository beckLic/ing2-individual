package org.example.mapper;

import org.example.dto.StockDTO;
import org.example.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    // Capa: Mapper
    // Aísla el enum TipoMovimiento y aísla la relación 1 a 1 con Detalle extrayendo solo su ID.
    public StockDTO toDTO(Stock entity) {
        if (entity == null) return null;

        return StockDTO.builder()
                .id(entity.getId())
                .detalleId(entity.getDetalle() != null ? entity.getDetalle().getId() : null)
                .tipo(entity.getTipo())
                .fechaMovimiento(entity.getFechaMovimiento())
                .cantActual(entity.getCantActual())
                .umbralReposicion(entity.getUmbralReposicion())
                .cantMax(entity.getCantMax())
                .build();
    }
}
