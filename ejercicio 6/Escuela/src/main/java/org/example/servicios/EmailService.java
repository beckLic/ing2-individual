package org.example.servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Inyecta dependencias "final" automáticamente
public class EmailService {
    private final JavaMailSender mailSender;

    // Método asíncrono recomendado para no bloquear el registro
    @Async
    public void enviarCorreoBienvenida(String emailDestino, String nombre) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(emailDestino);
        mensaje.setSubject("Bienvenido al Sistema Académico");
        mensaje.setText("Hola " + nombre + ",\n\nTu cuenta como Profesor ha sido creada exitosamente. Ya puedes ingresar al sistema.\n\nSaludos.");
        mailSender.send(mensaje);
    }
}