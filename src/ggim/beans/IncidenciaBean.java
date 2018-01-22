/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.beans;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase IncidenciaBean
 *
 * @author Ismael Molano
 */
@XmlRootElement(name = "incidencia")
public class IncidenciaBean {

    private final SimpleIntegerProperty id;
    private final SimpleObjectProperty<MaquinaBean> maquina;
    private final SimpleObjectProperty<Date> fecha;
    private final SimpleObjectProperty<EstadoIncidencia> estado;

    /**
     * Constructor de IncidenciaBean, que recibe todos los atributos de la clase
     *
     * @param id identidicador de incidencia
     * @param maquina objeto maquina deesta incidencia
     * @param fecha fecha de incidencia
     * @param estado estado de incidencia
     */
    public IncidenciaBean(Integer id, MaquinaBean maquina, Date fecha, EstadoIncidencia estado) {
        this.id = new SimpleIntegerProperty(id);
        this.maquina = new SimpleObjectProperty<MaquinaBean>(maquina);
        this.estado = new SimpleObjectProperty<EstadoIncidencia>(estado);
        this.fecha = new SimpleObjectProperty<Date>(fecha);
    }

    /**
     * Metodo publico que devuelve un Integer
     *
     * @return Integer
     */
    public Integer getId() {
        return this.id.get();
    }

    /**
     * Metodo publico que devuelve un String
     *
     * @return String
     */
    public MaquinaBean getMaquina() {
        return this.maquina.get();
    }

    /**
     * Metodo publico que devuelve un String
     *
     * @return String
     */
    @XmlElement(name = "fechaAlta")
    public Date getFecha() {
        return this.fecha.get();
    }

    /**
     * Metodo publico que devuelve un String
     *
     * @return String
     */
    public EstadoIncidencia getEstado() {
        return this.estado.get();
    }

    /**
     * Metodo publico que recibe un Integer
     *
     * @param id identidicador de incidencia
     */
    public void setId(Integer id) {
        this.id.set(id);
    }

    /**
     * Metodo publico que recibe un String
     *
     * @param maquina nombre de maquina
     */
    public void setMaquina(MaquinaBean maquina) {
        this.maquina.set(maquina);
    }

    /**
     * Metodo publico que recibe un String
     *
     * @param fecha fecha de incidencia
     */
    public void setFecha(Date fecha) {
        this.fecha.set(fecha);
    }

    /**
     * Metodo publico que recibe un String
     *
     * @param estado estado de incidencia
     */
    public void setEstado(EstadoIncidencia estado) {
        this.estado.set(estado);
    }
}
