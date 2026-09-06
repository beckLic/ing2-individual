package org.example.controller;


import org.example.dto.UsuarioLoginDTO;
import org.example.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Controller: Indica que esta clase es un controlador de Spring MVC (no un REST controller).
 * Su función es recibir peticiones HTTP, delegar la lógica al Service y devolver vistas HTML (Thymeleaf).
 */
@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // @GetMapping: Intercepta las peticiones HTTP tipo GET a la ruta "/login".
    // @RequestParam: Captura el parámetro "error" de la URL (ej: /login?error=true) si es que existe.
    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            // "Model" se usa para inyectar datos desde Java hacia la vista HTML.
            model.addAttribute("error", "Credenciales inválidas. Intente nuevamente.");
        }

        // Pasamos un objeto DTO vacío a la vista. El formulario de Thymeleaf lo va a "llenar" con lo que tipee el usuario.
        model.addAttribute("loginDto", new UsuarioLoginDTO());

        return "login"; // Retorna el archivo 'login.html' ubicado en src/main/resources/templates/
    }

    // @PostMapping: Intercepta las peticiones HTTP tipo POST cuando el usuario aprieta el botón de submit en el formulario.
    // @ModelAttribute: Toma los datos (username y password) del formulario HTML y los inyecta mágicamente en nuestro DTO.
    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute("loginDto") UsuarioLoginDTO loginDto) {
        try {
            // Lógica de validación didáctica (texto plano). Si falla, el service tira una RuntimeException.
            usuarioService.login(loginDto);

            // "redirect:": Le dice al navegador que cambie la URL y haga una nueva petición GET a "/home".
            return "redirect:/home";

        } catch (Exception e) {
            // Si la contraseña está mal o el usuario no existe, volvemos a cargar el login mandando la flag de error.
            return "redirect:/login?error=true";
        }
    }

    // Podemos mapear múltiples rutas al mismo método. Acá abarcamos tanto la raíz como "/home".
    @GetMapping({"/", "/home"})
    public String mostrarMenuPrincipal() {
        return "home"; // Retorna el archivo 'home.html' (el menú principal del sistema).
    }
}
