/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Charly
 */
public class ModeloBean {

    private final SimpleStringProperty modelo;
    private final SimpleStringProperty modoEmp;
    
    public ModeloBean(String modelo,
                        String modoEmp) {
        this.modelo   = new SimpleStringProperty(modelo);
        this.modoEmp   = new SimpleStringProperty(modoEmp);
    }
    
    public void setModelo (String modelo) {
        this.modelo.set(modelo);
    }
    
    public String getModelo () {
        return this.modelo.get();
    }
        
    public void setModoEmp (String modoEmp) {
        this.modoEmp.set(modoEmp);
    }
    
    public String getMaquina () {
        return this.modoEmp.get();
    }
    
}
