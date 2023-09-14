package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Imagen;
import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.repositorios.ImagenRepositorio;
import com.inmobiliaria.armenteros.servicios.ImagenServicio;
import com.inmobiliaria.armenteros.servicios.PropiedadServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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

//    @GetMapping("/lista/{idPropiedad}")
//    public ResponseEntity<List<byte[]>> obtenerImagenes(@PathVariable Integer idPropiedad){
//        Propiedad propiedad = propiedadServicio.getone(idPropiedad);
//        List<Imagen>imagen = imagenRepositorio.findAll();
//       List<byte[]> imagenes= new ArrayList<>();
//       
//        for (Imagen aux : propiedad.getImagenes()) {
//            imagenes.add(aux.getContenido());
//        }
//       
//       HttpHeaders headers = new HttpHeaders();
//      
//       headers.setContentType(MediaType.APPLICATION_JSON);
//       
//       return new ResponseEntity<>(imagenes,headers, HttpStatus.OK); 
//    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<Imagen>> obtenerImagenes(){
        
       List<Imagen> imagenes= imagenRepositorio.findAll();
       
       
       HttpHeaders headers = new HttpHeaders();
       
       headers.setContentType(MediaType.APPLICATION_JSON);
        
       return new ResponseEntity<>(imagenes,headers, HttpStatus.OK); 
    }
    
    
//    @GetMapping("/lista")
//    public String ListarImagenes(ModelMap modelo) {
//        List<Imagen> imagenes = imagenRepositorio.findAll();
//        modelo.addAttribute("imagenes", imagenes);
//        return "imagenes_list.html";
//    }
    
//    @GetMapping("/lista")
//    public ResponseEntity<List<byte[]>> obtener(){
//       
//        List<byte[]> imagenes= imagenServicio.listarTodos();
//       
//       
//       HttpHeaders headers = new HttpHeaders();
//      
//       headers.setContentType(MediaType.APPLICATION_JSON);
//       
//       return new ResponseEntity<>(imagenes,headers, HttpStatus.OK); 
//    }
    
    
//    @GetMapping("/lista")
//    public String listar(ModelMap modelo, @Param("keyword") String keyword) {
//        try {
//            List<Propiedad> propiedades = new ArrayList<>();
//            if (keyword == null) {
//                propiedadRepositorio.findAll().forEach(propiedades::add);
//            } else {
//                propiedadRepositorio.buscarPropiedadPorCalle(keyword).forEach(propiedades::add);
//                modelo.addAttribute("keyword", keyword);
//            }
//            modelo.addAttribute("propiedades", propiedades);
//        } catch (Exception e) {
//            modelo.addAttribute("error", e.getMessage());
//        }
//        return "propiedad_list.html";
//
//    }
    
    
    @GetMapping("/propiedad/{idPropiedad}")
    public ResponseEntity<byte[]> obtenerImagenes1(@PathVariable Integer idPropiedad) {
        Propiedad propiedad = propiedadServicio.getone(idPropiedad);

        byte[] imagen = propiedad.getImagenes().get(1).getContenido();

        System.out.println(propiedad.getImagenes().size());

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

//    @GetMapping("/lista")
//    public List<Imagen> obtenerImagenes(ModelMap modelo) {
//        Propiedad propiedad = propiedadServicio.getone(1);
//        List<Imagen> imagenes = new ArrayList<>();
//
//        for (Imagen imagen : propiedad.getImagenes()) {
//             Imagen image = new Imagen();
//            image.setNombre(imagen.getNombre());
//            image.setContenido(imagen.getContenido());
//            
//            
//            imagenes.add(image);
//        }
//
////        modelo.addAttribute("imagenes", imagenes);
//
//        return imagenes;
//    }

//    @GetMapping("/lista")
//    public String listarImagenes(ModelMap modelo) {
//        List<Imagen> imagenes = imagenRepositorio.findAll();
//
//        modelo.addAttribute("imagenes", imagenes);
//
//        return "imagenes_list.html";
//    }

}
