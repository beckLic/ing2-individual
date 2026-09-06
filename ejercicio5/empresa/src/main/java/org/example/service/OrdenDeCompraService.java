package org.example.service;

import org.example.dto.DetalleDTO;
import org.example.dto.OrdenDeCompraDTO;

import java.util.List;

public interface OrdenDeCompraService {
    OrdenDeCompraDTO crearOrden(OrdenDeCompraDTO dto);
    OrdenDeCompraDTO agregarDetalle(String idOrden, DetalleDTO detalleDto);
    void eliminarOrden(String id);
    List<OrdenDeCompraDTO> listarOrdenesActivas();
}
