package org.example.service;

import org.example.dto.ProveedorDTO;

import java.util.List;

public interface ProveedorService {
    ProveedorDTO crearProveedor(ProveedorDTO dto);
    ProveedorDTO buscarPorCuit(Long cuit);
    ProveedorDTO modificarProveedor(Long cuit, ProveedorDTO dto);
    void eliminarProveedor(Long cuit);
    List<ProveedorDTO> listarProveedores();
}
