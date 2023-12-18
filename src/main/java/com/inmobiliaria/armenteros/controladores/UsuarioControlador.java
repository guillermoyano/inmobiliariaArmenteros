package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Usuario;
import com.inmobiliaria.armenteros.excepciones.MiException;
import com.inmobiliaria.armenteros.repositorios.UsuarioRepositorio;
import com.inmobiliaria.armenteros.servicios.UsuarioServicio;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Tonna/SA
 */
@Controller
@RequestMapping("/usuario")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UsuarioControlador {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/crearUsuario")
    public String crearUsuario() {

        return "registro.html";
    }

    @PostMapping("crearUsuario")
    public String crearUsuario(String nombre, String email, String password, String password2, RedirectAttributes redirect) throws IOException {

        try {
            usuarioServicio.registrar(nombre, email, password, password2);
            redirect.addFlashAttribute("exito", "El usuario ha sido cargado correctamente");

        } catch (MiException e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "registro.html";
        }
        return "redirect:../propiedad/listarPropiedades";
    }

    @GetMapping("/listarUsuarios")
    public String listadoDeUsuarios(ModelMap modelo) {

        List<Usuario> usuarios = usuarioRepositorio.findAll();
        modelo.put("usuarios", usuarios);
        return "usuarios_list.html";
    }

    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id, RedirectAttributes redirect) {
        try {
            usuarioServicio.cambiarRol(id);
//            redirectAttributes.addFlashAttribute("success", "El usuario con id=" + id + " ha sido modificado correctamente!");
        } catch (MiException e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/listarUsuarios";
        }
        return "redirect:/usuario/listarUsuarios";
    }
}
