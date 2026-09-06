package org.example.controladores;

import lombok.RequiredArgsConstructor;
import org.example.entidades.Alumno;
import org.example.servicios.PortalProfesorService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/portal-profesor")
@RequiredArgsConstructor
public class PortalProfesorController {

    private final PortalProfesorService portalProfesorService;

    @GetMapping("/materias")
    public String misMaterias(Authentication auth, Model model) {
        model.addAttribute("profesor", portalProfesorService.obtenerProfesorLogueado(auth.getName()));
        return "profesores/mis-materias";
    }

    @GetMapping("/materias/{id}/alumnos")
    public String verAlumnos(@PathVariable Long id, Authentication auth, Model model) {
        List<Alumno> alumnos = portalProfesorService.obtenerAlumnosPorMateria(id, auth.getName());
        model.addAttribute("alumnos", alumnos);
        model.addAttribute("materiaId", id);
        model.addAttribute("notasMap", portalProfesorService.obtenerMapaDeNotas(id, alumnos));
        return "profesores/calificar";
    }

    @PostMapping("/calificar")
    public String calificar(@RequestParam Long materiaId, @RequestParam Long alumnoId,
                            @RequestParam Integer valor, RedirectAttributes attributes) {
        try {
            portalProfesorService.guardarNota(materiaId, alumnoId, valor);
            attributes.addFlashAttribute("exito", "Calificación guardada correctamente.");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Error al guardar nota.");
        }
        return "redirect:/portal-profesor/materias/" + materiaId + "/alumnos";
    }
}