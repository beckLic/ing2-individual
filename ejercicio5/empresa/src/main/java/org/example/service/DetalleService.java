package org.example.service;

import org.example.dto.DetalleDTO;

public interface DetalleService {
    DetalleDTO buscarDetalle(String id);
    DetalleDTO modificarDetalle(String id, int cantidad, double subtotal);
    void eliminarDetalle(String id);
}