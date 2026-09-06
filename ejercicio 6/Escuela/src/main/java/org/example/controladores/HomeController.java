package org.example.controladores;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String redireccionarPorRol(Authentication auth) {
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
        boolean isProfesor = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("PROFESOR"));

        if (isAdmin) {
            return "redirect:/dashboard/alumnos";
        } else if (isProfesor) {
            return "redirect:/portal-profesor/materias";
        } else {
            return "redirect:/estudiantes/materias";
        }
    }


}