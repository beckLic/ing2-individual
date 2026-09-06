package org.example.controller;

import org.example.dto.PromocionDTO;
import org.example.service.PromocionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/promociones")
@RequiredArgsConstructor
public class PromocionController {

    private final PromocionService promocionService;

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("promocionDto", PromocionDTO.builder().build());
        return "promocion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("promocionDto") PromocionDTO dto) {
        promocionService.crearPromocion(dto);
        return "redirect:/home";
    }

    @PostMapping("/{idPromocion}/agregar-producto")
    public String agregarProducto(@PathVariable String idPromocion, @RequestParam String idProducto) {
        promocionService.agregarProducto(idPromocion, idProducto);
        return "redirect:/promociones/" + idPromocion;
    }
}
