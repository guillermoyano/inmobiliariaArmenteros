package com.inmobiliaria.armenteros.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tonna/SA FR34K
 */
/**/
@Service
public class MailServicio {

    @Autowired
    private JavaMailSender mailSender;

    String from = "armenterospropiedades@gmail.com";//dirección de correo que hace el envío.
    //String to = "pepehonguito@gmail.com";//dirección de correo que recibe el mail.

    public ResponseEntity<?> sendEmail(String to) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Asunto del correo");
        message.setText("Bienvenido a Propiedades Armenteros !!!");
        mailSender.send(message); //método Send(envio), propio de Java Mail Sender.

        System.out.println("Mail enviado con exito");
        
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);

    }

}//usar mailSender acá…

