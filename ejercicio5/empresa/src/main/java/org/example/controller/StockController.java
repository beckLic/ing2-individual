package org.example.controller;


import org.example.enums.TipoMovimiento;
import org.example.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/templates/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/{productoId}")
    public String verMovimientos(@PathVariable String productoId, Model model) {
        model.addAttribute("movimientos", stockService.listarMovimientosPorProducto(productoId));
        model.addAttribute("productoId", productoId);
        return "templates/stock/lista";
    }

    @PostMapping("/registrar")
    public String registrarMovimiento(@RequestParam String productoId,
                                      @RequestParam TipoMovimiento tipo,
                                      @RequestParam int cantidad) {
        stockService.registrarMovimiento(productoId, tipo, cantidad, null);
        return "redirect:/stock/" + productoId;
    }
}
