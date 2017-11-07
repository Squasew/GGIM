/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.EntrenamientoBean;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author ubuntu
 */
public class GM01TextGen implements GM01TextGenInterface{

    private ArrayList <EntrenamientoBean> entrenamientos;
    
    public GM01TextGen(){
        entrenamientos = new ArrayList();
            for (int i = 0; i < 15; i++)
                entrenamientos.add(new EntrenamientoBean("nombre"+i,"entrenador","03/11/2017",i));
    }
    
    @Override
    public Collection getAllMaquinas() {
        return entrenamientos;
    }
    
}
