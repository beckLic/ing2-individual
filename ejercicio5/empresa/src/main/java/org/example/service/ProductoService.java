package org.example.service;

import org.example.dto.HardwareDTO;
import org.example.dto.ProductoDTO;
import org.example.dto.SoftwareDTO;

import java.util.List;

public interface ProductoService {
    ProductoDTO crearSoftware(SoftwareDTO dto);
    ProductoDTO crearHardware(HardwareDTO dto);
    ProductoDTO buscarPorId(String id);
    ProductoDTO modificarProducto(String id, ProductoDTO dto);
    void eliminarProducto(String id); // Baja lógica
    List<ProductoDTO> listarProductosActivos();
}