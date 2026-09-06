package org.example.servicios;

import lombok.RequiredArgsConstructor;
import org.example.dto.MateriaDTO;
import org.example.entidades.Aula;
import org.example.entidades.Materia;
import org.example.entidades.Profesor;
import org.example.repositorios.AulaRepositorio;
import org.example.repositorios.MateriaRepositorio;
import org.example.repositorios.ProfesorRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaService {
    private final MateriaRepositorio materiaRepositorio;
    private final ProfesorRepositorio profesorRepositorio;
    private final AulaRepositorio aulaRepositorio;

    @Transactional(readOnly = true)
    public List<Materia> listarTodas() {
        return materiaRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Materia obtenerPorId(Long id) {
        return materiaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
    }

    @Transactional
    public void guardar(MateriaDTO dto) {
        if (dto.getId() == null) {
            if (materiaRepositorio.existsByNombreAndGrado(dto.getNombre(), dto.getGrado())) {
                throw new RuntimeException("Ya existe una materia con ese nombre en ese grado.");
            }
        } else {
            if (materiaRepositorio.existsByNombreAndGradoAndIdNot(dto.getNombre(), dto.getGrado(), dto.getId())) {
                throw new RuntimeException("Ya existe otra materia con ese nombre en ese grado.");
            }
        }

        Materia materia = (dto.getId() != null) ? obtenerPorId(dto.getId()) : new Materia();

        Profesor profesor = profesorRepositorio.findById(dto.getProfesorId())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        Aula aula = aulaRepositorio.findById(dto.getAulaId())
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        materia.setNombre(dto.getNombre());
        materia.setGrado(dto.getGrado());
        materia.setProfesor(profesor);
        materia.setAula(aula);

        materiaRepositorio.save(materia);
    }

    @Transactional
    public void eliminar(Long id) {
        materiaRepositorio.deleteById(id);
    }
}