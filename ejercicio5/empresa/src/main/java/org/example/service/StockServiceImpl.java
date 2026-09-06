package org.example.service;

import org.example.dto.StockDTO;
import org.example.mapper.StockMapper;
import org.example.entity.Detalle;
import org.example.entity.Stock;
import org.example.enums.TipoMovimiento;
import org.example.repository.DetalleRepository;
import org.example.repository.StockRepository;
import org.example.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final DetalleRepository detalleRepository;
    private final StockMapper stockMapper;

    @Override
    @Transactional
    public StockDTO registrarMovimiento(String productoId, TipoMovimiento tipo, int cantidad, String detalleId) {
        Detalle detalle = null;
        if (detalleId != null) {
            detalle = detalleRepository.findById(detalleId).orElse(null);
        }

        Stock stock = Stock.builder()
                .tipo(tipo)
                .fechaMovimiento(new Date())
                .cantActual(cantidad) // Logica simplificada para el ejemplo
                .detalle(detalle)
                .build();
        return stockMapper.toDTO(stockRepository.save(stock));
    }

    @Override
    public List<StockDTO> listarMovimientosPorProducto(String productoId) {
        // Filtrado básico en memoria (didactico)
        return stockRepository.findAll().stream()
                .filter(s -> s.getDetalle() != null && s.getDetalle().getProducto().getId().equals(productoId))
                .map(stockMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void hacerPedido(String productoId, Long proveedorCuit) {
        // Lógica de negocio: Aquí se integraría con OrdenDeCompraService para generar un pedido automático.
        System.out.println("Pedido generado para el producto " + productoId + " al proveedor " + proveedorCuit);
    }
}
