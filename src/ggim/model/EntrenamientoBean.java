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
public class EntrenamientoBean {
    
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty entrenador;
    private final SimpleStringProperty fecha;
    private final SimpleIntegerProperty duracion;
    
    public EntrenamientoBean (String nombre,
                                String entrenador,
                                String fecha,
                                int duracion) {
        this.nombre = new SimpleStringProperty (nombre);
        this.entrenador = new SimpleStringProperty (entrenador);
        this.fecha = new SimpleStringProperty (fecha);
        this.duracion = new SimpleIntegerProperty (duracion);
    }
    
    public void setNombre(String nombre){
        this.nombre.set(nombre);
    }
    
    public String getNombre(){
        return this.nombre.get();
    }
    
    public void setEntrenador(String entrenador){
        this.entrenador.set(entrenador);
    }
    
    public String getEntrenador() {
        return this.entrenador.get();
    }
    
    public void setFecha(String fecha){
        this.fecha.set(fecha);
    }
    
    public String getFecha() {
        return this.fecha.get();
    }
    
    public void setDuracion(Integer duracion){
        this.duracion.set(duracion);
    }
    
    public Integer getDuracion(){
        return this.duracion.get();
    }
    
}
