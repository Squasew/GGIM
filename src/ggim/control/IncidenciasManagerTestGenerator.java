/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.control;


import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;

/**
 * Clase que genera objetos IncidenciasBean de pueba
 * @author Ismael Molano
 */
public class IncidenciasManagerTestGenerator implements IncidenciasManager{
    private static final Logger LOGGER = Logger.getLogger( IncidenciasManagerTestGenerator.class.getName() );
    ArrayList <IncidenciaBean> incidencias;
    ArrayList <String> maquinas;
    ArrayList <String> estados;
    private IncidenciaBean selectedIncidencia;
    private String formatoFecha="dd/MM/yyyy";
    /**
     * Metodo publico que devuelve una coleccion de Strings
     * @return Collection
     */
    @Override
    public Collection getAllIncidencias() {
        return incidencias;
    }
    /**
     * Metodo publico que devuelve una coleccion de Strings
     * @return Collection
     */
    public Collection getAllMaquinas(){
        return maquinas;
    }

    /**
     * Constructor vacio de la clase IncidenciasManagerTestGenerator
     */
    public IncidenciasManagerTestGenerator(){
        maquinas= new ArrayList<String>();
        maquinas.add("Sin selección");
        for(int i=10;i<18;i++){
            maquinas.add("maquina"+i);
        }
        LOGGER.info("Maquinas añadidas");
        estados= new ArrayList <String>();
        estados.add("Sin selección");
        estados.add("Resuelta");
        estados.add("En proceso");
        estados.add("Sin procesar");
        LOGGER.info("Estados añadidos");
        incidencias= new ArrayList <IncidenciaBean>();
        for(int i=10;i<18;i++){
            int x= (int)(Math.random()*3)+1;
            String fecha=i+"/11/2017";
            incidencias.add(new IncidenciaBean(i,maquinas.get(i-9),fecha,estados.get(x)));
        }
        LOGGER.info("Datos generados correctamente");
        
    }
    /**
     * Metodo publico que devuelve una coleccion de Strings
     * @return Collection
     */
    @Override
    public Collection<String> getAllEstados() {
        return estados;
    }
    /**
     * Metodo publico que devuelve una coleccion de IncidenciasBean que coincidan
     * con una fecha que recibe
     * @param fecha fecha por la que filtrar
     * @param filtro Coleccion de inciodencias a filtrar
     * @return Collection
     */
    @Override
    public Collection<IncidenciaBean> getFiltradasFecha(String fecha,ObservableList<IncidenciaBean> filtro) {
        LOGGER.info("Filtradas por fecha igual a "+fecha);
        Collection<IncidenciaBean> coll=null;
        coll=filtro.stream().filter(i->i.getFecha().equals(fecha)).collect(Collectors.toList());
        return coll;
    }
    /**
     * Metodo publico que devuelve una coleccion de IncidenciasBean que coincidan
     * con una maquina que recibe
     * @param maquina nombre de maquina por la que filtrar
     * @param filtro Coleccion de inciodencias a filtrar
     * @return Collection
     */
    @Override
    public Collection<IncidenciaBean> getFiltradasMaquinas(String maquina,ObservableList<IncidenciaBean> filtro) {
        LOGGER.info("Filtradas por maquina igual a "+maquina);
        Collection<IncidenciaBean> coll=null;
        coll=filtro.stream().filter(i->i.getMaquina().equals(maquina)).collect(Collectors.toList());
        return coll;
    }
    /**
     * Metodo publico que devuelve una coleccion de IncidenciasBean que coincidan
     * con un estado que recibe
     * @param estado estado por el que filtrar
     * @param filtro Coleccion de inciodencias a filtrar
     * @return Collection
     */
    @Override
    public Collection<IncidenciaBean> getFiltradasEstados(String estado,ObservableList<IncidenciaBean> filtro) {
        LOGGER.info("Filtradas por estado igual a "+estado);
        Collection<IncidenciaBean> coll=null;
        coll=filtro.stream().filter(i->i.getEstado().equals(estado)).collect(Collectors.toList());
        return coll;
    }
    /**
     * Metodo publico que devuelve una coleccion de IncidenciasBean que coincidan
     * con una id que recibe
     * @param id id de incidencia por la que filtrar
     * @param filtro Coleccion de inciodencias a filtrar
     * @return Collection
     */
    @Override
    public Collection<IncidenciaBean> getFiltradasID(Integer id,ObservableList<IncidenciaBean> filtro) {
        LOGGER.info("Filtradas por id igual a "+id);
        Collection<IncidenciaBean> coll=null;
        coll= filtro.stream().filter(i-> i.getId()==id).collect(Collectors.toList());
        return coll;
    }
    /**
     * Metodo que añade a la lista de Incidencias un objeto IncidenciaBean
     * @param incidencia incidencia a eliminar
     */
    public void elimiarIncidencia (IncidenciaBean incidencia){
        if(incidencias.isEmpty()){
            LOGGER.info("No hay ningun dato");
        }else{
            for(int i=0;i<incidencias.size();i++){
                if(incidencias.get(i).getId()==incidencia.getId()){
                    incidencias.remove(i);
                    LOGGER.info("Incidencia eliminada");
                    break;
                }
            }
        }
    }
    /**
     * Metodo que modifica de la lista de Incidencias un objeto IncidenciaBean
     * @param incidencia incidencia a modificar
     */
    public void modificarIncidencia(IncidenciaBean incidencia){
        if(incidencias.isEmpty()){
            LOGGER.info("No hay ningun dato");
        }else{
            for(int i=0;i<incidencias.size();i++){
                if(incidencias.get(i).getId()==incidencia.getId()){
                    incidencias.set(i, incidencia);
                    LOGGER.info("Incidencia modificada");
                    break;
                    
                }
            }
        }
    }
    /**
     * Metodo que elimina de la lista de Incidencias un objeto IncidenciaBean
     * @param incidencia incidencia a añadir
     */
    public void añadirIncidencia(IncidenciaBean incidencia){
        incidencias.add(incidencia);
        LOGGER.info("Incidencia añadida");
    }
    /**
     * Metodo publico que recibe un objeto de IncidenciaBean
     * @param incidencia incidencia seleccionada
     */
    @Override
    public void setSelectedIncidencia(IncidenciaBean incidencia) {
        selectedIncidencia=incidencia;
    }
    /**
     * Metodo publico que devuelve una oleccion de IncidenciaBean
     * @return Collection 
     */
    @Override
    public Collection getSelectedIncidencia() {
        ArrayList <IncidenciaBean>  coleccion= new ArrayList<IncidenciaBean>();
        coleccion.add(selectedIncidencia);
        return coleccion;
    }
    /**
     * Metodo publico que devuelve la id maxima en un String
     * @return String
     */
    @Override
    public String getMaxID() {
        int maximaID=0;
        for(int i=0;i<incidencias.size();i++){
            if(incidencias.get(i).getId()>maximaID){
                maximaID=incidencias.get(i).getId();
            }
        }
        LOGGER.info("Id de incidencia maximo es "+maximaID);
        return String.valueOf(maximaID+1);
    }
    
}
