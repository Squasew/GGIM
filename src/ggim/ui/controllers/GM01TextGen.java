/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.MaquinaBean;
import ggim.model.ModeloBean;
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
    private ArrayList <String> modelosCombo;
    private ArrayList <String> modelosCombo2;
    private ArrayList <ModeloBean> modelos;
    
    public GM01TextGen(){
        
        modelos = new ArrayList();
            for (int i = 1; i <= 4; i++)
                modelos.add(new ModeloBean("modelo"+i,"descripción"+i));
        
        modelosCombo = new ArrayList();
        modelosCombo2 = new ArrayList();
        modelosCombo.add("Sin modelo");
            for (int i = 1; i <= modelos.size(); i++) {
                
                modelosCombo2.add(modelos.get(i-1).getModelo());
                modelosCombo.add(modelos.get(i-1).getModelo());
                
            }
        
        maquinas = new ArrayList();
        int randomNum;
            for (int i = 10; i <= 25; i++) {
                randomNum = 0 + (int)(Math.random() * 3);
                maquinas.add(new MaquinaBean(i, modelos.get(randomNum).getModelo(), i+"/11/2018",i+"/12/2018","Usable"));
            }
                
    }
    
    @Override
    public Collection getAllMaquinas() {
        return maquinas;
    }
    
    @Override
    public Collection getAllModelos() {
        return modelosCombo;
    }

    @Override
    public Collection getAllModelos2() {
        return modelosCombo2;
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
        
        Collection <MaquinaBean> maquinaFiltered =
                maquinasFilter.stream().filter(maquina -> maquina.getEstado().equals(selectedItem)).collect(Collectors.toList());
        
        ObservableList <MaquinaBean> maquinasFilteredFinished =
                FXCollections.observableArrayList(maquinaFiltered);
        
        return maquinasFilteredFinished;
        
    }

    @Override
    public ObservableList<MaquinaBean> filterFecha(ObservableList<MaquinaBean> maquinasFilter, String trim) {
        
        Collection <MaquinaBean> maquinaFiltered =
                maquinasFilter.stream().filter(maquina -> maquina.getRevision().equals(trim)).collect(Collectors.toList());
        
        ObservableList <MaquinaBean> maquinasFilteredFinished =
                FXCollections.observableArrayList(maquinaFiltered);
        
        return maquinasFilteredFinished;
        
    }

    @Override
    public ObservableList<MaquinaBean> filterModelo(ObservableList<MaquinaBean> maquinasFilter, String selectedItem) {
        
        Collection <MaquinaBean> maquinaFiltered =
                maquinasFilter.stream().filter(maquina -> maquina.getMaquina().equals(selectedItem)).collect(Collectors.toList());
        
        ObservableList <MaquinaBean> maquinasFilteredFinished =
                FXCollections.observableArrayList(maquinaFiltered);
        
        return maquinasFilteredFinished;
        
    }

    @Override
    public ObservableList getCertain(MaquinaBean mb) {
        
        ObservableList <MaquinaBean> maquinasFilter =
                FXCollections.observableArrayList(maquinas);
        
        Collection <MaquinaBean> maquinaFiltered =
                maquinasFilter.stream().filter(maquina -> maquina.getID() == mb.getID()).collect(Collectors.toList());
        
        ObservableList <MaquinaBean> maquinasFilteredFinished =
                FXCollections.observableArrayList(maquinaFiltered);
        
        return maquinasFilteredFinished;
        
    }

    @Override
    public MaquinaBean makeNew(MaquinaBean mb) {
        
        int modelo = 0;
        int maxID = maquinas.size()+1;
        modelo = 0 + (int)(Math.random() * 3);
        mb = new MaquinaBean(maxID, modelos.get(modelo).getModelo(), maxID+"/11/2018",maxID+"/12/2018","Usable");
            
        return mb;
        
    }

    @Override
    public void modificarMaquina(MaquinaBean mb) {
        
        for (int i = 0; i <= maquinas.size(); i++) {
            if (maquinas.get(i).getID() == mb.getID()) {
                maquinas.get(i).setMaquina(mb.getMaquina());
                break;
            }
        }
        
    }

    @Override
    public int getIndex(MaquinaBean mb) {
        
        int index = 0;
        
        for (int i = 0; i < modelosCombo2.size(); i++) {
            if (modelosCombo2.get(i).equals(mb.getMaquina()))
                index = i; 
        }
        
        return index;
        
    }

    @Override
    public String getModeloName(String maquina) {
        
        String name = "";
        
        for (int i = 0; i < modelos.size(); i++) {
            if ((modelos.get(i).getModelo()).equals(maquina))
                name = modelos.get(i).getModelo();
        }
        
        return name;
        
    }

    @Override
    public String getModeloText(String maquina) {
        
        String text = "";
        
        for (int i = 0; i < modelos.size(); i++) {
            if ((modelos.get(i).getModelo()).equals(maquina))
                text = modelos.get(i).getModoEmp();
        }
        
        return text;
        
    }

}
