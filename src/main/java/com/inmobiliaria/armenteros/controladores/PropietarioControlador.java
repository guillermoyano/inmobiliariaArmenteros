package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.entidades.Propietario;
import com.inmobiliaria.armenteros.servicios.PropietarioServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping("/registrar")
    public String registrar(ModelMap modelo){
       
        return "propietario_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long dni, @RequestParam(required = false) String nombreApellido, 
            @RequestParam(required = false) Long telefono, @RequestParam(required = false) String email, 
            @RequestParam(required = false) String direccion, ModelMap modelo, RedirectAttributes redirect){
            System.out.println("algo");
            
        try {
            propietarioServicio.crearPropietario(dni, nombreApellido, telefono, email, direccion);
            System.out.println("1");
            
        } catch (Exception ex) {
            List<Propietario>propietario = propietarioServicio.listarPropietarios();
            modelo.addAttribute("propietarios", propietario);
            Logger.getLogger(PropietarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("2");
            return "propietario_form.html";
        }
            System.out.println("3");
        return "propiedad_form.html";
        }
}
