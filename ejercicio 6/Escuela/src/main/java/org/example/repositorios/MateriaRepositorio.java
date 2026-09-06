package org.example.repositorios;

import org.example.entidades.Materia;
import org.example.enums.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepositorio extends JpaRepository<Materia, Long> {
    boolean existsByNombreAndGrado(String nombre, Grado grado);
    boolean existsByNombreAndGradoAndIdNot(String nombre, Grado grado, Long id);
    List<Materia> findByGrado(Grado grado);
}