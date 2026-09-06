package org.example.service;


import org.example.dto.DetalleDTO;
import org.example.dto.OrdenDeCompraDTO;
import org.example.mapper.OrdenDeCompraMapper;
import org.example.entity.*;
import org.example.repository.OrdenDeCompraRepository;
import org.example.repository.ProductoRepository;
import org.example.repository.ProveedorRepository;
import org.example.service.OrdenDeCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenDeCompraServiceImpl implements OrdenDeCompraService {

    private final OrdenDeCompraRepository ordenRepository;
    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;
    private final OrdenDeCompraMapper ordenMapper;

    @Override
    @Transactional
    public OrdenDeCompraDTO crearOrden(OrdenDeCompraDTO dto) {
        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorCuit())
                .orElseThrow(() -> new RuntimeException("Proveedor no existe"));

        OrdenDeCompra orden = OrdenDeCompra.builder()
                .fecha(new Date())
                .totalPagado(dto.getTotalPagado())
                .eliminado(false)
                .proveedor(proveedor)
                .detalles(new ArrayList<>())
                .build();

        orden = ordenRepository.save(orden);
        return ordenMapper.toDTO(orden);
    }

    @Override
    @Transactional
    public OrdenDeCompraDTO agregarDetalle(String idOrden, DetalleDTO detalleDto) {
        OrdenDeCompra orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        Producto producto = productoRepository.findById(detalleDto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Detalle detalle = Detalle.builder()
                .producto(producto)
                .cantidad(detalleDto.getCantidad())
                .subtotal(detalleDto.getSubtotal())
                .eliminado(false)
                .build();

        // Lógica de Negocio: Sumar al total y agregar detalle (por CascadeType.ALL se guarda solo)
        orden.getDetalles().add(detalle);
        orden.setTotalPagado(orden.getTotalPagado() + detalle.getSubtotal());

        orden = ordenRepository.save(orden);
        return ordenMapper.toDTO(orden);
    }

    @Override
    @Transactional
    public void eliminarOrden(String id) {
        OrdenDeCompra orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        orden.setEliminado(true);
        // Cascading didáctico: podríamos también poner detalle.setEliminado(true) iterando.
        orden.getDetalles().forEach(d -> d.setEliminado(true));
        ordenRepository.save(orden);
    }

    @Override
    public List<OrdenDeCompraDTO> listarOrdenesActivas() {
        return ordenRepository.findByEliminadoFalse().stream()
                .map(ordenMapper::toDTO)
                .collect(Collectors.toList());
    }
}