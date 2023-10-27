package com.inmobiliaria.armenteros.servicios;

import com.inmobiliaria.armenteros.entidades.Imagen;
import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.entidades.Propietario;
import com.inmobiliaria.armenteros.excepciones.MiException;
import com.inmobiliaria.armenteros.repositorios.PropiedadRepositorio;
import com.inmobiliaria.armenteros.repositorios.PropietarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Guillote
 */
@Service
public class PropiedadServicio {

    @Autowired
    private PropiedadRepositorio propiedadRepositorio;
    @Autowired
    private PropietarioRepositorio propietarioRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearPropiedad(Long mts2Totales, Long mts2Cubiertos, Long mts2Descubiertos, String localidad, String barrio, String calle,
            String descripcion, Integer altura, Integer cantBanios, Integer cantHabitaciones, String estado, Boolean aguaCorriente, Boolean aireAcondicionado,
            Boolean aptoCredito, Boolean balcon, Boolean banio, Boolean aptoProfesional, Boolean cloacas, Boolean gasNatural, Boolean permiteMascotas, Boolean salonJuegos,
            Boolean gimnasio, Boolean luz, Boolean pavimento, Boolean cocina, Boolean patio, Boolean quincho, Boolean sum, Boolean terraza, Boolean baulera, Boolean parrilla,
            Boolean cochera, Boolean pileta, Boolean ascensor, Boolean lavadero, Boolean suite, Boolean vestidor, Boolean toillete, Boolean expensas, String tipoVivienda, String moneda, Long idPropietario,
            List<MultipartFile> archivo, Long precioPropiedad) throws MiException {

        validar(localidad, barrio, calle, descripcion, mts2Totales, mts2Cubiertos, mts2Descubiertos, altura, cantBanios, cantHabitaciones, estado, tipoVivienda, precioPropiedad, moneda);

        Optional<Propietario> respuestaPropietario = propietarioRepositorio.findById(idPropietario);

        Propietario propietario = new Propietario();

        if (respuestaPropietario.isPresent()) {
            propietario = respuestaPropietario.get();

            Propiedad propiedad = new Propiedad();

            propiedad.setPropietario(propietario);
            propiedad.setMts2Totales(mts2Totales);
            propiedad.setMts2Cubiertos(mts2Cubiertos);
            propiedad.setMts2Descubiertos(mts2Descubiertos);
            propiedad.setLocalidad(cambiarPrimeraLetraCadaPalabra(localidad));
            propiedad.setBarrio(cambiarPrimeraLetraCadaPalabra(barrio));
            propiedad.setCalle(cambiarPrimeraLetraCadaPalabra(calle));
            propiedad.setAltura(altura);
            propiedad.setEstado(estado);
            propiedad.setDescripcion(descripcion);
            propiedad.setCantBanios(cantBanios);
            propiedad.setCantHabitaciones(cantHabitaciones);
            propiedad.setFechaPublicacion(new Date());
            propiedad.setTipoVivienda(tipoVivienda);
            propiedad.setPrecioPropiedad(precioPropiedad);
            propiedad.setMoneda(moneda);

            if (aguaCorriente != null) {
                propiedad.setAguaCorriente(true);
            }
            if (aireAcondicionado != null) {
                propiedad.setAireAcondicionado(true);
            }
            if (aptoCredito != null) {
                propiedad.setAptoCredito(true);
            }
            if (aptoProfesional != null) {
                propiedad.setAptoProfesional(true);
            }
            if (balcon != null) {
                propiedad.setBalcon(true);
            }
            if (cloacas != null) {
                propiedad.setCloacas(true);
            }
            if (gasNatural != null) {
                propiedad.setGasNatural(true);
            }
            if (permiteMascotas != null) {
                propiedad.setPermiteMascotas(true);
            }
            if (salonJuegos != null) {
                propiedad.setSalonJuegos(true);
            }
            if (gimnasio != null) {
                propiedad.setGimnasio(true);
            }
            if (luz != null) {
                propiedad.setLuz(true);
            }
            if (pavimento != null) {
                propiedad.setPavimento(true);
            }
            if (banio != null) {
                propiedad.setBanio(true);
            }
            if (cocina != null) {
                propiedad.setCocina(true);
            }
            if (patio != null) {
                propiedad.setPatio(true);
            }
            if (quincho != null) {
                propiedad.setQuincho(true);
            }
            if (sum != null) {
                propiedad.setSum(true);
            }
            if (terraza != null) {
                propiedad.setTerraza(true);
            }
            if (baulera != null) {
                propiedad.setBaulera(true);
            }
            if (parrilla != null) {
                propiedad.setParrilla(true);
            }
            if (cochera != null) {
                propiedad.setCochera(true);
            }
            if (pileta != null) {
                propiedad.setPileta(true);
            }
            if (ascensor != null) {
                propiedad.setAscensor(true);
            }
            if (lavadero != null) {
                propiedad.setLavadero(true);
            }
            if (suite != null) {
                propiedad.setSuite(true);
            }
            if (vestidor != null) {
                propiedad.setVestidor(true);
            }
            if (toillete != null) {
                propiedad.setToillete(true);
            }
            if (expensas != null) {
                propiedad.setExpensas(true);
            }

            archivo.forEach(img -> {
                Imagen imagen;

                try {
                    imagen = imagenServicio.crearImagen(img);
                    imagen.setPropiedad(propiedad);
                    propiedad.agregarImagen(imagen);

                } catch (MiException ex) {
                    Logger.getLogger(PropiedadServicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            propiedadRepositorio.save(propiedad);

        }
    }

    public static String cambiarPrimeraLetraCadaPalabra(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }

        String[] palabras = texto.split(" ");
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < palabras.length; i++) {
            String palabra = palabras[i];
            if (!palabra.isEmpty()) {
                if (i == 0) {
                    resultado.append(palabra.substring(0, 1).toUpperCase());
                    resultado.append(palabra.substring(1).toLowerCase());
                } else if (palabra.length() == 2) {
                    resultado.append(palabra.toLowerCase());
                } else {
                    resultado.append(palabra.substring(0, 1).toUpperCase());
                    resultado.append(palabra.substring(1).toLowerCase());
                }
                resultado.append(" ");
            }
        }

        return resultado.toString().trim(); // Elimina el espacio en blanco al final.
    }

    public List<Propiedad> listarPropiedades() {

        List<Propiedad> propiedades = new ArrayList();

        propiedades = propiedadRepositorio.findAll();

        return propiedades;
    }

    @Transactional
    public void modificarPropiedad(Integer idPropiedad, Long mts2Totales, Long mts2Cubiertos, Long mts2Descubiertos, String localidad, String barrio, String calle,
            String descripcion, Integer altura, Integer cantBanios, Integer cantHabitaciones, String estado, Boolean aguaCorriente, Boolean aireAcondicionado,
            Boolean aptoCredito, Boolean balcon, Boolean banio, Boolean aptoProfesional, Boolean cloacas, Boolean gasNatural, Boolean permiteMascotas, Boolean salonJuegos,
            Boolean gimnasio, Boolean luz, Boolean pavimento, Boolean cocina, Boolean patio, Boolean quincho, Boolean sum, Boolean terraza, Boolean baulera, Boolean parrilla,
            Boolean cochera, Boolean pileta, Boolean ascensor, Boolean lavadero, Boolean suite, Boolean vestidor, Boolean toillete, Boolean expensas, String tipoVivienda,
            Long precioPropiedad, String moneda) throws MiException {

        validar(localidad, barrio, calle, descripcion, mts2Totales, mts2Cubiertos, mts2Descubiertos, altura, cantBanios, cantHabitaciones, estado, tipoVivienda, precioPropiedad, moneda);

        Optional<Propiedad> respuesta = propiedadRepositorio.findById(idPropiedad);

        if (respuesta.isPresent()) {

            Propiedad propiedad = respuesta.get();

            propiedad.setMts2Totales(mts2Totales);
            propiedad.setMts2Cubiertos(mts2Cubiertos);
            propiedad.setMts2Descubiertos(mts2Descubiertos);
            propiedad.setLocalidad(localidad);
            propiedad.setBarrio(barrio);
            propiedad.setCalle(calle);
            propiedad.setAltura(altura);
            propiedad.setEstado(estado);
            propiedad.setDescripcion(descripcion);
            propiedad.setTipoVivienda(tipoVivienda);
            propiedad.setMoneda(moneda);
            propiedad.setPrecioPropiedad(precioPropiedad);

            if (aguaCorriente != null) {
                propiedad.setAguaCorriente(true);
            } else {
                propiedad.setAguaCorriente(false);
            }
            if (aireAcondicionado != null) {
                propiedad.setAireAcondicionado(true);
            } else {
                propiedad.setAireAcondicionado(false);
            }
            if (aptoCredito != null) {
                propiedad.setAptoCredito(true);
            } else {
                propiedad.setAptoCredito(false);
            }
            if (aptoProfesional != null) {
                propiedad.setAptoProfesional(true);
            } else {
                propiedad.setAptoProfesional(false);
            }
            if (balcon != null) {
                propiedad.setBalcon(true);
            } else {
                propiedad.setBalcon(false);
            }
            if (cloacas != null) {
                propiedad.setCloacas(true);
            } else {
                propiedad.setCloacas(false);
            }
            if (gasNatural != null) {
                propiedad.setGasNatural(true);
            } else {
                propiedad.setGasNatural(false);
            }
            if (permiteMascotas != null) {
                propiedad.setPermiteMascotas(true);
            } else {
                propiedad.setPermiteMascotas(false);
            }
            if (salonJuegos != null) {
                propiedad.setSalonJuegos(true);
            } else {
                propiedad.setSalonJuegos(false);
            }
            if (gimnasio != null) {
                propiedad.setGimnasio(true);
            } else {
                propiedad.setGimnasio(false);
            }
            if (luz != null) {
                propiedad.setLuz(true);
            } else {
                propiedad.setLuz(false);
            }
            if (pavimento != null) {
                propiedad.setPavimento(true);
            } else {
                propiedad.setPavimento(false);
            }
            if (banio != null) {
                propiedad.setBanio(true);
            } else {
                propiedad.setBanio(false);
            }
            if (cocina != null) {
                propiedad.setCocina(true);
            } else {
                propiedad.setCocina(false);
            }
            if (patio != null) {
                propiedad.setPatio(true);
            } else {
                propiedad.setPatio(false);
            }
            if (quincho != null) {
                propiedad.setQuincho(true);
            } else {
                propiedad.setQuincho(false);
            }
            if (sum != null) {
                propiedad.setSum(true);
            } else {
                propiedad.setSum(false);
            }
            if (terraza != null) {
                propiedad.setTerraza(true);
            } else {
                propiedad.setTerraza(false);
            }
            if (baulera != null) {
                propiedad.setBaulera(true);
            } else {
                propiedad.setBaulera(false);
            }
            if (parrilla != null) {
                propiedad.setParrilla(true);
            } else {
                propiedad.setParrilla(false);
            }
            if (cochera != null) {
                propiedad.setCochera(true);
            } else {
                propiedad.setCochera(false);
            }
            if (pileta != null) {
                propiedad.setPileta(true);
            } else {
                propiedad.setPileta(false);
            }
            if (ascensor != null) {
                propiedad.setAscensor(true);
            } else {
                propiedad.setAscensor(false);
            }
            if (lavadero != null) {
                propiedad.setLavadero(true);
            } else {
                propiedad.setLavadero(false);
            }
            if (suite != null) {
                propiedad.setSuite(true);
            } else {
                propiedad.setSuite(false);
            }
            if (vestidor != null) {
                propiedad.setVestidor(true);
            } else {
                propiedad.setVestidor(false);
            }
            if (toillete != null) {
                propiedad.setToillete(true);
            } else {
                propiedad.setToillete(false);
            }
            if (expensas != null) {
                propiedad.setExpensas(true);
            } else {
                propiedad.setExpensas(false);
            }

            propiedadRepositorio.save(propiedad);
        }
    }

    @Transactional
    public void eliminarPropiedad(Integer idPropiedad) throws MiException {

        Optional<Propiedad> respuesta = propiedadRepositorio.findById(idPropiedad);

        if (respuesta.isPresent()) {
            imagenServicio.eliminarImagen(idPropiedad);
            Propiedad propiedad = respuesta.get();
            propiedadRepositorio.delete(propiedad);
        }
    }

    public void estadoPropiedad(Integer idPropiedad) throws MiException {

        Optional<Propiedad> respuesta = propiedadRepositorio.findById(idPropiedad);

        if (respuesta.isPresent()) {

            Propiedad propiedad = respuesta.get();
            if (propiedad.getEstadoComercial() != null) {
                if (propiedad.getEstadoComercial() == true) {
                    propiedad.setEstadoComercial(false);
                } else {
                    propiedad.setEstadoComercial(true);
                }
                propiedadRepositorio.save(propiedad);
            }
        }
    }
    

    public Propiedad getone(Integer idPropiedad) {
        return propiedadRepositorio.getOne(idPropiedad);
    }

    private void validar(String localidad, String barrio, String calle, String descripcion, Long mts2Totales, Long mts2Cubiertos, Long mts2Descubiertos, Integer altura, Integer cantBanios,
            Integer cantHabitaciones, String estado, String tipoVivienda, Long precioPropiedad, String moneda) throws MiException {

        if (tipoVivienda.isEmpty()) {
            throw new MiException("La localidad no puede estar vacia");
        }
        if (mts2Totales == null) {
            throw new MiException("La cantidad de mts2 totales, no puede ser nula");
        }
        if (mts2Cubiertos == null) {
            throw new MiException("La cantidad de mts2 cubiertos, no puede ser nula");
        }
        if (mts2Descubiertos == null) {
            throw new MiException("La cantidad de mts2 descubiertos, no puede ser nula");
        }
        if (localidad.isEmpty() || localidad == null) {
            throw new MiException("La localidad no puede estar vacia");
        }
        if (barrio.isEmpty() || barrio == null) {
            throw new MiException("El Barrio no puede estar vacio");
        }
        if (calle.isEmpty() || calle == null) {
            throw new MiException("La Calle no puede estar vacia");
        }
        if (altura == null) {
            throw new MiException("La Altura no puede estar vacia");
        }
        if (descripcion.isEmpty() || descripcion == null) {
            throw new MiException("La descripcion no puede ser nula o estar vacia");
        }
        if (cantBanios == null) {
            throw new MiException("no puede ser nula");
        }
        if (cantHabitaciones == null) {
            throw new MiException("no puede ser nula");
        }
        if (estado == null) {
            throw new MiException("El estado no pueden estar vacio");
        }
        if (moneda == null) {
            throw new MiException("Debe elegir un tipo de moneda");
        }
        if (precioPropiedad == null || precioPropiedad < 0) {
            throw new MiException("El precio de la propiedad no puede ser nula o ser menor a cero");
        }
    }

}
