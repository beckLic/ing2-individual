package org.example.service;

import org.example.dto.PromocionDTO;
import org.example.dto.ProductoDTO;
import java.util.List;

public interface PromocionService {
    PromocionDTO crearPromocion(PromocionDTO dto);
    PromocionDTO agregarProducto(String idPromocion, String idProducto);
    PromocionDTO quitarProducto(String idPromocion, String idProducto);
    boolean validarVigencia(String idPromocion);
    double calcularPrecioPromocional(String idPromocion, double precioBase);
    List<ProductoDTO> listarProductosEnPromocion(String idPromocion);
}
