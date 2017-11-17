/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.MaquinaBean;
import java.util.Collection;
import javafx.collections.ObservableList;

/**
 *
 * @author ubuntu
 */
public interface GM01TextGenInterface {
    
    public Collection getAllMaquinas();
    
    public Collection getAllModelos();

    public ObservableList<MaquinaBean> filterID(ObservableList<MaquinaBean> maquinasFilter, String value);

    public ObservableList<MaquinaBean> filterEstado(ObservableList<MaquinaBean> maquinasFilter, String selectedItem);

    public ObservableList<MaquinaBean> filterFecha(ObservableList<MaquinaBean> maquinasFilter, String trim);

    public ObservableList<MaquinaBean> filterModelo(ObservableList<MaquinaBean> maquinasFilter, String selectedItem);
    
}
