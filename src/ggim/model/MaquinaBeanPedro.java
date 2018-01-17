/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase Maquina Bean
 * 
 * @author Pedro Alonso Montejo
 */
@XmlRootElement(name="maquina")
public class MaquinaBeanPedro implements Serializable{
    
    private final SimpleIntegerProperty iD;
    private final SimpleObjectProperty<ModeloBean> maquina;
    private final SimpleStringProperty revision;
    private final SimpleStringProperty prevision;
    private final SimpleObjectProperty<EstadoMaquina> estado;
    
    public MaquinaBeanPedro () {
        this.iD         = new SimpleIntegerProperty(0);
        this.maquina    = new SimpleObjectProperty<>(new ModeloBean());
        this.revision   = new SimpleStringProperty("");
        this.prevision   = new SimpleStringProperty("");
        this.estado     = new SimpleObjectProperty("");
    }
    
    /**
     * Metodo que controla la creación de una máquina (Constructor)
     * 
     * @param id es el ID que se le establecerá a la máquina cuando esta se cree
     * @param maquina es el modelo de la maquina que se le establecerá cuando
     * esta se cree
     * @param revision es la fecha de la última revisión que se le establecerá
     * cuando esta se cree
     * @param prevision es la fecha de la próxima revisión que se le establecerá
     * cuando esta se cree
     * @param estado es el estado que se le establecerá a la máquina cuando esta
     * se cree
     */
    public MaquinaBeanPedro(int id,
                        ModeloBean maquina,
                        String revision,
                        String prevision,
                        EstadoMaquina estado) {
        this.iD         = new SimpleIntegerProperty(id);
        this.maquina    = new SimpleObjectProperty<>(maquina);
        this.revision   = new SimpleStringProperty(revision);
        this.prevision   = new SimpleStringProperty(prevision);
        this.estado     = new SimpleObjectProperty(estado);
    }
    
    /**
     * Metodo que establece el ID de la máquina
     * 
     * @param id el ID que se establecerá
     */
    public void setID (int id) {
        this.iD.set(id);
    }
    
    /**
     * Metodo que recoge el ID actual de la máquina
     * 
     * @return devuelve el ID actual de la máquina
     */
    @XmlElement(name="id")
    public int getID () {
        return this.iD.get();
    }
    
    /**
     * Metodo que establece el modelo de la máquina
     * 
     * @param maquina es el modelo que se establecerá
     */
    public void setMaquina (ModeloBean maquina) {
        this.maquina.set(maquina);
    }
    
    /**
     * Metodo que recoge el modelo actual de la máquina
     * 
     * @return devuelve el modelo actual de la máquina
     */
    @XmlElement(name="modelo")
    public ModeloBean getMaquina () {
        return this.maquina.get();
    }
    
    /**
     * Metodo que establece la fecha de la última revisión de la máquina
     * 
     * @param revision es la fecha que se establecerá
     */
    public void setRevision (String revision) {
        this.revision.set(revision);
    }
    
    /**
     * Metodo que recoge la fecha de la última revisión actual de la máquina
     * 
     * @return devuelve la fecha de revisión actual de la máquina
     */
    @XmlElement(name="fechaUltimaRevision")
    public String getRevision () {
        return this.revision.get();
    }
    
    /**
     * Metodo que establece la fecha de la proxima revisión de la máquina
     * 
     * @param prevision es la fecha que se establecerá
     */
    public void setPrevision (String prevision) {
        this.revision.set(prevision);
    }
    
    /**
     * Metodo que recoge la fecha de de la próxima revisión actual de la máquina
     * 
     * @return devuelve la fecha de la próxima revisión actual de la máquina
     */
    @XmlElement(name="fechaProximaRevision")
    public String getPrevision () {
        return this.prevision.get();
    }
    
    /**
     * Metodo queestablece el estado de la máquina
     * 
     * @param estado es el estado que se establecerá
     */
    public void setEstado (EstadoMaquina estado) {
        this.estado.set(estado);
    }
    
    /**
     * Metodo que recoge el estado actual de la máquina
     * 
     * @return devuelve el estado actual de la máquina
     */
    @XmlElement(name="estado")
    public EstadoMaquina getEstado () {
        return this.estado.get();
    }
    
}
