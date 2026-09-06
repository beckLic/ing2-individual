package org.example.service;


import org.example.dto.StockDTO;
import org.example.enums.TipoMovimiento;
import java.util.List;

public interface StockService {
    StockDTO registrarMovimiento(String productoId, TipoMovimiento tipo, int cantidad, String detalleId);
    List<StockDTO> listarMovimientosPorProducto(String productoId);
    void hacerPedido(String productoId, Long proveedorCuit);
}
