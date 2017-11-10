/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.EntrenamientoBean;
import ggim.model.MaquinaBean;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author ubuntu
 */
public class GM01TextGen implements GM01TextGenInterface{

    private ArrayList <MaquinaBean> maquinas;
    
    public GM01TextGen(){
        maquinas = new ArrayList();
            for (int i = 1; i <= 15; i++)
                maquinas.add(new MaquinaBean(i, "maquina"+i, i+"/11/2018","Activa"));
    }
    
    @Override
    public Collection getAllMaquinas() {
        return maquinas;
    }
    
}
