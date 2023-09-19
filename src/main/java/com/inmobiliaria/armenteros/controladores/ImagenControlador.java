package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Imagen;
import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.repositorios.ImagenRepositorio;
import com.inmobiliaria.armenteros.servicios.ImagenServicio;
import com.inmobiliaria.armenteros.servicios.PropiedadServicio;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tonna/SA
 */
@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

    @Autowired
    PropiedadServicio propiedadServicio;
    @Autowired
    ImagenServicio imagenServicio;
    @Autowired
    ImagenRepositorio imagenRepositorio;

    @GetMapping("/listaDeImagenes")
    public String obtenerImagenes2(ModelMap modelo) {

        List<Imagen> imagenes = imagenRepositorio.findAll();
        List<String> imagen1 = new ArrayList<>();

        for (Imagen aux : imagenes) {
            byte[] foto = aux.getContenido();
            String base = Base64.getEncoder().encodeToString(foto);
            imagen1.add(base);
        }
        modelo.addAttribute("imagen1", imagen1);
        return "carruselImagenes.html";
    }

    @GetMapping("/propiedad/{idPropiedad}")
    public String listarImagenesPropiedad(ModelMap modelo, @PathVariable Integer idPropiedad) {
        Propiedad propiedad = propiedadServicio.getone(idPropiedad);
        List<Imagen> imagenes = imagenRepositorio.listaImagenes(idPropiedad);
        System.out.println("aca Ta!!");
        List<String> imagen1 = new ArrayList<>();

        for (Imagen aux : imagenes) {
            byte[] foto = aux.getContenido();
            String base = Base64.getEncoder().encodeToString(foto);
            imagen1.add(base);
        }
        modelo.addAttribute("imagen1", imagen1);
        modelo.addAttribute("propiedad", propiedad);
        return "carruselImagenes.html";
    }

}
