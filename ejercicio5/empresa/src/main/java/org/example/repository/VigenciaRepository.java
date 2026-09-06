package org.example.repository;


import org.example.entity.Vigencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VigenciaRepository extends JpaRepository<Vigencia, String> {
}
