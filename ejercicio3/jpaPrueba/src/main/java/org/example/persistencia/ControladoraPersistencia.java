package org.example.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.logica.Alumno;

public class ControladoraPersistencia {


    public ControladoraPersistencia() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdIngPU");

        EntityManager em = emf.createEntityManager();

        this.aluJPA = new AlumnoController(em);
    }

    AlumnoController aluJPA = new AlumnoController();
    public void crearAlumno(Alumno alu) {
        aluJPA.crear(alu);
    }
}
