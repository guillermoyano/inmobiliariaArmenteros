package com.inmobiliaria.armenteros.servicios;

import com.inmobiliaria.armenteros.entidades.Imagen;
import com.inmobiliaria.armenteros.excepciones.MiException;
import com.inmobiliaria.armenteros.repositorios.ImagenRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Guillote
 */
@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;

    @Transactional
    public Imagen crearImagen(MultipartFile archivo) throws MiException {

        if (archivo != null) {
            try {

                Imagen imagen = new Imagen();

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);
            } catch (Exception e) {

                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Transactional
    public Imagen modificarImagen(MultipartFile archivo, Integer idImagen) throws MiException {

        if (archivo != null) {
            try {

                Imagen imagen = new Imagen();

                if (idImagen != null) {
                    Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);

                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);

            } catch (Exception e) {

                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public Imagen getone(Integer idImagen) {
        return imagenRepositorio.getOne(idImagen);
    }

    @Transactional
    public List<byte[]> listarTodos() {

        List<byte[]> imagenes = imagenRepositorio.listaContenido();

        return imagenes;
    }
}
