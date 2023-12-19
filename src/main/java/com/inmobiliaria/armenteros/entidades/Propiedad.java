package com.inmobiliaria.armenteros.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Guillote
 */
@Entity
public class Propiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
    @TableGenerator(name = "custom-id", table = "id_generator", pkColumnName = "id_key", pkColumnValue = "propiedad", valueColumnName = "id_value", initialValue = 1000, allocationSize = 1)
    private Integer idPropiedad;
    private Long precioPropiedad;
    private Long mts2Totales;
    private Long mts2Cubiertos;
    private Long mts2Descubiertos;
    private String localidad;
    private String barrio;
    private String calle;
    private String altura;
    private String estado;
    private String descripcion;
    private String moneda;
    private Boolean estadoComercial = true;
    private Boolean reserva = true;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propiedad")
    private List<Imagen> imagenes;

    public void agregarImagen(Imagen imagen) {
        if (imagenes == null) {
            imagenes = new ArrayList();

        }
        imagenes.add(imagen);
    }

    public Propiedad() {
    }

    public Propiedad(Integer idPropiedad, Long precioPropiedad, Long mts2Totales, Long mts2Cubiertos, Long mts2Descubiertos, String localidad, String barrio, String calle, String altura, String estado, String descripcion, String moneda, Date fechaPublicacion, Integer cantHabitaciones, Integer cantBanios, String tipoVivienda, Propietario propietario, List<Imagen> imagenes) {
        this.idPropiedad = idPropiedad;
        this.precioPropiedad = precioPropiedad;
        this.mts2Totales = mts2Totales;
        this.mts2Cubiertos = mts2Cubiertos;
        this.mts2Descubiertos = mts2Descubiertos;
        this.localidad = localidad;
        this.barrio = barrio;
        this.calle = calle;
        this.altura = altura;
        this.estado = estado;
        this.descripcion = descripcion;
        this.moneda = moneda;
        this.fechaPublicacion = fechaPublicacion;
        this.cantHabitaciones = cantHabitaciones;
        this.cantBanios = cantBanios;
        this.tipoVivienda = tipoVivienda;
        this.propietario = propietario;
        this.imagenes = imagenes;
    }

    public Integer getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(Integer idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public Long getPrecioPropiedad() {
        return precioPropiedad;
    }

    public void setPrecioPropiedad(Long precioPropiedad) {
        this.precioPropiedad = precioPropiedad;
    }

    public Long getMts2Totales() {
        return mts2Totales;
    }

    public void setMts2Totales(Long mts2Totales) {
        this.mts2Totales = mts2Totales;
    }

    public Long getMts2Cubiertos() {
        return mts2Cubiertos;
    }

    public void setMts2Cubiertos(Long mts2Cubiertos) {
        this.mts2Cubiertos = mts2Cubiertos;
    }

    public Long getMts2Descubiertos() {
        return mts2Descubiertos;
    }

    public void setMts2Descubiertos(Long mts2Descubiertos) {
        this.mts2Descubiertos = mts2Descubiertos;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Boolean getEstadoComercial() {
        return estadoComercial;
    }

    public void setEstadoComercial(Boolean estadoComercial) {
        this.estadoComercial = estadoComercial;
    }

    public Boolean getReserva() {
        return reserva;
    }

    public void setReserva(Boolean reserva) {
        this.reserva = reserva;
    }

    public Boolean getAguaCorriente() {
        return aguaCorriente;
    }

    public void setAguaCorriente(Boolean aguaCorriente) {
        this.aguaCorriente = aguaCorriente;
    }

    public Boolean getAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(Boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public Boolean getAptoCredito() {
        return aptoCredito;
    }

    public void setAptoCredito(Boolean aptoCredito) {
        this.aptoCredito = aptoCredito;
    }

    public Boolean getAptoProfesional() {
        return aptoProfesional;
    }

    public void setAptoProfesional(Boolean aptoProfesional) {
        this.aptoProfesional = aptoProfesional;
    }

    public Boolean getCloacas() {
        return cloacas;
    }

    public void setCloacas(Boolean cloacas) {
        this.cloacas = cloacas;
    }

    public Boolean getGasNatural() {
        return gasNatural;
    }

    public void setGasNatural(Boolean gasNatural) {
        this.gasNatural = gasNatural;
    }

    public Boolean getPermiteMascotas() {
        return permiteMascotas;
    }

    public void setPermiteMascotas(Boolean permiteMascotas) {
        this.permiteMascotas = permiteMascotas;
    }

    public Boolean getSalonJuegos() {
        return salonJuegos;
    }

    public void setSalonJuegos(Boolean salonJuegos) {
        this.salonJuegos = salonJuegos;
    }

    public Boolean getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(Boolean gimnasio) {
        this.gimnasio = gimnasio;
    }

    public Boolean getLuz() {
        return luz;
    }

    public void setLuz(Boolean luz) {
        this.luz = luz;
    }

    public Boolean getPavimento() {
        return pavimento;
    }

    public void setPavimento(Boolean pavimento) {
        this.pavimento = pavimento;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Boolean getPatio() {
        return patio;
    }

    public void setPatio(Boolean patio) {
        this.patio = patio;
    }

    public Boolean getQuincho() {
        return quincho;
    }

    public void setQuincho(Boolean quincho) {
        this.quincho = quincho;
    }

    public Boolean getBalcon() {
        return balcon;
    }

    public void setBalcon(Boolean balcon) {
        this.balcon = balcon;
    }

    public Boolean getSum() {
        return sum;
    }

    public void setSum(Boolean sum) {
        this.sum = sum;
    }

    public Boolean getTerraza() {
        return terraza;
    }

    public void setTerraza(Boolean terraza) {
        this.terraza = terraza;
    }

    public Boolean getBaulera() {
        return baulera;
    }

    public void setBaulera(Boolean baulera) {
        this.baulera = baulera;
    }

    public Boolean getParrilla() {
        return parrilla;
    }

    public void setParrilla(Boolean parrilla) {
        this.parrilla = parrilla;
    }

    public Boolean getCochera() {
        return cochera;
    }

    public void setCochera(Boolean cochera) {
        this.cochera = cochera;
    }

    public Boolean getPileta() {
        return pileta;
    }

    public void setPileta(Boolean pileta) {
        this.pileta = pileta;
    }

    public Boolean getAscensor() {
        return ascensor;
    }

    public void setAscensor(Boolean ascensor) {
        this.ascensor = ascensor;
    }

    public Boolean getLavadero() {
        return lavadero;
    }

    public void setLavadero(Boolean lavadero) {
        this.lavadero = lavadero;
    }

    public Boolean getSuite() {
        return suite;
    }

    public void setSuite(Boolean suite) {
        this.suite = suite;
    }

    public Boolean getVestidor() {
        return vestidor;
    }

    public void setVestidor(Boolean vestidor) {
        this.vestidor = vestidor;
    }

    public Boolean getToillete() {
        return toillete;
    }

    public void setToillete(Boolean toillete) {
        this.toillete = toillete;
    }

    public Integer getCantHabitaciones() {
        return cantHabitaciones;
    }

    public void setCantHabitaciones(Integer cantHabitaciones) {
        this.cantHabitaciones = cantHabitaciones;
    }

    public Boolean getBanio() {
        return banio;
    }

    public void setBanio(Boolean banio) {
        this.banio = banio;
    }

    public Integer getCantBanios() {
        return cantBanios;
    }

    public void setCantBanios(Integer cantBanios) {
        this.cantBanios = cantBanios;
    }

    public Boolean getCocina() {
        return cocina;
    }

    public void setCocina(Boolean cocina) {
        this.cocina = cocina;
    }

    public Boolean getExpensas() {
        return expensas;
    }

    public void setExpensas(Boolean expensas) {
        this.expensas = expensas;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

}
