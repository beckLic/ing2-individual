package org.example.controladores;

import lombok.RequiredArgsConstructor;
import org.example.entidades.Alumno;
import org.example.repositorios.NotaRepositorio;
import org.example.servicios.EstudianteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    private final NotaRepositorio notaRepositorio;

    @GetMapping("/materias")
    public String misMaterias(Authentication auth, Model model) {
        Alumno alumno = estudianteService.obtenerPorEmail(auth.getName());
        model.addAttribute("alumno", alumno);

        // Creamos un mapa: MateriaID -> NotaValor
        java.util.Map<Long, Integer> notasMap = new java.util.HashMap<>();
        for (org.example.entidades.Materia m : alumno.getMaterias()) {
            notaRepositorio.findByAlumnoIdAndMateriaId(alumno.getId(), m.getId())
                    .ifPresent(nota -> notasMap.put(m.getId(), nota.getValor()));
        }
        model.addAttribute("notasMap", notasMap);

        return "estudiantes/mis-materias";
    }

    @GetMapping("/inscripcion")
    public String panelInscripcion(Authentication auth, Model model) {
        model.addAttribute("materiasDisponibles", estudianteService.obtenerMateriasDisponiblesParaInscripcion(auth.getName()));
        return "estudiantes/inscripcion";
    }

    @PostMapping("/inscribir/{materiaId}")
    public String inscribir(Authentication auth, @PathVariable Long materiaId, RedirectAttributes attributes) {
        try {
            estudianteService.inscribirEnMateria(auth.getName(), materiaId);
            attributes.addFlashAttribute("exito", "¡Inscripción exitosa!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/estudiantes/materias";
    }
}