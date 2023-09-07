
package com.inmobiliaria.armenteros.controladores;

import com.inmobiliaria.armenteros.entidades.Imagen;
import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.servicios.PropiedadServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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


    
    @GetMapping("/propiedad/{idPropiedad}")
    public ResponseEntity<List<Imagen>> obtenerImagenes(@PathVariable Integer idPropiedad){
        Propiedad propiedad = propiedadServicio.getone(idPropiedad);
        
       List<Imagen> imagenes= propiedad.getImagenes();
       
       HttpHeaders headers = new HttpHeaders();
       
       headers.setContentType(MediaType.APPLICATION_JSON);
        
       return new ResponseEntity<>(imagenes,headers, HttpStatus.OK); 
    }

}
