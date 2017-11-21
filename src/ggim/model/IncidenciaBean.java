/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Clase IncidenciaBean
 * @author Ismael Molano
 */
public class IncidenciaBean {
     private final SimpleIntegerProperty id;
     private final SimpleStringProperty maquina;
     private final SimpleStringProperty fecha;
     private final SimpleStringProperty estado;

    /**
     * Constructor de IncidenciaBean, que recibe todos los atributos de la clase
     * @param id identidicador de incidencia
     * @param maquina nombre de maquina
     * @param fecha fecha de incidencia
     * @param estado estado de incidencia
     */
    public IncidenciaBean(Integer id, String maquina, String fecha, String estado) {
        this.id = new SimpleIntegerProperty(id);
        this.maquina= new SimpleStringProperty(maquina);
        this.estado= new SimpleStringProperty(estado);
        this.fecha= new SimpleStringProperty(fecha);
    }

    /**
     * Metodo publico que devuelve un Integer
     * @return Integer
     */
    public Integer getId() {
        return this.id.get();
    }

    /**
     * Metodo publico que devuelve un String
     * @return String
     */
    public String  getMaquina() {
        return this.maquina.get();
    }

    /**
     * Metodo publico que devuelve un String
     * @return String
     */
    public String getFecha() {
        return this.fecha.get();
    }

    /**
     * Metodo publico que devuelve un String
     * @return String
     */
    public String getEstado() {
        return this.estado.get();
    }
    
    /**
     * Metodo publico que recibe un Integer
     * @param id identidicador de incidencia
     */
    public void setId(Integer id){
       this.id.set(id);
    }
    
    /**
     * Metodo publico que recibe un String
     * @param maquina nombre de maquina
     */
    public void setMaquina(String maquina){
        this.maquina.set(maquina);
    }
    
    /**
     * Metodo publico que recibe un String
     * @param fecha fecha de incidencia
     */
    public void setFecha(String fecha){
        this.fecha.set(fecha);
    }
    
    /**
     * Metodo publico que recibe un String
     * @param estado estado de incidencia
     */
    public void setEstado(String estado){
        this.estado.set(estado);
    }
    
    
    
    
    
    
    
     
    
    
}
