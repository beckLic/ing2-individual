package org.example.repositorios;

import org.example.entidades.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, Long> {
    boolean existsByDni(int dni);
    boolean existsByLegajo(int legajo);
    // Estos métodos sirven para validar duplicados al actualizar, ignorando el ID actual
    boolean existsByDniAndIdNot(int dni, Long id);
    boolean existsByLegajoAndIdNot(int legajo, Long id);
    Optional<Alumno> findByUsuarioEmail(String email);
    List<Alumno> findByMateriasId(Long materiaId);
}
