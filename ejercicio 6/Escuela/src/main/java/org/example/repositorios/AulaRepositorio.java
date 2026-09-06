package org.example.repositorios;

import org.example.entidades.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepositorio extends JpaRepository<Aula, Long> {

    // Métodos útiles por si querés validar que no se repitan los números de aula
    boolean existsByNumero(int numero);
    boolean existsByNumeroAndIdNot(int numero, Long id);

}