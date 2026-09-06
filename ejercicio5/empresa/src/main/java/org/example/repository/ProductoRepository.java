package org.example.repository;

import org.example.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
    // Query Method: Trae todos los productos cuya bandera 'eliminado' sea falsa (Baja lógica)
    List<Producto> findByEliminadoFalse();
}