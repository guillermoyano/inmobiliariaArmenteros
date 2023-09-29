package com.inmobiliaria.armenteros.repositorios;

import com.inmobiliaria.armenteros.entidades.Propiedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guillote
 */

@Repository
public interface PropiedadRepositorio extends JpaRepository <Propiedad, Integer> {

    @Query(value="SELECT * FROM Propiedad WHERE calle like %:calle%", nativeQuery = true) 
   public List<Propiedad> buscarPropiedadPorCalle(@Param("calle") String calle);

 @Query(value = "SELECT i FROM Propiedad i WHERE " +
       "(:localidad IS NULL OR i.localidad = :localidad) AND " +
       "(:barrio IS NULL OR i.barrio = :barrio) AND " +
       "(:precioMin IS NULL OR ((i.moneda = 'Dólares' AND i.precio >= :precioMin) OR " +
       "(i.moneda = 'Pesos' AND i.precio >= :precioMin * :tipoCambio))) AND " +
       "(:precioMax IS NULL OR ((i.moneda = 'Dólares' AND i.precio <= :precioMax) OR " +
       "(i.moneda = 'Pesos' AND i.precio <= :precioMax * :tipoCambio))) AND " +
       "(:tipo IS NULL OR i.tipo = :tipo) AND " +
       "(:estado IS NULL OR i.estado = :estado) AND " +
       "(:moneda IS NULL OR i.moneda = :moneda)", nativeQuery = true)
public List<Propiedad> searchInmuebles(String localidad, String barrio, Double precioMin, Double precioMax,
                               String tipo, String estado, String moneda, Double tipoCambio);

}
