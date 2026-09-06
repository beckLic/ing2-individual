package org.example.service;


import org.example.dto.VigenciaDTO;
import java.util.List;

public interface VigenciaService {
    VigenciaDTO crearVigencia(VigenciaDTO dto);
    List<VigenciaDTO> listarPorProducto(String productoId);
}
