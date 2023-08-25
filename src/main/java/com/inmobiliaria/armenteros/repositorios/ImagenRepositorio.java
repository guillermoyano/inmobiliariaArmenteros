package com.inmobiliaria.armenteros.repositorios;

import com.inmobiliaria.armenteros.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guillote
 */
@Repository
public interface ImagenRepositorio extends JpaRepository <Imagen, Integer>{

}
