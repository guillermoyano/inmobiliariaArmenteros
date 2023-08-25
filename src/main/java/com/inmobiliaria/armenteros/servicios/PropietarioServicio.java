package com.inmobiliaria.armenteros.servicios;
import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.entidades.Propietario;
import com.inmobiliaria.armenteros.excepciones.MiException;
import com.inmobiliaria.armenteros.repositorios.PropiedadRepositorio;
import com.inmobiliaria.armenteros.repositorios.PropietarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guillote
 */
@Service
public class PropietarioServicio {

    @Autowired
    private PropietarioRepositorio propietarioRepositorio;
    @Autowired
    private PropiedadRepositorio propiedadRepositorio;

    @Transactional
    public void crearPropietario(Long dni, String nombreApellido, Long telefono, String email, String direccion, String idPropiedad, Propiedad propiedad) throws MiException{

        if(dni == null){
            throw new MiException ("El DNI debe ser ingresado");
        }
        
        Propietario propietario = new Propietario();
        propiedadRepositorio.findById(idPropiedad).get();
        Propiedad propiedades = new Propiedad();

        propietario.setDni(dni);
        propietario.setNombreApellido(nombreApellido);
        propietario.setEmail(email);
        propietario.setTelefono(telefono);
        propietario.setDirección(direccion);
        propietario.setPropiedad(propiedad);
        propietarioRepositorio.save(propietario);
    }

    public List<Propietario> listarPropietarios() {

        List<Propietario> propietarios = new ArrayList();
        propietarios = propietarioRepositorio.findAll();
        return propietarios;
    }

    @Transactional
    public void modificarPropietario(Long dni, String nombreApellido, Long telefono, String email, String direccion, String idPropiedad, Propiedad propiedad) throws MiException {

        validar(dni, nombreApellido, telefono, email, direccion);

        Optional<Propietario> respuesta = propietarioRepositorio.findById(dni);

        if (respuesta.isPresent()) {

            Propietario propietario = respuesta.get();

            propietario.setDni(dni);
            propietario.setNombreApellido(nombreApellido);
            propietario.setTelefono(telefono);
            propietario.setEmail(email);
            propietario.setDirección(direccion);
            propietario.setPropiedad(propiedad);

            propietarioRepositorio.save(propietario);
        }
    }

    @Transactional
    public void eliminarPropietario(Long dni) throws MiException {

        Optional<Propietario> respuesta = propietarioRepositorio.findById(dni);

        if (respuesta.isPresent()) {
            Propietario propietario = respuesta.get();
            propietarioRepositorio.delete(propietario);
        }
    }

    public Propietario getone(Long dni) {
        return propietarioRepositorio.getOne(dni);
    }

    private void validar(Long dni, String nombreApellido, Long telefono, String email, String direccion) throws MiException {

        if (dni == null || dni < 1000000) {
            throw new MiException("El DNI no puede ser nulo o estar vacío o tener menos de 7 dígitos");
        }
        if (nombreApellido.isEmpty() || nombreApellido == null) {
            throw new MiException("Debe completar correctamente el nombre");
        }
        if (telefono == null || telefono < 1120000000) {
            throw new MiException("El DNI no puede ser nulo o estar vacío o tener menos de 7 dígitos");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("Debe completar correctamente el correo electrónico");
        }
        if (direccion.isEmpty() || direccion == null) {
            throw new MiException("Debe completar correctamente la dirección");
        }

    }

}
