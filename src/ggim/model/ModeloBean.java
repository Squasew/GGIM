/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Clase Modelo Bean
 * 
 * @author Pedro Alonso Montejo
 */
public class ModeloBean {

    private final SimpleStringProperty modelo;
    private final SimpleStringProperty modoEmp;
    
    /**
     * Metodo que controla la creación del modelo (Constructor)
     * 
     * @param modelo es el nombre que se le establecerá al modelo cuando este
     * se cree
     * @param modoEmp es el modo de empleo que se le establecerá al modelo
     * cuando este se cree
     */
    public ModeloBean(String modelo,
                        String modoEmp) {
        this.modelo   = new SimpleStringProperty(modelo);
        this.modoEmp   = new SimpleStringProperty(modoEmp);
    }
    
    /**
     * Metodo que establece el identificador del modelo
     * 
     * @param modelo es el modelo que see establecerá
     */
    public void setModelo (String modelo) {
        this.modelo.set(modelo);
    }
    
    /**
     * Metodo que devuelve el identificador del modelo
     * 
     * @return devuelve el identificador del modelo
     */
    public String getModelo () {
        return this.modelo.get();
    }
        
    /**
     * Metodo que establece el modo de empleo del modelo
     * 
     * @param modoEmp
     */
    public void setModoEmp (String modoEmp) {
        this.modoEmp.set(modoEmp);
    }
    
    /**
     * Metodo que devuelve el metodo de empleo del modelo
     * 
     * @return devuelve el metodo de empleo del modelo
     */
    public String getModoEmp () {
        return this.modoEmp.get();
    }
    
}
