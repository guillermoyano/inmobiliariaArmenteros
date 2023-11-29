package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Imagen;
import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.excepciones.MiException;
import com.inmobiliaria.armenteros.repositorios.ImagenRepositorio;
import com.inmobiliaria.armenteros.repositorios.PropiedadRepositorio;
import com.inmobiliaria.armenteros.repositorios.PropietarioRepositorio;
import com.inmobiliaria.armenteros.servicios.PropiedadServicio;
import com.inmobiliaria.armenteros.servicios.PropietarioServicio;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    ImagenRepositorio imagenRepositorio;

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
    public String registro(@RequestParam(required = false) Long mts2Totales, @RequestParam(required = false) Long mts2Cubiertos,
            @RequestParam(required = false) Long mts2Descubiertos, @RequestParam(required = false) String localidad,
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
            @RequestParam(required = false) Long idPropietario, List<MultipartFile> archivo, @RequestParam(required = false) Long precioPropiedad,
            @RequestParam(required = false) String moneda,
            RedirectAttributes redirect, ModelMap modelo) throws MiException {
        modelo.put("propietario", propietarioServicio.getone(idPropietario));
        try {
            propiedadServicio.crearPropiedad(mts2Totales, mts2Cubiertos, mts2Descubiertos, localidad, barrio, calle, descripcion, altura, cantBanios,
                    cantHabitaciones, estado, aguaCorriente, aireAcondicionado, aptoCredito, balcon, banio, aptoProfesional, cloacas, gasNatural,
                    permiteMascotas, salonJuegos, gimnasio, luz, pavimento, cocina, patio, quincho, sum, terraza, baulera, parrilla, cochera, pileta,
                    ascensor, lavadero, suite, vestidor, toillete, expensas, tipoVivienda, moneda, idPropietario, archivo, precioPropiedad);
            redirect.addFlashAttribute("exito", "La propiedad fue cargada correctamente");
            return "redirect:../";
        } catch (MiException ex) {
            redirect.addFlashAttribute("error", ex.getMessage());
            redirect.addFlashAttribute("mts2Totales", mts2Totales);
            redirect.addFlashAttribute("mts2Cubiertos", mts2Cubiertos);
            redirect.addFlashAttribute("mts2Descubiertos", mts2Descubiertos);
            redirect.addFlashAttribute("localidad", localidad);
            redirect.addFlashAttribute("barrio", barrio);
            redirect.addFlashAttribute("calle", calle);
            redirect.addFlashAttribute("altura", altura);
            redirect.addFlashAttribute("descripcion", descripcion);
            redirect.addFlashAttribute("cantBanios", cantBanios);
            redirect.addFlashAttribute("cantHabitaciones", cantHabitaciones);
            redirect.addFlashAttribute("precioPropiedad", precioPropiedad);

            return "redirect:../propiedad/registrar/" + idPropietario;
        }
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo, @Param("keyword") String keyword) {
        try {
            List<Propiedad> propiedades = new ArrayList<>();
            if (keyword == null) {
                propiedadRepositorio.ordenarPropiedadPorFechaDesc().forEach(propiedades::add);
            } else {
                propiedadRepositorio.buscarPropiedadPorCalle(keyword).forEach(propiedades::add);
                modelo.addAttribute("keyword", keyword);
            }
            modelo.addAttribute("propiedades", propiedades);
        } catch (Exception e) {
            modelo.addAttribute("error", e.getMessage());
        }
        return "propiedad_list1.html";

    }

    @GetMapping("/listarPropiedades")
    public String listarPropiedades(ModelMap modelo, @Param("keyword") String keyword, @Param("keyword1") String keyword1, @Param("keyword2") String keyword2,
            @Param("keyword3") String keyword3, @Param("keyword4") Long keyword4, @Param("keyword5") Long keyword5) {

        try {
            List<Propiedad> propiedades = new ArrayList<>();

            if (keyword == null) {
                propiedadRepositorio.ordenarPropiedadPorFechaDesc().forEach(propiedades::add);
                ImagenesPorPropiedad(modelo);
            } else {
                propiedadRepositorio.buscarPropiedadPotTipoDeVivienda(keyword, keyword1, keyword2, keyword3, keyword4, keyword5).forEach(propiedades::add);
                modelo.addAttribute("keyword", keyword);
                ImagenesPorPropiedad(modelo);
            }
            modelo.addAttribute("propiedades", propiedades);
        } catch (Exception e) {
            modelo.addAttribute("error", e.getMessage());
        }
        return "index.html";
    }

    public void ImagenesPorPropiedad(ModelMap modelo) {
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

    }

    @GetMapping("modificar/{idPropiedad}")
    public String modificar(@PathVariable Integer idPropiedad, ModelMap modelo) throws MiException {

        modelo.put("propiedad", propiedadServicio.getone(idPropiedad));
        Long algo = propiedadServicio.getone(idPropiedad).getPropietario().getIdPropietario();
        modelo.put("propietario", propietarioServicio.getone(algo));

        return "propiedad_modificar.html";
    }

    @PostMapping("modificar/{idPropiedad}")
    public String modificar(@PathVariable Integer idPropiedad, Long mts2Totales, Long mts2Cubiertos, Long mts2Descubiertos, String localidad, String barrio, String calle,
            String descripcion, Integer altura, Integer cantBanios, Integer cantHabitaciones, String estado, Boolean aguaCorriente, Boolean aireAcondicionado,
            Boolean aptoCredito, Boolean balcon, Boolean banio, Boolean aptoProfesional, Boolean cloacas, Boolean gasNatural, Boolean permiteMascotas, Boolean salonJuegos,
            Boolean gimnasio, Boolean luz, Boolean pavimento, Boolean cocina, Boolean patio, Boolean quincho, Boolean sum, Boolean terraza, Boolean baulera, Boolean parrilla,
            Boolean cochera, Boolean pileta, Boolean ascensor, Boolean lavadero, Boolean suite, Boolean vestidor, Boolean toillete, Boolean expensas, String tipoVivienda,
            Long precioPropiedad, String moneda, ModelMap modelo, RedirectAttributes redirect) throws MiException {
        try {
            propiedadServicio.modificarPropiedad(idPropiedad, mts2Totales, mts2Cubiertos, mts2Descubiertos, localidad, barrio, calle, descripcion, altura, cantBanios,
                    cantHabitaciones, estado, aguaCorriente, aireAcondicionado, aptoCredito, balcon, banio, aptoProfesional, cloacas, gasNatural, permiteMascotas,
                    salonJuegos, gimnasio, luz, pavimento, cocina, patio, quincho, sum, terraza, baulera, parrilla, cochera, pileta, ascensor, lavadero, suite,
                    vestidor, toillete, expensas, tipoVivienda, precioPropiedad, moneda);
            redirect.addFlashAttribute("exito", "Ha sido modificada correctamente.");
            return "redirect:../lista";
        } catch (MiException ex) {
            redirect.addFlashAttribute("error", ex.getMessage());
            return "propiedad_modificar.html";
        }
    }

    @GetMapping("eliminar/{idPropiedad}")
    public String eliminarPropiedad(@PathVariable Integer idPropiedad, RedirectAttributes redirect) throws MiException {
        propiedadServicio.eliminarPropiedad(idPropiedad);
        return "redirect:../lista";
    }

    @GetMapping("darDeBaja/{idPropiedad}")
    public String cambiarPropiedad(@PathVariable Integer idPropiedad, RedirectAttributes redirect) throws MiException {
        propiedadServicio.estadoPropiedad(idPropiedad);
        return "redirect:../lista";
    }

    @GetMapping("reservar/{idPropiedad}")
    public String reservaPropiedad(@PathVariable Integer idPropiedad, RedirectAttributes redirect) throws MiException {
        propiedadServicio.reservaPropiedad(idPropiedad);
        return "redirect:../lista";
    }

}
