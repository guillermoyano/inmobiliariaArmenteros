package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Propietario;
import com.inmobiliaria.armenteros.excepciones.MiException;
import com.inmobiliaria.armenteros.repositorios.PropietarioRepositorio;
import com.inmobiliaria.armenteros.servicios.MailServicio;
import com.inmobiliaria.armenteros.servicios.PropietarioServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
@RequestMapping("/propietario")
public class PropietarioControlador {

    @Autowired
    PropietarioServicio propietarioServicio;
    @Autowired
    PropietarioRepositorio propietarioRepositorio;
    
    @Autowired
    MailServicio mailServicio;
    
    
    @GetMapping("/buscarPropietario")
    public String buscarPropietario(ModelMap modelo){
        return "buscarPropietario.html";
    }
    
    @PostMapping("/buscarPropietario")
    public String buscarPropietarioPorDni(@RequestParam Long dni, RedirectAttributes redirect, ModelMap modelo){
        Propietario propietario = propietarioServicio.buscarPorDni(dni);
        if(propietario !=null){
        return "redirect:../propietario/listaUnico/" + propietario.getIdPropietario() ;
        }else{
            return "propietario_form.html";
        }
    }
    
    
    @GetMapping("/registrar")
    public String registrar(ModelMap modelo, @PathVariable Long idPropietario){
        return "propietario_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long dni, @RequestParam(required = false) String nombreApellido, 
            @RequestParam(required = false) Long telefono, @RequestParam(required = false) String email, 
            @RequestParam(required = false) String direccion, ModelMap modelo, RedirectAttributes redirect){
        try {
            if(propietarioServicio.buscarPorDni(dni)==null){
                propietarioServicio.crearPropietario(dni, nombreApellido, telefono, email, direccion);
                Propietario propietario = propietarioServicio.buscarPorDni(dni);
                mailServicio.sendEmail(email);
                redirect.addFlashAttribute("exito", "salió todo bien");
                return "redirect:../propiedad/registrar/" +propietario.getIdPropietario();
            }
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombreApellido", nombreApellido);
            modelo.put("telefono", telefono);
            modelo.put("email", email);
            modelo.put("direccion", direccion);
            return "propietario_form.html";
        }
        return "redirect:../propiedad/registrar";
        }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo, @Param("keyword") Long keyword) {
        try {
            List<Propietario> propietarios = new ArrayList<>();

            if (keyword == null) {
                propietarioRepositorio.findAll().forEach(propietarios::add);
            } else {
                propietarioRepositorio. buscarPropietarioPorDni1(keyword).forEach(propietarios::add);
                modelo.addAttribute("keyword", keyword);
            }
            modelo.addAttribute("tutores", propietarios);
        } catch (Exception e) {
            modelo.addAttribute("error", e.getMessage());
        }
        return "propietario_list.html";

    }

    @GetMapping("/listaUnico/{idPropietario}")
    public String listarunico(ModelMap modelo, @PathVariable Long idPropietario) {
        Propietario propietarios = propietarioServicio.getone(idPropietario);
        modelo.addAttribute("propietarios", propietarios);

        return "propietario_unico.html";

    }
}
