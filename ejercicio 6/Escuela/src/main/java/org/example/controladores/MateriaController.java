package org.example.controladores;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.MateriaDTO;
import org.example.entidades.Materia;
import org.example.servicios.MateriaService;
// Asumimos que vas a crear un AulaService simple que tenga un listarTodos()
// Si no lo tenés, podés inyectar el AulaRepositorio directamente por ahora.
import org.example.repositorios.AulaRepositorio;
import org.example.servicios.ProfesorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard/materias")
@RequiredArgsConstructor
public class MateriaController {
    private final MateriaService materiaService;
    private final ProfesorService profesorService;
    private final AulaRepositorio aulaRepositorio;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("materias", materiaService.listarTodas());
        return "materias/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("materiaDTO", new MateriaDTO());
        cargarListasDesplegables(model);
        return "materias/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("materiaDTO") MateriaDTO dto,
                          BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            cargarListasDesplegables(model);
            return "materias/formulario";
        }
        try {
            materiaService.guardar(dto);
            redirectAttributes.addFlashAttribute("exito", "Materia guardada correctamente.");
            return "redirect:/dashboard/materias";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            cargarListasDesplegables(model);
            return "materias/formulario";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Materia materia = materiaService.obtenerPorId(id);
        MateriaDTO dto = new MateriaDTO();

        dto.setId(materia.getId());
        dto.setNombre(materia.getNombre());
        dto.setGrado(materia.getGrado());
        dto.setProfesorId(materia.getProfesor().getId());
        dto.setAulaId(materia.getAula().getId());

        model.addAttribute("materiaDTO", dto);
        cargarListasDesplegables(model);
        return "materias/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            materiaService.eliminar(id);
            redirectAttributes.addFlashAttribute("exito", "Materia dada de baja.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la materia.");
        }
        return "redirect:/dashboard/materias";
    }

    // Método auxiliar para no repetir código
    private void cargarListasDesplegables(Model model) {
        model.addAttribute("profesores", profesorService.listarTodos());
        model.addAttribute("aulas", aulaRepositorio.findAll());
    }
}