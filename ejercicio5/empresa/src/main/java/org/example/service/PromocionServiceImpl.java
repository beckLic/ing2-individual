package org.example.service;

import org.example.dto.ProductoDTO;
import org.example.dto.PromocionDTO;
import org.example.mapper.ProductoMapper;
import org.example.mapper.PromocionMapper;
import org.example.entity.Producto;
import org.example.entity.Promocion;
import org.example.repository.ProductoRepository;
import org.example.repository.PromocionRepository;
import org.example.service.PromocionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromocionServiceImpl implements PromocionService {

    private final PromocionRepository promocionRepository;
    private final ProductoRepository productoRepository;
    private final PromocionMapper promocionMapper;
    private final ProductoMapper productoMapper;

    @Override
    @Transactional
    public PromocionDTO crearPromocion(PromocionDTO dto) {
        Promocion prom = Promocion.builder()
                .nombre(dto.getNombre())
                .porcentajeDescuento(dto.getPorcentajeDescuento())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .activo(true)
                .productos(new ArrayList<>())
                .build();
        return promocionMapper.toDTO(promocionRepository.save(prom));
    }

    @Override
    @Transactional
    public PromocionDTO agregarProducto(String idPromocion, String idProducto) {
        Promocion prom = promocionRepository.findById(idPromocion).orElseThrow();
        Producto prod = productoRepository.findById(idProducto).orElseThrow();
        prom.getProductos().add(prod);
        return promocionMapper.toDTO(promocionRepository.save(prom));
    }

    @Override
    @Transactional
    public PromocionDTO quitarProducto(String idPromocion, String idProducto) {
        Promocion prom = promocionRepository.findById(idPromocion).orElseThrow();
        prom.getProductos().removeIf(p -> p.getId().equals(idProducto));
        return promocionMapper.toDTO(promocionRepository.save(prom));
    }

    @Override
    public boolean validarVigencia(String idPromocion) {
        Promocion prom = promocionRepository.findById(idPromocion).orElseThrow();
        Date hoy = new Date();
        return prom.getActivo() && hoy.after(prom.getFechaInicio()) && hoy.before(prom.getFechaFin());
    }

    @Override
    public double calcularPrecioPromocional(String idPromocion, double precioBase) {
        Promocion prom = promocionRepository.findById(idPromocion).orElseThrow();
        if (validarVigencia(idPromocion)) {
            return precioBase - (precioBase * (prom.getPorcentajeDescuento() / 100.0));
        }
        return precioBase;
    }

    @Override
    public List<ProductoDTO> listarProductosEnPromocion(String idPromocion) {
        Promocion prom = promocionRepository.findById(idPromocion).orElseThrow();
        return prom.getProductos().stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
