package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que arranca la aplicación Spring Boot.
 *
 * @SpringBootApplication es una "macro-anotación" que agrupa tres anotaciones:
 * 1. @Configuration: Permite registrar beans extra en el contexto.
 * 2. @EnableAutoConfiguration: Le dice a Spring Boot que auto-configure cosas basadas en las dependencias (ej: base de datos, MVC).
 * 3. @ComponentScan: Busca otras anotaciones (@Component, @Service, @Controller, etc.) en este paquete y sus subpaquetes.
 */
@SpringBootApplication
public class EmpresaApplication {

    public static void main(String[] args) {
        // Arranca el contenedor de Spring y levanta el servidor Tomcat embebido
        SpringApplication.run(EmpresaApplication.class, args);
    }

}