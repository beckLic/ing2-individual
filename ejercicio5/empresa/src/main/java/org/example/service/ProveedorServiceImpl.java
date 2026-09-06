package org.example.service;

import org.example.dto.ProductoDTO;
import org.example.dto.ProveedorDTO;
import org.example.mapper.ProductoMapper;
import org.example.mapper.ProveedorMapper;
import org.example.entity.Producto;
import org.example.entity.Proveedor;
import org.example.repository.ProductoRepository;
import org.example.repository.ProveedorRepository;
import org.example.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;
    private final ProveedorMapper proveedorMapper;
    private final ProductoMapper productoMapper;

    @Override
    @Transactional
    public ProveedorDTO crearProveedor(ProveedorDTO dto) {
        Proveedor prov = Proveedor.builder()
                .cuit(dto.getCuit())
                .razonSocial(dto.getRazonSocial())
                .productos(new ArrayList<>())
                .build();
        return proveedorMapper.toDTO(proveedorRepository.save(prov));
    }

    @Override
    public ProveedorDTO buscarPorCuit(Long cuit) {
        return proveedorMapper.toDTO(proveedorRepository.findById(cuit)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado")));
    }

    @Override
    @Transactional
    public ProveedorDTO modificarProveedor(Long cuit, ProveedorDTO dto) {
        Proveedor prov = proveedorRepository.findById(cuit)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        prov.setRazonSocial(dto.getRazonSocial());
        return proveedorMapper.toDTO(proveedorRepository.save(prov));
    }


    @Transactional
    public ProveedorDTO modificarProductos(Long cuit, String productoId) {
        Proveedor prov = proveedorRepository.findById(cuit)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        Producto prod = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        prov.getProductos().add(prod);
        return proveedorMapper.toDTO(proveedorRepository.save(prov));
    }

    @Transactional
    public void eliminarProveedor(Long cuit) {
        proveedorRepository.deleteById(cuit);
    }


    public List<ProveedorDTO> listarProveedores() {
        return proveedorRepository.findAll().stream()
                .map(proveedorMapper::toDTO)
                .collect(Collectors.toList());
    }


    public List<ProductoDTO> listarProductos(Long cuit) {
        Proveedor prov = proveedorRepository.findById(cuit)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        return prov.getProductos().stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
