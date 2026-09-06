package org.example.repository;


import org.example.entity.OrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, String> {
    // Query Method para listar solo las órdenes que no fueron dadas de baja
    List<OrdenDeCompra> findByEliminadoFalse();
}
