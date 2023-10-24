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

//   @Query(value="select * from propiedad where (tipo_vivienda is null or tipo_vivienda like %:keyword%) "
//           + "and (estado is null or estado like %:keyword1%) and (moneda is null or moneda like %:keyword2%) "
//           + "and (localidad is null or localidad like %:keyword3%) "
//           + "and (:keyword4 IS NULL OR :keyword4 = '' OR precio_propiedad IS NULL OR precio_propiedad <= :keyword4) "
//           + "and (:keyword5 IS NULL OR :keyword5 = '' OR mts2totales IS NULL OR mts2totales <= :keyword5);", nativeQuery = true) 
//   public List<Propiedad> buscarPropiedadPotTipoDeVivienda(@Param("keyword") String keyword, 
//           @Param("keyword1") String keyword1, @Param("keyword2") String keyword2, 
//           @Param("keyword3") String keyword3, @Param("keyword4") Long keyword4, @Param("keyword5") Long keyword5);
   
   @Query(value="select * from propiedad where (tipo_vivienda is null or tipo_vivienda like %:keyword%) "
           + "and (estado is null or estado like %:keyword1%) and (moneda is null or moneda like %:keyword2%) "
           + "and (localidad is null or localidad like %:keyword3%) "
           + "and (:keyword4 IS NULL OR :keyword4 = '' OR precio_propiedad IS NULL OR precio_propiedad >= :keyword4) "
           + "and (:keyword5 IS NULL OR :keyword5 = '' OR precio_propiedad IS NULL OR precio_propiedad <= :keyword5);", nativeQuery = true) 
   public List<Propiedad> buscarPropiedadPotTipoDeVivienda(@Param("keyword") String keyword, 
           @Param("keyword1") String keyword1, @Param("keyword2") String keyword2, 
           @Param("keyword3") String keyword3, @Param("keyword4") Long keyword4, @Param("keyword5") Long keyword5);
}
