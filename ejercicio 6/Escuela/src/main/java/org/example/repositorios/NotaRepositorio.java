package org.example.repositorios;

import org.example.entidades.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NotaRepositorio extends JpaRepository<Nota, Long> {
    Optional<Nota> findByAlumnoIdAndMateriaId(Long alumnoId, Long materiaId);
}