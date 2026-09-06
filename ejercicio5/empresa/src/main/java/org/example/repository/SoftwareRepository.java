package org.example.repository;


import org.example.entity.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareRepository extends JpaRepository<Software, String> {
    // Hereda todo de JpaRepository. Solo interactúa con la tabla/entidad Software.
}
