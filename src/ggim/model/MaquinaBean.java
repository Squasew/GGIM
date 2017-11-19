/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ubuntu
 */
public class MaquinaBean {
    
    private final SimpleIntegerProperty iD;
    private final SimpleStringProperty maquina;
    private final SimpleStringProperty revision;
    private final SimpleStringProperty prevision;
    private final SimpleStringProperty estado;
    
    public MaquinaBean(int id,
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
    
    public void setID (int id) {
        this.iD.set(id);
    }
    
    public int getID () {
        return this.iD.get();
    }
    
    public void setMaquina (String maquina) {
        this.maquina.set(maquina);
    }
    
    public String getMaquina () {
        return this.maquina.get();
    }
    
    public void setRevision (String revision) {
        this.revision.set(revision);
    }
    
    public String getRevision () {
        return this.revision.get();
    }
    
    public void setEstado (String estado) {
        this.estado.set(estado);
    }
    
    public String getEstado () {
        return this.estado.get();
    }
    
}
