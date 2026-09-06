package org.example.controller;


import org.example.dto.SoftwareDTO;
import org.example.service.ProductoService;
import org.example.service.SoftwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/software")
@RequiredArgsConstructor
public class SoftwareController {

    private final ProductoService productoService;
    private final SoftwareService softwareService;

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("softwareDto", new SoftwareDTO());
        return "software/formulario";
    }

    @PostMapping("/guardar")
    public String guardarSoftware(@ModelAttribute("softwareDto") SoftwareDTO dto) {
        if (dto.getId() == null || dto.getId().isEmpty()) {
            productoService.crearSoftware(dto);
        } else {
            productoService.modificarProducto(dto.getId(), dto);
        }
        return "redirect:/productos";
    }

    @PostMapping("/renovar/{id}")
    public String renovarLicencia(@PathVariable String id, @RequestParam int mesesExtra) {
        softwareService.renovarLicencia(id, mesesExtra);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editarSoftware(@PathVariable String id, Model model) {
        model.addAttribute("softwareDto", productoService.buscarPorId(id));
        return "software/formulario";
    }
}
