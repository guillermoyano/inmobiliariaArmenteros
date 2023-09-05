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
public interface PropiedadRepositorio extends JpaRepository <Propiedad, String> {

    @Query(value="SELECT * FROM Propiedad WHERE calle like %:calle%", nativeQuery = true) 
   public List<Propiedad> buscarPropiedadPorCalle(@Param("calle") String calle);
}
