package org.example.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.logica.Alumno;

public class AlumnoController {
    public AlumnoController() {
    }

    private EntityManager em;

    // El constructor recibe el EntityManager
    public AlumnoController(EntityManager em) {
        this.em = em;
    }

    // Método para GUARDAR un alumno
    public void crear(Alumno alumno) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(alumno); // Guarda en la BD
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback(); // Si hay error, deshace los cambios
            e.printStackTrace();
        }
    }

    // Método para BUSCAR un alumno por ID
    public Alumno buscarPorId(Long id) {
        return em.find(Alumno.class, id);
    }

    // Método para ACTUALIZAR un alumno
    public void actualizar(Alumno alumno) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(alumno); // Actualiza los datos
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    // Método para ELIMINAR un alumno
    public void eliminar(Long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Alumno alumno = buscarPorId(id);
            if (alumno != null) {
                em.remove(alumno); // Borra de la BD
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
}
