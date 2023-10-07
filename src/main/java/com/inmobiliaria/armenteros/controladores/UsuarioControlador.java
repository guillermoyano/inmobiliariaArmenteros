
package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Usuario;
import com.inmobiliaria.armenteros.enumeraciones.Rol;
import com.inmobiliaria.armenteros.excepciones.MiException;
import com.inmobiliaria.armenteros.repositorios.UsuarioRepositorio;
import com.inmobiliaria.armenteros.servicios.UsuarioServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Tonna/SA
 */

@Controller
@RequestMapping("/usuario")

public class UsuarioControlador {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    UsuarioServicio usuarioServicio;
    
    @GetMapping("/listarUsuarios")
    public String listadoDeUsuarios (ModelMap modelo){
        
        List<Usuario>usuarios = usuarioRepositorio.findAll();
        modelo.put("usuarios", usuarios);
        return "usuarios_list.html";
    }
    
   @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id, RedirectAttributes redirect) {
        try {
            usuarioServicio.cambiarRol(id);
//            redirectAttributes.addFlashAttribute("success", "El usuario con id=" + id + " ha sido modificado correctamente!");
        } catch (MiException e) {
            System.out.println("hola");
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/listarUsuarios";
        }
        return "redirect:/usuario/listarUsuarios";
    }
}
