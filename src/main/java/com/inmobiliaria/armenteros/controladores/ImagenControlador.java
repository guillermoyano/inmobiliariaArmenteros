
package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Imagen;
import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.repositorios.ImagenRepositorio;
import com.inmobiliaria.armenteros.servicios.ImagenServicio;
import com.inmobiliaria.armenteros.servicios.PropiedadServicio;
import java.util.ArrayList;
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


    
//    @GetMapping("/propiedad/{idPropiedad}")
//    public ResponseEntity<List<Imagen>> obtenerImagenes(@PathVariable Integer idPropiedad){
//        Propiedad propiedad = propiedadServicio.getone(idPropiedad);
//        
//       List<Imagen> imagenes= propiedad.getImagenes();
//       
//       HttpHeaders headers = new HttpHeaders();
//       
//       headers.setContentType(MediaType.APPLICATION_JSON);
//        
//       return new ResponseEntity<>(imagenes,headers, HttpStatus.OK); 
//    }
//@GetMapping("/perfil/{id}")
//    public ResponseEntity<byte[]> imagenUsuario (@PathVariable Integer idPropiedad){
//        Propiedad propiedad = propiedadServicio.getone(idPropiedad);
//        
//       byte[] imagen= propiedad.getImagenes().toArray();
//       
//       HttpHeaders headers = new HttpHeaders();
//       
//       headers.setContentType(MediaType.IMAGE_JPEG);
//       
//        
//        
//       return new ResponseEntity<>(imagen,headers, HttpStatus.OK); 
//    }
    
    @GetMapping("/propiedad/{idPropiedad}")
    public ResponseEntity <byte[]> obtenerImagenes1(@PathVariable Integer idPropiedad){
        Propiedad propiedad = propiedadServicio.getone(idPropiedad);
        
       byte[] imagen= propiedad.getImagenes().get(1).getContenido();
       
       HttpHeaders headers = new HttpHeaders();
       
       headers.setContentType(MediaType.APPLICATION_JSON);
       
        
        
       return new ResponseEntity<>(imagen,headers, HttpStatus.OK); 
    }
    
    @GetMapping("/lista")
    public String listarImagenes(ModelMap modelo){
        try {
//            List<byte[]>imagenes1 = new ArrayList<>();
             List<Imagen> imagenes = imagenServicio.listarTodos();
            modelo.addAttribute("imagenes", imagenes);
        } catch (Exception e) {
            modelo.addAttribute("error", e.getMessage());
        }
        return "imagenes_list.html";
    }
    
//    @GetMapping("/lista")
//    public ResponseEntity<List<Imagen>> obtenerImagenes(ModelMap modelo){
//        
//       List<Imagen> imagenes= imagenServicio.listarTodos();
//       
//       modelo.getAttribute("imagenes");
//       
//       HttpHeaders headers = new HttpHeaders();
//       
//       headers.setContentType(MediaType.APPLICATION_JSON);
//        
//       return new ResponseEntity<>(imagenes,headers, HttpStatus.OK); 
//    }


}

