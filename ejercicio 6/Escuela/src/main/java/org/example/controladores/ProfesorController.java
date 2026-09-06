package org.example.controladores;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.ProfesorDTO;
import org.example.entidades.Profesor;
import org.example.servicios.ProfesorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard/profesores")
@RequiredArgsConstructor
public class ProfesorController {
    private final ProfesorService profesorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("profesores", profesorService.listarTodos());
        return "profesores/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("profesorDTO", new ProfesorDTO());
        return "profesores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("profesorDTO") ProfesorDTO profesorDTO,
                          BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "profesores/formulario";
        }
        try {
            profesorService.guardarCRUD(profesorDTO);
            redirectAttributes.addFlashAttribute("exito", "El profesor se guardó correctamente.");
            return "redirect:/dashboard/profesores";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "profesores/formulario";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Profesor profesor = profesorService.obtenerPorId(id);
        ProfesorDTO dto = new ProfesorDTO();

        dto.setId(profesor.getId());
        dto.setNombre(profesor.getNombre());
        dto.setApellido(profesor.getApellido());
        dto.setMail(profesor.getUsuario().getEmail());
        dto.setDni(profesor.getDni());
        dto.setTitulo(profesor.getTitulo());
        dto.setSexo(profesor.getSexo());
        dto.setFechaNacimiento(profesor.getFechaNacimiento());

        model.addAttribute("profesorDTO", dto);
        return "profesores/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            profesorService.eliminar(id);
            redirectAttributes.addFlashAttribute("exito", "El profesor fue dado de baja exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al dar de baja al profesor.");
        }
        return "redirect:/dashboard/profesores";
    }
}