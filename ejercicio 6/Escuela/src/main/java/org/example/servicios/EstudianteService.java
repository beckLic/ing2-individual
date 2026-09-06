package org.example.servicios;

import lombok.RequiredArgsConstructor;
import org.example.entidades.Alumno;
import org.example.entidades.Materia;
import org.example.repositorios.AlumnoRepositorio;
import org.example.repositorios.MateriaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    private final AlumnoRepositorio alumnoRepositorio;
    private final MateriaRepositorio materiaRepositorio;

    @Transactional(readOnly = true)
    public Alumno obtenerPorEmail(String email) {
        return alumnoRepositorio.findByUsuarioEmail(email)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Materia> obtenerMateriasDisponiblesParaInscripcion(String email) {
        Alumno alumno = obtenerPorEmail(email);

        // Buscamos TODAS las materias que correspondan al grado del alumno
        List<Materia> disponibles = materiaRepositorio.findByGrado(alumno.getGrado());

        // Filtramos y quitamos aquellas en las que el alumno ya está inscripto
        disponibles.removeAll(alumno.getMaterias());

        return disponibles;
    }

    @Transactional
    public void inscribirEnMateria(String email, Long materiaId) {
        Alumno alumno = obtenerPorEmail(email);
        Materia materia = materiaRepositorio.findById(materiaId)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada."));

        // Validamos que sean del mismo grado y que no esté inscripto
        if (alumno.getGrado() != materia.getGrado()) {
            throw new RuntimeException("No puedes inscribirte a una materia de otro grado.");
        }
        if (!alumno.getMaterias().contains(materia)) {
            alumno.getMaterias().add(materia);
            alumnoRepositorio.save(alumno);
        }
    }
}