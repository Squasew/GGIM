/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Clase Maquina Bean
 * 
 * @author Pedro Alonso Montejo
 */
public class MaquinaBeanPedro {
    
    private final SimpleIntegerProperty iD;
    private final SimpleStringProperty maquina;
    private final SimpleStringProperty revision;
    private final SimpleStringProperty prevision;
    private final SimpleStringProperty estado;
    
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
                        String maquina,
                        String revision,
                        String prevision,
                        String estado) {
        this.iD         = new SimpleIntegerProperty(id);
        this.maquina    = new SimpleStringProperty(maquina);
        this.revision   = new SimpleStringProperty(revision);
        this.prevision   = new SimpleStringProperty(prevision);
        this.estado     = new SimpleStringProperty(estado);
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
    public int getID () {
        return this.iD.get();
    }
    
    /**
     * Metodo que establece el modelo de la máquina
     * 
     * @param maquina es el modelo que se establecerá
     */
    public void setMaquina (String maquina) {
        this.maquina.set(maquina);
    }
    
    /**
     * Metodo que recoge el modelo actual de la máquina
     * 
     * @return devuelve el modelo actual de la máquina
     */
    public String getMaquina () {
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
    public String getPrevision () {
        return this.prevision.get();
    }
    
    /**
     * Metodo queestablece el estado de la máquina
     * 
     * @param estado es el estado que se establecerá
     */
    public void setEstado (String estado) {
        this.estado.set(estado);
    }
    
    /**
     * Metodo que recoge el estado actual de la máquina
     * 
     * @return devuelve el estado actual de la máquina
     */
    public String getEstado () {
        return this.estado.get();
    }
    
}
