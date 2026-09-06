package org.example.controller;

import org.example.dto.VigenciaDTO;
import org.example.service.VigenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vigencias")
@RequiredArgsConstructor
public class VigenciaController {

    private final VigenciaService vigenciaService;

    @GetMapping("/{productoId}")
    public String listarPorProducto(@PathVariable String productoId, Model model) {
        model.addAttribute("vigencias", vigenciaService.listarPorProducto(productoId));
        return "vigencia/lista";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("vigenciaDto") VigenciaDTO dto) {
        vigenciaService.crearVigencia(dto);
        return "redirect:/productos";
    }
}