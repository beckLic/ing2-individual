package org.example.service;

import org.example.dto.*;
import org.example.mapper.ProductoMapper;
import org.example.entity.*;
import org.example.repository.ProductoRepository;
import org.example.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    @Transactional
    public ProductoDTO crearSoftware(SoftwareDTO dto) {
        // Convertimos DTO a Entidad manualmente para persistir
        Software software = new Software();
        software.setNombre(dto.getNombre());
        software.setMarca(dto.getMarca());
        software.setEliminado(false);
        software.setClaveLicencia(dto.getClaveLicencia());
        software.setVersion(dto.getVersion());
        software.setVigenciaLicenciaMeses(dto.getVigenciaLicenciaMeses());
        software.setEnlaceDescarga(dto.getEnlaceDescarga());

        software = productoRepository.save(software);
        return productoMapper.toDTO(software);
    }

    @Override
    @Transactional
    public ProductoDTO crearHardware(HardwareDTO dto) {
        Hardware hardware = new Hardware();
        hardware.setNombre(dto.getNombre());
        hardware.setMarca(dto.getMarca());
        hardware.setEliminado(false);
        hardware.setNumeroSerie(dto.getNumeroSerie());
        hardware.setGarantiaMeses(dto.getGarantiaMeses());
        hardware.setEspecificaciones(dto.getEspecificaciones());

        hardware = productoRepository.save(hardware);
        return productoMapper.toDTO(hardware);
    }

    @Override
    public ProductoDTO buscarPorId(String id) {
        Producto prod = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return productoMapper.toDTO(prod);
    }

    @Override
    @Transactional
    public ProductoDTO modificarProducto(String id, ProductoDTO dto) {
        Producto prod = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        prod.setNombre(dto.getNombre());
        prod.setMarca(dto.getMarca());
        // En un proyecto real verificaríamos con instanceof si es Software/Hardware para setear atributos específicos

        prod = productoRepository.save(prod);
        return productoMapper.toDTO(prod);
    }

    @Override
    @Transactional
    public void eliminarProducto(String id) {
        // Lógica de negocio: BAJA LÓGICA (Soft Delete). No hacemos repository.delete(prod)
        Producto prod = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        prod.setEliminado(true);
        productoRepository.save(prod);
    }

    @Override
    public List<ProductoDTO> listarProductosActivos() {
        // Traemos solo los no eliminados usando el Query Method del Repository
        List<Producto> activos = productoRepository.findByEliminadoFalse();
        return activos.stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
