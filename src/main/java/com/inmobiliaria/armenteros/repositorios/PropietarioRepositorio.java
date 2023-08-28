package com.inmobiliaria.armenteros.repositorios;

import com.inmobiliaria.armenteros.entidades.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guillote
 */
@Repository
public interface PropietarioRepositorio extends JpaRepository <Propietario, Long> {
    
    @Query(value = "SELECT * FROM Propietario p order by dni desc limit 1", nativeQuery = true)
    public Propietario buscarPropietarioPordni();

}
