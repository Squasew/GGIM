/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

import ggim.model.MaquinaBeanPedro;
import java.util.Collection;
import javafx.collections.ObservableList;

/**
 * Clase que controla qué metodos deberían existir en la clase GM01TextGen
 * 
 * @author Pedro Alonso Montejo
 */
public interface GM01TextGenInterface {
    
    /**
     * 
     * @return
     */
    public Collection getAllMaquinas();
    
    /**
     *
     * @return
     */
    public Collection getAllModelos();
    
    /**
     *
     * @return
     */
    public Collection getAllModelos2();

    /**
     *
     * @param maquinasFilter
     * @param value
     * @return
     */
    public ObservableList<MaquinaBeanPedro> filterID(ObservableList<MaquinaBeanPedro> maquinasFilter, String value);

    /**
     *
     * @param maquinasFilter
     * @param selectedItem
     * @return
     */
    public ObservableList<MaquinaBeanPedro> filterEstado(ObservableList<MaquinaBeanPedro> maquinasFilter, String selectedItem);

    /**
     *
     * @param maquinasFilter
     * @param trim
     * @return
     */
    public ObservableList<MaquinaBeanPedro> filterFecha(ObservableList<MaquinaBeanPedro> maquinasFilter, String trim);

    /**
     *
     * @param maquinasFilter
     * @param selectedItem
     * @return
     */
    public ObservableList<MaquinaBeanPedro> filterModelo(ObservableList<MaquinaBeanPedro> maquinasFilter, String selectedItem);

    /**
     *
     * @param mb
     * @return
     */
    public ObservableList getCertain(MaquinaBeanPedro mb);

    /**
     *
     * @param mb
     * @return
     */
    public void makeNew(MaquinaBeanPedro mb);

    /**
     *
     * @param mb
     */
    public void modificarMaquina(MaquinaBeanPedro mb);

    /**
     *
     * @param mb
     * @return
     */
    public int getIndex(MaquinaBeanPedro mb);

    /**
     *
     * @param maquina
     * @return
     */
    public String getModeloName(String maquina);

    /**
     *
     * @param maquina
     * @return
     */
    public String getModeloText(String maquina);

    public void eliminarMaquina(MaquinaBeanPedro maquinaBeanPedro);
    
}
