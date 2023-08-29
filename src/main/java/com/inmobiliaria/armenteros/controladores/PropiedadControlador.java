package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.entidades.Propietario;
import com.inmobiliaria.armenteros.repositorios.PropietarioRepositorio;
import com.inmobiliaria.armenteros.servicios.PropiedadServicio;
import com.inmobiliaria.armenteros.servicios.PropietarioServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/propiedad")
public class PropiedadControlador {

    @Autowired
    PropiedadServicio propiedadServicio;
    @Autowired
    PropietarioServicio propietarioServicio;
    @Autowired
    PropietarioRepositorio propietarioRepositorio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo, Long idPropietario) {
        
        modelo.put("propietario", propietarioRepositorio.buscarPropietarioPordni());
        return "propiedad_form.html";
    }
    

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Double mts2Totales, @RequestParam(required = false) Double mts2Cubiertos, @RequestParam(required = false) Double mts2Descubiertos,
            @RequestParam(required = false) String localidad, @RequestParam(required = false) String barrio, @RequestParam(required = false) String calle, @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) Integer altura, @RequestParam(required = false) Integer cantBanios, @RequestParam(required = false) Integer cantHabitaciones,
            @RequestParam(required = false) String estado, @RequestParam(required = false) Boolean aguaCorriente, @RequestParam(required = false) Boolean aireAcondicionado,
            @RequestParam(required = false) Boolean aptoCredito, @RequestParam(required = false) Boolean balcon, @RequestParam(required = false) Boolean banio, @RequestParam(required = false) Boolean aptoProfesional,
            @RequestParam(required = false) Boolean cloacas, @RequestParam(required = false) Boolean gasNatural, @RequestParam(required = false) Boolean permiteMascotas, @RequestParam(required = false) Boolean salonJuegos,
            @RequestParam(required = false) Boolean gimnasio, @RequestParam(required = false) Boolean luz, @RequestParam(required = false) Boolean pavimento, @RequestParam(required = false) Boolean cocina,
            @RequestParam(required = false) Boolean patio, @RequestParam(required = false) Boolean quincho, @RequestParam(required = false) Boolean sum, @RequestParam(required = false) Boolean terraza,
            @RequestParam(required = false) Boolean baulera, @RequestParam(required = false) Boolean parrilla, @RequestParam(required = false) Boolean cochera, @RequestParam(required = false) Boolean pileta,
            @RequestParam(required = false) Boolean ascensor, @RequestParam(required = false) Boolean lavadero, @RequestParam(required = false) Boolean suite, @RequestParam(required = false) Boolean vestidor,
            @RequestParam(required = false) Boolean toillete, @RequestParam(required = false) Boolean expensas, @RequestParam(required = false) String tipoVivienda, ModelMap modelo, @RequestParam(required = false) Long idPropietario, RedirectAttributes redirect) {

        try {
            propiedadServicio.crearPropiedad(mts2Totales, mts2Cubiertos, mts2Descubiertos, localidad, barrio, calle,
                    descripcion, altura, cantBanios, cantHabitaciones, estado, aguaCorriente, aireAcondicionado,
                    aptoCredito, balcon, banio, aptoProfesional, cloacas, gasNatural, permiteMascotas, salonJuegos,
                    gimnasio, luz, pavimento, cocina, patio, quincho, sum, terraza, baulera, parrilla, cochera,
                    pileta, ascensor, lavadero, suite, vestidor, toillete, expensas, tipoVivienda, idPropietario);
            redirect.addFlashAttribute("exito", "La propiedad fue cargada correctamente");

        } catch (Exception ex) {
            List<Propiedad> propiedades = propiedadServicio.listarPropiedades();
            modelo.addAttribute("propiedades", propiedades);
            Logger.getLogger(PropiedadControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "propiedad_form.html";
        }
        System.out.println("3");
        return "index.html";
    }

}
