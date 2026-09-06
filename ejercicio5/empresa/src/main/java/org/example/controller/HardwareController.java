package org.example.controller;


import org.example.dto.HardwareDTO;
import org.example.service.HardwareService;
import org.example.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hardware")
@RequiredArgsConstructor
public class HardwareController {

    private final ProductoService productoService;
    private final HardwareService hardwareService;

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("hardwareDto", new HardwareDTO());
        return "hardware/formulario";
    }

    @PostMapping("/guardar")
    public String guardarHardware(@ModelAttribute("hardwareDto") HardwareDTO dto) {
        if (dto.getId() == null || dto.getId().isEmpty()) {
            productoService.crearHardware(dto);
        } else {
            productoService.modificarProducto(dto.getId(), dto);
        }
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editarHardware(@PathVariable String id, Model model) {
        model.addAttribute("hardwareDto", productoService.buscarPorId(id));
        return "hardware/formulario";
    }
}