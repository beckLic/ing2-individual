package org.example.controller;


import org.example.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos") // Prefijo para todas las rutas de este controller
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public String listarProductos(Model model) {
        // Model se usa para pasar la lista de DTOs del Service a la Vista
        model.addAttribute("productos", productoService.listarProductosActivos());
        return "producto/lista";
    }

    // @PathVariable: Extrae el valor "{id}" de la URL y lo pasa como parámetro
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable String id) {
        productoService.eliminarProducto(id); // Baja lógica
        return "redirect:/productos";
    }
}