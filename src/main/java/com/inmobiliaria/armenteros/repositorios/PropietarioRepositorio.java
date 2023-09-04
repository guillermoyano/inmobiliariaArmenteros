package com.inmobiliaria.armenteros.repositorios;

import com.inmobiliaria.armenteros.entidades.Propietario;
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
public interface PropietarioRepositorio extends JpaRepository <Propietario, Long> {
    
    @Query(value = "SELECT * FROM Propietario p order by id_Propietario desc limit 1", nativeQuery = true)
    public Propietario buscarPropietarioPordni();
    
     @Query("SELECT p FROM Propietario p WHERE p.dni = :dni")
    public Propietario buscarPropietarioPorDni(@Param("dni") Long dni);
    
    @Query(value="SELECT * FROM Propietario WHERE dni like %:dni%", nativeQuery = true)
    public List<Propietario> buscarPropietarioPorDni1(@Param("dni") Long dni);

}
