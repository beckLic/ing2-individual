package org.example.service;

import org.example.dto.DetalleDTO;
import org.example.mapper.DetalleMapper;
import org.example.entity.Detalle;
import org.example.repository.DetalleRepository;
import org.example.service.DetalleService;
import lombok.RequiredArgsConstructor;
import org.example.dto.DetalleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DetalleServiceImpl implements DetalleService {

    private final DetalleRepository detalleRepository;
    private final DetalleMapper detalleMapper;

    @Override
    public DetalleDTO buscarDetalle(String id) {
        Detalle detalle = detalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        return detalleMapper.toDTO(detalle);
    }

    @Override
    @Transactional
    public DetalleDTO modificarDetalle(String id, int cantidad, double subtotal) {
        Detalle detalle = detalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        detalle.setCantidad(cantidad);
        detalle.setSubtotal(subtotal);
        return detalleMapper.toDTO(detalleRepository.save(detalle));
    }

    @Override
    @Transactional
    public void eliminarDetalle(String id) {
        Detalle detalle = detalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        detalle.setEliminado(true); // Baja lógica para no romper la base
        detalleRepository.save(detalle);
    }
}
