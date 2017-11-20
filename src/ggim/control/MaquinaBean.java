/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.control;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Clase MaquinaBean
 * @author Ismael Molano
 */
public class MaquinaBean {
    private SimpleStringProperty maquina;
    private SimpleStringProperty estado;
    private SimpleIntegerProperty modelo;
    private SimpleStringProperty ultimaRevision;
    private SimpleStringProperty proximaRevision;
    private SimpleStringProperty descripcion;
    
    /**
     * Constructor de la calse MaquinaBean que recibe todos los atributos
     * @param maquina nombre de maquina
     * @param estado estado de maquina
     * @param modelo modelo de maquina
     * @param ultimaRevision fecha de ultima revision
     * @param proximaRevision fecha de proxima revision
     * @param descripcion descripcion de maquina
     */
    public MaquinaBean(String maquina, String estado, Integer modelo ,String ultimaRevision,String proximaRevision ,String descripcion){
        this.maquina= new SimpleStringProperty(estado);
        this.estado=new SimpleStringProperty(estado);
        this.modelo= new SimpleIntegerProperty(modelo);
        this.ultimaRevision= new SimpleStringProperty(ultimaRevision);
        this.proximaRevision= new SimpleStringProperty(proximaRevision);
        this.descripcion= new SimpleStringProperty(descripcion);
    }

    /**
     * Metodo publico que devuelve un String
     * @return String
     */
    public String getDescripcion() {
        return this.descripcion.get();
    }

    /**
     * Metodo publico que recibe un String
     * @param descripcion descripcion de maquina
     */
    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    
    /**
     * Metodo publico que devuelve un String
     * @return String
     */
    public String getMaquina() {
        return this.maquina.get();
    }

    /**
     * Metodo publico que recibe un String
     * @param maquina nombre de maquina
     */
    public void setMaquina(String maquina) {
        this.maquina.set(maquina);
    }

    /**
     * Metodo publico que devuelve un String
     * @return String
     */
    public String getEstado() {
        return this.estado.get();
    }

    /**
     * Metodo publico que recibe un String
     * @param estado estado de maquina
     */
    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    /**
     * Metodo publico que devuelve un Integer
     * @return Integer
     */
    public Integer getModelo() {
        return this.modelo.get();
    }

    /**
     * Metodo publico que recibe un Integer
     * @param modelo modelo de maquina
     */
    public void setModelo(Integer modelo) {
        this.modelo.set(modelo);
    }

    /**
     * Metodo publico que devuelve un String
     * @return String
     */
    public String getUltimaRevision() {
        return this.ultimaRevision.get();
    }

    /**
     * Metodo publico que recibe un String
     * @param ultimaRevision fecha de ultima revision
     */
    public void setUltimaRevision(String ultimaRevision) {
        this.ultimaRevision.set(ultimaRevision);
    }

    /**
     * Metodo publico que devuelve un String
     * @return String
     */
    public String getProximaRevision() {
        return this.proximaRevision.get();
    }

    /**
     * Metodo publico que recibe un String
     * @param proximaRevision fecha de proxima revision
     */
    public void setProximaRevision(String proximaRevision) {
        this.proximaRevision.set(proximaRevision);
    }
}
