package org.example.repository;


import org.example.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Capa: Repository
 * Interfaz que hereda de JpaRepository para obtener todos los métodos CRUD básicos
 * (save, findById, findAll, delete) sin necesidad de escribir SQL.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    // Query Method: Spring Boot automáticamente crea la consulta SQL
    // 'SELECT * FROM usuarios WHERE username = ?' solo leyendo el nombre de este método.
    // Se usa Optional para evitar NullPointerException si el usuario no existe.
    Optional<Usuario> findByUsername(String username);
}