package org.example.controller;

import org.example.dto.DetalleDTO;
import org.example.service.DetalleService;
import org.example.service.OrdenDeCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/detalles")
@RequiredArgsConstructor
public class DetalleController {

    private final DetalleService detalleService;
    private final OrdenDeCompraService ordenService;

    // Ruta para asociar un detalle nuevo a una orden existente
    @PostMapping("/agregar/{idOrden}")
    public String agregarDetalle(@PathVariable String idOrden, @ModelAttribute("detalleDto") DetalleDTO dto) {
        ordenService.agregarDetalle(idOrden, dto);
        return "redirect:/ordenes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        detalleService.eliminarDetalle(id);
        return "redirect:/ordenes"; // Se redirige a la vista de órdenes
    }
}