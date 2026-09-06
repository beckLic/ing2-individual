package org.example.servicios;

import lombok.RequiredArgsConstructor;
import org.example.dto.AlumnoDTO;
import org.example.entidades.Alumno;
import org.example.entidades.Rol;
import org.example.entidades.Usuario;
import org.example.enums.RolNombre;
import org.example.repositorios.AlumnoRepositorio;
import org.example.repositorios.RolRepositorio;
import org.example.repositorios.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnoService {

    private final AlumnoRepositorio alumnoRepositorio;
    private final RolRepositorio rolRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<Alumno> listarTodos() {
        return alumnoRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Alumno obtenerPorId(Long id) {
        return alumnoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con ID: " + id));
    }

    @Transactional
    public void guardar(AlumnoDTO dto) {
        if (dto.getId() == null) {
            if (alumnoRepositorio.existsByDni(dto.getDni())) throw new RuntimeException("El DNI ya está registrado.");
            if (alumnoRepositorio.existsByLegajo(dto.getLegajo())) throw new RuntimeException("El legajo ya está registrado.");
        } else {
            if (alumnoRepositorio.existsByDniAndIdNot(dto.getDni(), dto.getId())) throw new RuntimeException("El DNI ya pertenece a otro alumno.");
            if (alumnoRepositorio.existsByLegajoAndIdNot(dto.getLegajo(), dto.getId())) throw new RuntimeException("El legajo ya pertenece a otro alumno.");
        }

        Alumno alumno;

        if (dto.getId() != null) {
            alumno = obtenerPorId(dto.getId());
        } else {
            alumno = new Alumno();
            Usuario usuario = new Usuario();

            usuario.setEmail(dto.getMail());

            usuario.setPassword(passwordEncoder.encode(String.valueOf(dto.getDni())));

            Rol rolAlumno = rolRepositorio.findByNombre(RolNombre.ALUMNO)
                    .orElseThrow(() -> new RuntimeException("Error crítico: Rol ALUMNO no encontrado en la base de datos."));

            usuario.getRoles().add(rolAlumno);
            alumno.setUsuario(usuario);
        }

        alumno.setNombre(dto.getNombre());
        alumno.setApellido(dto.getApellido());
        alumno.setDni(dto.getDni());
        alumno.setLegajo(dto.getLegajo());
        alumno.setGrado(dto.getGrado());

        alumnoRepositorio.save(alumno);
    }

    @Transactional
    public void eliminar(Long id) {
        alumnoRepositorio.deleteById(id);
    }
}
