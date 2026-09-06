package org.example.repository;


import org.example.entity.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, String> {
    // Query Method: Busca todas las promociones que estén marcadas como activas
    List<Promocion> findByActivoTrue();
}
