/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

import ggim.beans.MaquinaBean;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Clase que genera objetos MaquinaBean de pueba
 * @author Ismael Molano
 */
public class MaquinasManagerTestGenerator implements MaquinasManager{
    private String mac;
    ArrayList<String> estados;
    
    /**
     * Metodo publico que devuelve un objeto MaquinaBean
     * @return MaquinaBean 
     */
    public String getMaquina() {
        return mac;
    }
    /**
     * Metodo publico que recibe un objeto MaquinaBean
     * @param maquina  nombre de maquina
     */
    public void setMaquina(String maquina) {
        this.mac = maquina;
    }
    /**
     * 
     * Metodo que recibe un String y devuelve una coleccion de  MaquinaBean con esa maquina
     * @param name nombre de maquina
     * @return Collection
     */
    @Override
    public Collection getThisMaquina(String name) {
        estados= new ArrayList<String>();
        estados.add("Sin selección");
        estados.add("Resuelta");
        estados.add("En proceso");
        estados.add("Sin procesar");
        int x= (int)(Math.random()*3)+1;
        MaquinaBean maquina;
        maquina = new MaquinaBean(name, estados.get(x), x,x+"/11/2017",x+"/11/2018","descripcion maquina "+ name);
        ArrayList<MaquinaBean> maquinas= new ArrayList<MaquinaBean>();
        maquinas.add(maquina);
        return maquinas;
    }
    
    
    
    
}
