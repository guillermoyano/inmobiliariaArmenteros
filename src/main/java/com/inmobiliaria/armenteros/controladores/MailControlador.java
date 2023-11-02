
package com.inmobiliaria.armenteros.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Tonna/SA FR34K
 */
/**/
@Controller
@RequestMapping("/mail")
public class MailControlador {


@Autowired
private JavaMailSender mailSender;


@GetMapping
public String sendEmail() {

return "send_mail";

}

@PostMapping("/enviar-correo")
    public String enviarCorreo(@RequestParam String nombre, @RequestParam String email, @RequestParam String asunto, @RequestParam String mensaje, RedirectAttributes redirect) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("propiedadesarmenteros@gmail.com"); // Reemplaza con tu dirección de correo electrónico
        mail.setSubject(asunto);
        mail.setText("De: " + nombre + "\nCorreo: " + email + "\nMensaje: " + mensaje);

        mailSender.send(mail);

        // Puedes redirigir al usuario a una página de confirmación
        return "redirect:/";
    }
}


