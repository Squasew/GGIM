/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.MaquinaBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ubuntu
 */
public class GM01TextGen implements GM01TextGenInterface{

    private ArrayList <MaquinaBean> maquinas;
    private ArrayList <String> modelos;
    
    public GM01TextGen(){
        maquinas = new ArrayList();
            for (int i = 1; i <= 15; i++)
                maquinas.add(new MaquinaBean(i, "maquina"+i, i+"/11/2018","Usable"));
        modelos = new ArrayList();
            modelos.add("Sin modelo");
            for (int i = 1; i <= 15; i++)
                modelos.add("maquina"+i);
    }
    
    @Override
    public Collection getAllMaquinas() {
        return maquinas;
    }
    
    @Override
    public Collection getAllModelos() {
        return modelos;
    }

    @Override
    public ObservableList<MaquinaBean> filterID(ObservableList<MaquinaBean> maquinasFilter, String value) {
        
        int id = Integer.parseInt(value);
        
        Collection <MaquinaBean> maquinaFiltered =
                maquinasFilter.stream().filter(maquina -> maquina.getID() == id).collect(Collectors.toList());
        
        ObservableList <MaquinaBean> maquinasFilteredFinished =
                FXCollections.observableArrayList(maquinaFiltered);
        
        return maquinasFilteredFinished;
        
    }

    @Override
    public ObservableList<MaquinaBean> filterEstado(ObservableList<MaquinaBean> maquinasFilter, String selectedItem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<MaquinaBean> filterFecha(ObservableList<MaquinaBean> maquinasFilter, String trim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<MaquinaBean> filterModelo(ObservableList<MaquinaBean> maquinasFilter, String selectedItem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
