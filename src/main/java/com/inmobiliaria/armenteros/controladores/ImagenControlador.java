package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Imagen;
import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.excepciones.MiException;
import com.inmobiliaria.armenteros.repositorios.ImagenRepositorio;
import com.inmobiliaria.armenteros.servicios.ImagenServicio;
import com.inmobiliaria.armenteros.servicios.PropiedadServicio;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/listaDeImagenes/{idPropiedad}")
    public String obtenerImagenes2(ModelMap modelo, Integer idPropiedad) {

        List<Imagen> imagenes = imagenRepositorio.listaImagenes(idPropiedad);
        List<String> imagen1 = new ArrayList<>();

        for (Imagen aux : imagenes) {
            byte[] foto = aux.getContenido();
            String base = Base64.getEncoder().encodeToString(foto);
            imagen1.add(base);
        }
        modelo.addAttribute("imagen1", imagen1);
        return "carruselImagenes.html";
    }

//    @GetMapping("/listaDeImagenes/{idPropiedad}")
//    public String obtenerImagenesPropiedad(ModelMap modelo, Integer idPropiedad) {
//
//        List<Imagen> imagenes = imagenRepositorio.listaImagenes(idPropiedad);
//        List<String> imagen1 = new ArrayList<>();
//
//        for (Imagen aux : imagenes) {
//            byte[] foto = aux.getContenido();
//            String base = Base64.getEncoder().encodeToString(foto);
//            imagen1.add(base);
//        }
//        modelo.addAttribute("imagen1", imagen1);
//        return "carruselImagenes.html";
//    }

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
        return "detallePropiedad.html";
    }

    @GetMapping("/propiedad1/{idPropiedad}")
    public ResponseEntity<byte[]> obtenerImagenes1(@PathVariable Integer idPropiedad) {

        List<Imagen> imagen = imagenRepositorio.listaImagenes(idPropiedad);

        byte[] foto = imagen.get(0).getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println(foto);
        return new ResponseEntity<>(foto, headers, HttpStatus.OK);

    }

    @GetMapping("/modificar/{idPropiedad}")
    public String modificar(@PathVariable Integer idPropiedad, ModelMap modelo) throws MiException {

        modelo.put("propiedad", propiedadServicio.getone(idPropiedad));
        Propiedad propiedad = propiedadServicio.getone(idPropiedad);
        List<Imagen> imagenes = imagenRepositorio.listaImagenes(idPropiedad);
        System.out.println("aca Ta!!");
        List<String> imagen1 = new ArrayList<>();

        for (Imagen aux : imagenes) {
            byte[] foto = aux.getContenido();
            String base = Base64.getEncoder().encodeToString(foto);
            Integer idImagen = aux.getIdImagen();
            imagen1.add(base);
        }
        modelo.addAttribute("imagen1", imagen1);
        modelo.addAttribute("imagenes", imagenes);
        modelo.addAttribute("propiedad", propiedad);
        return "imagenes_modificar.html";
    }

    @GetMapping("/modificar1/{idImagen}")
    public String modificarImagen(@PathVariable Integer idImagen, ModelMap modelo, RedirectAttributes redirect) throws MiException {

        Imagen imagen = imagenServicio.getone(idImagen);
        byte[] foto = imagen.getContenido();
        String base = Base64.getEncoder().encodeToString(foto);
        modelo.put("base", base);
        System.out.println("salida del get");
        return "modificarImagenPropiedad.html";
    }

    @PostMapping("/modificar1/{idImagen}")
    public String modificarImagen1(@PathVariable Integer idImagen, MultipartFile archivo, ModelMap modelo, RedirectAttributes redirect) throws MiException {

        Integer idPropiedad = imagenServicio.getone(idImagen).getPropiedad().getIdPropiedad();
        System.out.println("algo" + idPropiedad);
        try {
            imagenServicio.modificarImagen(archivo, idImagen);
            redirect.addFlashAttribute("exito", "La imágen ha sido modificada correctamente.");
            
        } catch (MiException ex) {
            redirect.addFlashAttribute("error", ex.getMessage());
            return "imagenes_modificar.html";

        }

       return "redirect:/imagen/modificar/" + idPropiedad;
    }
//    @PostMapping("/modificar/{idPropiedad}")
//    public String modificar(@PathVariable Integer idPropiedad, MultipartFile archivo, ModelMap modelo, RedirectAttributes redirect) throws MiException {
//        try {
//            // 1. Obtén todas las imágenes relacionadas con la propiedad
//            List<Imagen> imagenesPropiedad = imagenRepositorio.listaImagenes(idPropiedad); // Reemplaza con el método adecuado
//
//            // 2. Itera a través de las imágenes y modifícalas
//            for (Imagen imagen : imagenesPropiedad) {
//                imagenServicio.modificarImagen(archivo, imagen.getPropiedad().getIdPropiedad()); // Asume que tienes un método para modificar una imagen específica
//            }
//
//            redirect.addFlashAttribute("exito", "Todas las imágenes han sido modificadas correctamente.");
//            return "redirect:/";
//        } catch (MiException ex) {
//            redirect.addFlashAttribute("error", ex.getMessage());
//            return "imagenes_modificar.html";
//        }
//    }

}
