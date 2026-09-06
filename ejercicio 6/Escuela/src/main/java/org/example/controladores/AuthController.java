package org.example.controladores;

import org.springframework.ui.Model;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.ProfesorRegistroDTO;
import org.example.servicios.ProfesorService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final ProfesorService profesorService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registro")
    public String registroPage(Model model) {
        model.addAttribute("profesorDTO", new ProfesorRegistroDTO());
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@Valid @ModelAttribute("profesorDTO") ProfesorRegistroDTO dto,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "auth/registro";
        }
        try {
            profesorService.registrarProfesor(dto);
            return "redirect:/login?registrado=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/registro";
        }
    }
}
