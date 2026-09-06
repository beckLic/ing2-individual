package org.example.servicios;

import lombok.RequiredArgsConstructor;
import org.example.entidades.Alumno;
import org.example.entidades.Materia;
import org.example.entidades.Nota;
import org.example.entidades.Profesor;
import org.example.repositorios.AlumnoRepositorio;
import org.example.repositorios.MateriaRepositorio;
import org.example.repositorios.NotaRepositorio;
import org.example.repositorios.ProfesorRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PortalProfesorService {
    private final ProfesorRepositorio profesorRepositorio;
    private final MateriaRepositorio materiaRepositorio;
    private final AlumnoRepositorio alumnoRepositorio;
    private final NotaRepositorio notaRepositorio;

    public Profesor obtenerProfesorLogueado(String email) {
        return profesorRepositorio.findByUsuarioEmail(email)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado."));
    }

    public List<Alumno> obtenerAlumnosPorMateria(Long materiaId, String email) {
        // Validar que la materia pertenezca al profesor
        Materia materia = materiaRepositorio.findById(materiaId).orElseThrow();
        if (!materia.getProfesor().getUsuario().getEmail().equals(email)) {
            throw new RuntimeException("No tienes permiso para ver esta materia.");
        }
        return alumnoRepositorio.findByMateriasId(materiaId);
    }

    // Trae las notas actuales de los alumnos de una materia para mostrarlas en la vista
    public Map<Long, Integer> obtenerMapaDeNotas(Long materiaId, List<Alumno> alumnos) {
        Map<Long, Integer> notas = new HashMap<>();
        for (Alumno alumno : alumnos) {
            notaRepositorio.findByAlumnoIdAndMateriaId(alumno.getId(), materiaId)
                    .ifPresent(nota -> notas.put(alumno.getId(), nota.getValor()));
        }
        return notas;
    }

    @Transactional
    public void guardarNota(Long materiaId, Long alumnoId, Integer valor) {
        Nota nota = notaRepositorio.findByAlumnoIdAndMateriaId(alumnoId, materiaId)
                .orElse(new Nota());

        if (nota.getId() == null) {
            Alumno alumno = alumnoRepositorio.findById(alumnoId).orElseThrow();
            Materia materia = materiaRepositorio.findById(materiaId).orElseThrow();
            nota.setAlumno(alumno);
            nota.setMateria(materia);
        }
        nota.setValor(valor);
        notaRepositorio.save(nota);
    }
}