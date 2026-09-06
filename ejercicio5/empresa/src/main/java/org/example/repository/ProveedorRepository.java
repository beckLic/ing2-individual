package org.example.repository;


import org.example.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    // El ID es Long (porque el CUIT supera el límite del int)
}
