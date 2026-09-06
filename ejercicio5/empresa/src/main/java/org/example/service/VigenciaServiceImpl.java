package org.example.service;

import org.example.dto.VigenciaDTO;
import org.example.mapper.VigenciaMapper;
import org.example.entity.Producto;
import org.example.entity.Vigencia;
import org.example.repository.ProductoRepository;
import org.example.repository.VigenciaRepository;
import org.example.service.VigenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VigenciaServiceImpl implements VigenciaService {

    private final VigenciaRepository vigenciaRepository;
    private final ProductoRepository productoRepository;
    private final VigenciaMapper vigenciaMapper;

    @Override
    @Transactional
    public VigenciaDTO crearVigencia(VigenciaDTO dto) {
        Producto prod = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Vigencia vigencia = Vigencia.builder()
                .prod(prod)
                .precio(dto.getPrecio())
                .desde(dto.getDesde())
                .hasta(dto.getHasta())
                .build();
        return vigenciaMapper.toDTO(vigenciaRepository.save(vigencia));
    }

    @Override
    public List<VigenciaDTO> listarPorProducto(String productoId) {
        // Didactico: En un caso real usariamos un QueryMethod findByProdId(String id)
        return vigenciaRepository.findAll().stream()
                .filter(v -> v.getProd().getId().equals(productoId))
                .map(vigenciaMapper::toDTO)
                .collect(Collectors.toList());
    }
}