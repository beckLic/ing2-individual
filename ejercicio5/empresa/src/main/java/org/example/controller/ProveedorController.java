package org.example.controller;

import org.example.dto.ProveedorDTO;
import org.example.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedores", proveedorService.listarProveedores());
        return "proveedor/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("proveedorDto", ProveedorDTO.builder().build());
        return "proveedor/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("proveedorDto") ProveedorDTO dto) {
        try {
            // 1. Intentamos buscar el proveedor.
            // Si existe, el código avanza a la siguiente línea.
            proveedorService.buscarPorCuit(dto.getCuit());

            // 2. Como existe, lo modificamos.
            proveedorService.modificarProveedor(dto.getCuit(), dto);

        } catch (RuntimeException e) {
            // 3. Si "buscarPorCuit" lanza la excepción "Proveedor no encontrado",
            // el código salta automáticamente acá. Significa que es nuevo.
            proveedorService.crearProveedor(dto);
        }
        return "redirect:/proveedores";
    }

    @GetMapping("/eliminar/{cuit}")
    public String eliminar(@PathVariable Long cuit) {
        proveedorService.eliminarProveedor(cuit);
        return "redirect:/proveedores";
    }

    @GetMapping("/editar/{cuit}")
    public String editar(@PathVariable Long cuit, Model model) {
        model.addAttribute("proveedorDto", proveedorService.buscarPorCuit(cuit));
        return "proveedor/formulario";
    }
}
