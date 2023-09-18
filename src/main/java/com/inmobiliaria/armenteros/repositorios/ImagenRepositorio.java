package com.inmobiliaria.armenteros.repositorios;

import com.inmobiliaria.armenteros.entidades.Imagen;
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
public interface ImagenRepositorio extends JpaRepository<Imagen, Integer> {

    @Query(value = "SELECT contenido FROM imagen", nativeQuery = true)
    public List<byte[]> listaContenido();

    @Query(value = "select * from imagen where propiedad_id_propiedad= :idPropiedad", nativeQuery = true)
    public List<Imagen> listaImagenes(@Param("idPropiedad") Integer idPropiedad);
}
