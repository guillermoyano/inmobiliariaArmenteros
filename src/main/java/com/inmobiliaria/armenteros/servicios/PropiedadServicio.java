package com.inmobiliaria.armenteros.servicios;

import com.inmobiliaria.armenteros.entidades.Propiedad;
import com.inmobiliaria.armenteros.entidades.Propietario;
import com.inmobiliaria.armenteros.repositorios.PropiedadRepositorio;
import com.inmobiliaria.armenteros.repositorios.PropietarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
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
public class PropiedadServicio {

    @Autowired
    private PropiedadRepositorio propiedadRepositorio;
     @Autowired
    private PropietarioRepositorio propietarioRepositorio;
     
@Transactional
    public void crearPropiedad(Double mts2Totales, Double mts2Cubiertos, Double mts2Descubiertos, String localidad, String barrio, String calle,
            String descripcion, Integer altura, Integer cantBanios, Integer cantHabitaciones, String estado, Boolean aguaCorriente, Boolean aireAcondicionado,
            Boolean aptoCredito, Boolean balcon, Boolean banio, Boolean aptoProfesional, Boolean cloacas, Boolean gasNatural, Boolean permiteMascotas, Boolean salonJuegos,
            Boolean gimnasio, Boolean luz, Boolean pavimento, Boolean cocina, Boolean patio, Boolean quincho, Boolean sum, Boolean terraza, Boolean baulera, Boolean parrilla,
            Boolean cochera, Boolean pileta, Boolean ascensor, Boolean lavadero, Boolean suite, Boolean vestidor, Boolean toillete, Boolean expensas, String tipoVivienda, Long idPropietario) throws Exception {

        validar(localidad, barrio, calle, descripcion, mts2Totales, mts2Cubiertos, mts2Descubiertos, altura, cantBanios, cantHabitaciones, estado, tipoVivienda);

        Optional<Propietario> respuestaPropietario = propietarioRepositorio.findById(idPropietario);

        Propietario propietario = new Propietario();

        if (respuestaPropietario.isPresent()) {
            propietario = respuestaPropietario.get();
        }
        
        
        
        Propiedad propiedad = new Propiedad();

        propiedad.setPropietario(propietario);
        propiedad.setMts2Totales(mts2Totales);
        propiedad.setMts2Cubiertos(mts2Cubiertos);
        propiedad.setMts2Descubiertos(mts2Descubiertos);
        propiedad.setLocalidad(localidad);
        propiedad.setBarrio(barrio);
        propiedad.setCalle(calle);
        propiedad.setAltura(altura);
        propiedad.setEstado(estado);
        propiedad.setDescripcion(descripcion);
        propiedad.setCantBanios(cantBanios);
        propiedad.setCantHabitaciones(cantHabitaciones);
        propiedad.setFechaPublicacion(new Date());
        propiedad.setTipoVivienda(tipoVivienda);

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

        propiedadRepositorio.save(propiedad);
    }

    public List<Propiedad> listarPropiedades() {

        List<Propiedad> propiedades = new ArrayList();

        propiedades = propiedadRepositorio.findAll();

        return propiedades;
    }

    @Transactional
    public void modificarPropiedad(String idPropiedad, Double mts2Totales, Double mts2Cubiertos, Double mts2Descubiertos, String localidad, String barrio, String calle,
            String descripcion, Integer altura, Integer cantBanios, Integer cantHabitaciones, Date fechaPublicacion, String estado, Boolean aguaCorriente, Boolean aireAcondicionado,
            Boolean aptoCredito, Boolean balcon, Boolean banio, Boolean aptoProfesional, Boolean cloacas, Boolean gasNatural, Boolean permiteMascotas, Boolean salonJuegos,
            Boolean gimnasio, Boolean luz, Boolean pavimento, Boolean cocina, Boolean patio, Boolean quincho, Boolean sum, Boolean terraza, Boolean baulera, Boolean parrilla,
            Boolean cochera, Boolean pileta, Boolean ascensor, Boolean lavadero, Boolean suite, Boolean vestidor, Boolean toillete, Boolean expensas, String tipoVivienda) throws Exception {

        validar(localidad, barrio, calle, descripcion, mts2Totales, mts2Cubiertos, mts2Descubiertos, altura, cantBanios, cantHabitaciones, estado, tipoVivienda);

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
            if (aguaCorriente != null) {
                propiedad.setAguaCorriente(true);
            } else {
                propiedad.setAguaCorriente(false);
            }
            propiedad.setAireAcondicionado(aireAcondicionado);
            propiedad.setAptoCredito(aptoCredito);
            propiedad.setAptoProfesional(aptoProfesional);
            propiedad.setCloacas(cloacas);
            propiedad.setGasNatural(gasNatural);
            propiedad.setPermiteMascotas(permiteMascotas);
            propiedad.setSalonJuegos(salonJuegos);
            propiedad.setGimnasio(gimnasio);
            propiedad.setLuz(luz);
            propiedad.setPavimento(pavimento);
            propiedad.setFechaPublicacion(new Date());
            propiedad.setBanio(banio);
            propiedad.setCocina(cocina);
            propiedad.setPatio(patio);
            propiedad.setQuincho(quincho);
            propiedad.setSum(sum);
            propiedad.setTerraza(terraza);
            propiedad.setBaulera(baulera);
            propiedad.setParrilla(parrilla);
            propiedad.setCochera(cochera);
            propiedad.setPileta(pileta);
            propiedad.setAscensor(ascensor);
            propiedad.setLavadero(lavadero);
            propiedad.setSuite(suite);
            propiedad.setVestidor(vestidor);
            propiedad.setToillete(toillete);
            propiedad.setCantBanios(cantBanios);
            propiedad.setExpensas(expensas);
            propiedad.setTipoVivienda(tipoVivienda);

            propiedadRepositorio.save(propiedad);
        }
    }

    @Transactional
    public void eliminarPropiedad(String idPropiedad) throws Exception {

        Optional<Propiedad> respuesta = propiedadRepositorio.findById(idPropiedad);

        if (respuesta.isPresent()) {
            Propiedad propiedad = respuesta.get();
            propiedadRepositorio.delete(propiedad);
        }
    }

    public Propiedad getone(String idPropiedad) {
        return propiedadRepositorio.getOne(idPropiedad);
    }

    private void validar(String localidad, String barrio, String calle, String descripcion, Double mts2Totales, Double mts2Cubiertos, Double mts2Descubiertos, Integer altura, Integer cantBanios,
            Integer cantHabitaciones, String estado, String tipoVivienda) throws Exception {

        if (localidad.isEmpty() || localidad == null) {
            throw new Exception("La localidad no puede estar vacia");
        }
        if (barrio.isEmpty() || barrio == null) {
            throw new Exception("El Barrio no puede estar vacio");
        }
        if (calle.isEmpty() || calle == null) {
            throw new Exception("La Calle no puede estar vacia");
        }
        if (descripcion.isEmpty() || descripcion == null) {
            throw new Exception("La descripcion no puede ser nula o estar vacia");
        }
        if (mts2Totales == null) {
            throw new Exception("La cantidad de mts2 totales, no puede ser nula");
        }
        if (mts2Cubiertos == null) {
            throw new Exception("La cantidad de mts2 cubiertos, no puede ser nula");
        }
        if (mts2Descubiertos == null) {
            throw new Exception("La cantidad de mts2 descubiertos, no puede ser nula");
        }
        if (cantHabitaciones == null) {
            throw new Exception("no puede ser nula");
        }
        if (cantBanios == null) {
            throw new Exception("no puede ser nula");
        }
        if (altura == null) {
            throw new Exception("La Altura no puede estar vacia");
        }
        if (estado == null) {
            throw new Exception("El estado no pueden estar vacio");
        }
        if (tipoVivienda.isEmpty() || tipoVivienda == null) {
            throw new Exception("La localidad no puede estar vacia");
        }

    }
}
