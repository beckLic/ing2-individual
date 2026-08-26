package org.example.logica;

import org.example.persistencia.ControladoraPersistencia;

public class Controladora {
    ControladoraPersistencia controladora = new ControladoraPersistencia();
    public void crearAlumno(Alumno alumno){
        controladora.crearAlumno(alumno);
    }
}
