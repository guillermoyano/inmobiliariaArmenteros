package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Imagen;
import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.entidades.Propietario;
import com.inmobiliaria.armenteros.repositorios.PropiedadRepositorio;
import com.inmobiliaria.armenteros.repositorios.PropietarioRepositorio;
import com.inmobiliaria.armenteros.servicios.PropiedadServicio;
import com.inmobiliaria.armenteros.servicios.PropietarioServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    PropiedadRepositorio propiedadRepositorio;
    @Autowired
    PropietarioServicio propietarioServicio;
    @Autowired
    PropietarioRepositorio propietarioRepositorio;

    @GetMapping("/registrar/{idPropietario}")
    public String registrar(ModelMap modelo, @PathVariable Long idPropietario) {

        modelo.put("propietario", propietarioServicio.getone(idPropietario));
        return "propiedad_form.html";
    }

    @GetMapping("/registrarUno")
    public String registrarUno(ModelMap modelo) {

        modelo.put("propietario", propietarioRepositorio.buscarPropietarioPorId());
        return "propiedad_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Double mts2Totales, @RequestParam(required = false) Double mts2Cubiertos,
            @RequestParam(required = false) Double mts2Descubiertos, @RequestParam(required = false) String localidad,
            @RequestParam(required = false) String barrio, @RequestParam(required = false) String calle,
            @RequestParam(required = false) String descripcion, @RequestParam(required = false) Integer altura,
            @RequestParam(required = false) Integer cantBanios, @RequestParam(required = false) Integer cantHabitaciones,
            @RequestParam(required = false) String estado, @RequestParam(required = false) Boolean aguaCorriente,
            @RequestParam(required = false) Boolean aireAcondicionado, @RequestParam(required = false) Boolean aptoCredito,
            @RequestParam(required = false) Boolean balcon, @RequestParam(required = false) Boolean banio,
            @RequestParam(required = false) Boolean aptoProfesional, @RequestParam(required = false) Boolean cloacas,
            @RequestParam(required = false) Boolean gasNatural, @RequestParam(required = false) Boolean permiteMascotas,
            @RequestParam(required = false) Boolean salonJuegos, @RequestParam(required = false) Boolean gimnasio,
            @RequestParam(required = false) Boolean luz, @RequestParam(required = false) Boolean pavimento,
            @RequestParam(required = false) Boolean cocina, @RequestParam(required = false) Boolean patio,
            @RequestParam(required = false) Boolean quincho, @RequestParam(required = false) Boolean sum,
            @RequestParam(required = false) Boolean terraza, @RequestParam(required = false) Boolean baulera,
            @RequestParam(required = false) Boolean parrilla, @RequestParam(required = false) Boolean cochera,
            @RequestParam(required = false) Boolean pileta, @RequestParam(required = false) Boolean ascensor,
            @RequestParam(required = false) Boolean lavadero, @RequestParam(required = false) Boolean suite,
            @RequestParam(required = false) Boolean vestidor, @RequestParam(required = false) Boolean toillete,
            @RequestParam(required = false) Boolean expensas, @RequestParam(required = false) String tipoVivienda,
            @RequestParam(required = false) Long idPropietario, List <MultipartFile> archivo, @RequestParam(required = false) Long precioPropiedad,
            RedirectAttributes redirect, ModelMap modelo) {
        System.out.println("sssss");
        try {
            propiedadServicio.crearPropiedad(mts2Totales, mts2Cubiertos, mts2Descubiertos,
                    localidad, barrio, calle, descripcion, altura, cantBanios, cantHabitaciones,
                    estado, aguaCorriente, aireAcondicionado, aptoCredito, balcon, banio, aptoProfesional,
                    cloacas, gasNatural, permiteMascotas, salonJuegos, gimnasio, luz, pavimento, cocina,
                    patio, quincho, sum, terraza, baulera, parrilla, cochera, pileta, ascensor, lavadero, suite,
                    vestidor, toillete, expensas, tipoVivienda, idPropietario, archivo, precioPropiedad);
//            List<Propietario> propietarios = propietarioServicio.listarPropietarios();
//            redirect.addAttribute("propietarios", propietarios);
            redirect.addFlashAttribute("exito", "La propiedad fue cargada correctamente");

            System.out.println("2sssss");

        } catch (Exception ex) {
//            List<Propiedad> propiedades = propiedadServicio.listarPropiedades();
//            modelo.addAttribute("propiedades", propiedades);
            Logger.getLogger(PropiedadControlador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("4sssss");
            return "propiedad_form.html";

        }
        System.out.println("3sssss");
        return "redirect:/";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo, @Param("keyword") String keyword) {
        try {
            List<Propiedad> propiedades = new ArrayList<>();
            if (keyword == null) {
                propiedadRepositorio.findAll().forEach(propiedades::add);
            } else {
                propiedadRepositorio.buscarPropiedadPorCalle(keyword).forEach(propiedades::add);
                modelo.addAttribute("keyword", keyword);
            }
            modelo.addAttribute("propiedades", propiedades);
        } catch (Exception e) {
            modelo.addAttribute("error", e.getMessage());
        }
        return "propiedad_list.html";

    }
}
