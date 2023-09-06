package com.inmobiliaria.armenteros.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Guillote
 */
@Entity
@SequenceGenerator(name = "sec_generator", sequenceName = "secuencial_sequence", initialValue = 100, allocationSize = 1)
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Propiedad {

    @Id
    @SequenceGenerator(name = "sec_generator", sequenceName = "secuencial_sequence", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_generator")
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idPropiedad;
    private Double mts2Totales;
    private Double mts2Cubiertos;
    private Double mts2Descubiertos;
    private String localidad;
    private String barrio;
    private String calle;
    private Integer altura;
    private String estado;
    private String descripcion;
    private Boolean aguaCorriente = false;
    private Boolean aireAcondicionado = false;
    private Boolean aptoCredito = false;
    private Boolean aptoProfesional = false;
    private Boolean cloacas = false;
    private Boolean gasNatural = false;
    private Boolean permiteMascotas = false;
    private Boolean salonJuegos = false;
    private Boolean gimnasio = false;
    private Boolean luz = false;
    private Boolean pavimento = false;
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    private Boolean patio = false;
    private Boolean quincho = false;
    private Boolean balcon = false;
    private Boolean sum = false;
    private Boolean terraza = false;
    private Boolean baulera = false;
    private Boolean parrilla = false;
    private Boolean cochera = false;
    private Boolean pileta = false;
    private Boolean ascensor = false;
    private Boolean lavadero = false;
    private Boolean suite = false;
    private Boolean vestidor = false;
    private Boolean toillete = false;
    private Integer cantHabitaciones;
    private Boolean banio = false;
    private Integer cantBanios;
    private Boolean cocina = false;
    private Boolean expensas = false;
    private String tipoVivienda;
    @OneToOne
    private Propietario propietario;
    @OneToOne
    private Imagen imagen;
}
