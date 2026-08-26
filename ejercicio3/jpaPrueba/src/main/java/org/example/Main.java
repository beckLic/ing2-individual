package org.example;

import org.example.logica.Alumno;
import org.example.logica.Controladora;
import org.example.persistencia.ControladoraPersistencia;

import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Controladora controladora = new Controladora();
        Alumno alu = new Alumno("augusto","beck", new Date());

        controladora.crearAlumno(alu);

    }
}
