package org.example.servicios;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProfesorDTO;
import org.example.dto.ProfesorRegistroDTO;
import org.example.entidades.Profesor;
import org.example.entidades.Rol;
import org.example.entidades.Usuario;
import org.example.enums.RolNombre;
import org.example.repositorios.ProfesorRepositorio;
import org.example.repositorios.RolRepositorio;
import org.example.repositorios.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorService {
    private final ProfesorRepositorio profesorRepository;
    private final UsuarioRepositorio usuarioRepository;
    private final RolRepositorio rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional
    public void registrarProfesor(ProfesorRegistroDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        // 1. Crear Usuario
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        Rol rolProfesor = rolRepository.findByNombre(RolNombre.PROFESOR)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.getRoles().add(rolProfesor);

        // 2. Crear Profesor
        Profesor profesor = new Profesor();
        profesor.setNombre(dto.getNombre());
        profesor.setApellido(dto.getApellido());
        profesor.setDni(dto.getDni());
        profesor.setTitulo(dto.getTitulo());
        profesor.setSexo(dto.getSexo());
        profesor.setFechaNacimiento(dto.getFechaNacimiento());
        profesor.setUsuario(usuario); // Se guarda en cascada

        profesorRepository.save(profesor);

        // 3. Enviar Correo
        emailService.enviarCorreoBienvenida(usuario.getEmail(), profesor.getNombre());
    }

    @Transactional(readOnly = true)
    public List<Profesor> listarTodos() {
        return profesorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Profesor obtenerPorId(Long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + id));
    }

    @Transactional
    public void guardarCRUD(ProfesorDTO dto) {
        // Validación de DNI
        if (dto.getId() == null) {
            if (profesorRepository.existsByDni(dto.getDni())) throw new RuntimeException("El DNI ya está registrado.");
        } else {
            if (profesorRepository.existsByDniAndIdNot(dto.getDni(), dto.getId())) throw new RuntimeException("El DNI ya pertenece a otro profesor.");
        }

        Profesor profesor;

        if (dto.getId() != null) {
            profesor = obtenerPorId(dto.getId());
            profesor.getUsuario().setEmail(dto.getMail());
        } else {
            profesor = new Profesor();
            Usuario usuario = new Usuario();
            usuario.setEmail(dto.getMail());
            // Contraseña por defecto: El DNI del profesor (encriptada)
            usuario.setPassword(passwordEncoder.encode(String.valueOf(dto.getDni())));

            Rol rolProfesor = rolRepository.findByNombre(RolNombre.PROFESOR)
                    .orElseThrow(() -> new RuntimeException("Error crítico: Rol PROFESOR no encontrado."));

            usuario.getRoles().add(rolProfesor);
            profesor.setUsuario(usuario);
        }

        profesor.setNombre(dto.getNombre());
        profesor.setApellido(dto.getApellido());
        profesor.setDni(dto.getDni());
        profesor.setTitulo(dto.getTitulo());
        profesor.setSexo(dto.getSexo());
        profesor.setFechaNacimiento(dto.getFechaNacimiento());

        profesorRepository.save(profesor);
    }

    @Transactional
    public void eliminar(Long id) {
        // Gracias a @SQLDelete en Persona, esto hace un UPDATE encubierto (Baja lógica)
        profesorRepository.deleteById(id);
    }
}
