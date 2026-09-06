package org.example.repositorios;

import org.example.entidades.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesorRepositorio extends JpaRepository<Profesor, Long> {
    boolean existsByDni(int dni);
    boolean existsByDniAndIdNot(int dni, Long id);
    Optional<Profesor> findByUsuarioEmail(String email);
}