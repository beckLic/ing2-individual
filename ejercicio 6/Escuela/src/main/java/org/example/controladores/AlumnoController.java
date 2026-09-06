package org.example.controladores;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.AlumnoDTO;
import org.example.entidades.Alumno;
import org.example.servicios.AlumnoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard/alumnos")
@RequiredArgsConstructor
public class AlumnoController {
    private final AlumnoService alumnoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alumnos", alumnoService.listarTodos());
        return "alumnos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("alumnoDTO", new AlumnoDTO());
        return "alumnos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("alumnoDTO") AlumnoDTO alumnoDTO,
                          BindingResult result,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        if (result.hasErrors()) {
            return "alumnos/formulario";
        }
        try {
            alumnoService.guardar(alumnoDTO);
            redirectAttributes.addFlashAttribute("exito", "El alumno se guardó correctamente.");
            return "redirect:/dashboard/alumnos";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "alumnos/formulario";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Alumno alumno = alumnoService.obtenerPorId(id);
        AlumnoDTO dto = new AlumnoDTO();

        dto.setId(alumno.getId());
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());
        dto.setDni(alumno.getDni());
        dto.setLegajo(alumno.getLegajo());
        dto.setGrado(alumno.getGrado());

        model.addAttribute("alumnoDTO", dto);
        return "alumnos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            alumnoService.eliminar(id);
            redirectAttributes.addFlashAttribute("exito", "El alumno fue eliminado.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el alumno.");
        }
        return "redirect:/dashboard/alumnos";
    }
}