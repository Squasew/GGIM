/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.control;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Interfaz de MaquinasManager
 * @author Ismael Molano
 */
public interface MaquinasManager {
    
    /**
     * Metodo que recibe un String y devuelve una coleccion de  MaquinaBean con esa maquina
     * @param maquina nombre de maquina
     * @return Collection
     */
    public Collection getThisMaquina(String maquina);
    
    
}
