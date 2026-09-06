package org.example.controller;
import org.example.dto.DetalleDTO;
import org.example.dto.OrdenDeCompraDTO;
import org.example.service.OrdenDeCompraService;
import org.example.service.ProductoService;
import org.example.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ordenes")
@RequiredArgsConstructor
public class OrdenDeCompraController {

    private final OrdenDeCompraService ordenService;
    private final ProveedorService proveedorService; // Para el combo de proveedores
    private final ProductoService productoService;   // Para el combo de productos

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ordenes", ordenService.listarOrdenesActivas());
        return "orden/lista";
    }

    @GetMapping("/nueva")
    public String formulario(Model model) {
        model.addAttribute("ordenDto", OrdenDeCompraDTO.builder().totalPagado(0.0).build());
        model.addAttribute("proveedores", proveedorService.listarProveedores()); // Llenamos el <select>
        return "orden/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("ordenDto") OrdenDeCompraDTO dto) {
        ordenService.crearOrden(dto);
        return "redirect:/ordenes";
    }

    // NUEVO: Entrar a ver la factura para cargarle los detalles
    @GetMapping("/{id}")
    public String verFactura(@PathVariable String id, Model model) {
        // Didáctico: Filtramos la lista para encontrar la orden
        OrdenDeCompraDTO orden = ordenService.listarOrdenesActivas().stream()
                .filter(o -> o.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        model.addAttribute("orden", orden);
        model.addAttribute("detalleDto", DetalleDTO.builder().build());
        model.addAttribute("productos", productoService.listarProductosActivos());
        return "orden/ver";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        ordenService.eliminarOrden(id);
        return "redirect:/ordenes";
    }
}
