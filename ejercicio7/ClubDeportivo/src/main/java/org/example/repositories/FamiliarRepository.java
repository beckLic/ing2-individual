package org.example.repositories;
import org.example.entities.Familiar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FamiliarRepository extends JpaRepository<Familiar, Long> {
    Optional<Familiar> findByDni(String dni);
    boolean existsByDni(String dni);
}
