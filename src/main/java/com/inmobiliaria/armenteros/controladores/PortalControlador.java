package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Imagen;
import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.entidades.Usuario;
import com.inmobiliaria.armenteros.excepciones.MiException;
import com.inmobiliaria.armenteros.repositorios.ImagenRepositorio;
import com.inmobiliaria.armenteros.repositorios.PropiedadRepositorio;
import com.inmobiliaria.armenteros.servicios.UsuarioServicio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Guillote
 */
@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private PropiedadRepositorio propiedadRepositorio;
    @Autowired
    private ImagenRepositorio imagenRepositorio;

    @GetMapping("/")
    public String index(ModelMap modelo) {

        return "redirect:./propiedad/listarPropiedades";
    }
    
      @GetMapping("/nosotros")
    public String nosotros() {

        return "nosotros.html";
    }

    @GetMapping("/casasPrincipal")
    public String casasPrincipal(ModelMap modelo) {

        List<Propiedad> propiedades = propiedadRepositorio.findAll();
        List<Imagen> imagenes = imagenRepositorio.findAll();

        Map<Integer, List<String>> imagenesPorPropiedad = new HashMap<>();

        for (Propiedad aux : propiedades) {
            Integer idPropiedad = aux.getIdPropiedad();
            List<String> imagen1 = new ArrayList<>();

            for (Imagen aux1 : imagenes) {
                if (aux1.getPropiedad().getIdPropiedad() == idPropiedad) {
                    byte[] foto = aux1.getContenido();
                    String base = Base64.getEncoder().encodeToString(foto);
                    imagen1.add(base);
                }
            }
            imagenesPorPropiedad.put(idPropiedad, imagen1);
        }
        modelo.addAttribute("propiedades", propiedades);
        modelo.addAttribute("imagenesPorPropiedad", imagenesPorPropiedad);
        return "casasPrincipal.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, String password2,
             ModelMap modelo, RedirectAttributes redirectAttributes) throws IOException, MiException {

        try {
            usuarioServicio.registrar(nombre, email, password, password2);
            redirectAttributes.addFlashAttribute("exito", "El usuario fue cargado correctamente!");

            return "redirect:/";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "registro.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o contraseña inválidos");
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, RedirectAttributes redirectAttributes) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if (logueado.getRol().toString().equals("ADMIN") || logueado.getRol().toString().equals("USER")) {
            redirectAttributes.addFlashAttribute("exito", "El usuario fue logeado correctamente!");
            return "redirect:./propiedad/listarPropiedades";
        }

        return "redirect:./propiedad/listarPropiedades";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        return "usuario_modificar.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/perfil/{id}")
    public String actualizar(@PathVariable String id, @RequestParam String nombre,
            @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo,
             RedirectAttributes redirectAttributes) {

        try {
            usuarioServicio.actualizar(id, nombre, email, password, password2);

            redirectAttributes.addFlashAttribute("exito", "El usuario fue actualizado correctamente!");

            return "redirect:/";

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "usuario_modificar.html";
        }
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.put("usuarios", usuarios);

        return "usuario_list.html";
    }

}
