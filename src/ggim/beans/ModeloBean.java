/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.beans;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase Modelo Bean
 * 
 * @author Pedro Alonso Montejo
 */
@XmlRootElement(name="modelo")
public class ModeloBean implements Serializable{

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty modelo;
    private final SimpleStringProperty modoEmp;
    
    public ModeloBean() {
        this.id = new SimpleIntegerProperty(0);
        this.modelo   = new SimpleStringProperty("");
        this.modoEmp   = new SimpleStringProperty("");
    }
    
    /**
     * Metodo que controla la creación del modelo (Constructor)
     * 
     * @param modelo es el nombre que se le establecerá al modelo cuando este
     * se cree
     * @param modoEmp es el modo de empleo que se le establecerá al modelo
     * cuando este se cree
     */
    public ModeloBean(int id,
                        String modelo,
                        String modoEmp) {
        this.id = new SimpleIntegerProperty(id);
        this.modelo   = new SimpleStringProperty(modelo);
        this.modoEmp   = new SimpleStringProperty(modoEmp);
    }
    
    @XmlElement(name="id")
    public int getID () {
        return this.id.get();
    }
    public void setID (int id) {
        this.id.set(id);
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
    @XmlElement(name="nombre")
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
    @XmlElement(name="modoEmpleo")
    public String getModoEmp () {
        return this.modoEmp.get();
    }
    
}