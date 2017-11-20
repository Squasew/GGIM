/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.control;

import java.util.Collection;
import javafx.collections.ObservableList;

/**
 * Interfaz de IncidenciasManager
 * @author Ismael Molano
 */
public interface IncidenciasManager {

    /**
     * Metodo publico que devuelve la id maxima en un String
     * @return String
     */
    public String getMaxID();

    /**
     * Metodo publico que devuelve una coleccion de IncidenciasBean
     * @return Collection
     */
    public Collection getAllIncidencias();

    /**
     * Metodo publico que devuelve una coleccion de Strings
     * @return Collection
     */
    public Collection getAllMaquinas();

    /**
     * Metodo publico que devuelve una coleccion de Strings
     * @return Collection
     */
    public Collection getAllEstados();

    /**
     * Metodo publico que devuelve una coleccion de IncidenciasBean que coincidan
     * con una fecha que recibe
     * @param fecha fecha por la que filtrar
     * @param filtro Coleccion de inciodencias a filtrar
     * @return Collection
     */
    public Collection getFiltradasFecha(String fecha,ObservableList<IncidenciaBean> filtro);

    /**
     * Metodo publico que devuelve una coleccion de IncidenciasBean que coincidan
     * con una maquina que recibe
     * @param maquina nombre de maquina por la que filtrar
     * @param filtro Coleccion de inciodencias a filtrar
     * @return Collection
     */
    public Collection getFiltradasMaquinas(String maquina,ObservableList<IncidenciaBean> filtro);

    /**
     * Metodo publico que devuelve una coleccion de IncidenciasBean que coincidan
     * con un estado que recibe
     * @param estado estado de incidencia por la que filtrar
     * @param filtro Coleccion de inciodencias a filtrar
     * @return Collection
     */
    public Collection getFiltradasEstados(String estado,ObservableList<IncidenciaBean> filtro);

    /**
     * Metodo publico que devuelve una coleccion de IncidenciasBean que coincidan
     * con una id que recibe
     * @param id id de incidencia por la que filtrar
     * @param filtro Coleccion de inciodencias a filtrar
     * @return Collection
     */
    public Collection getFiltradasID(Integer id,ObservableList<IncidenciaBean> filtro);

    /**
     * Metodo que añade a la lista de Incidencias un objeto IncidenciaBean
     * @param incidencia incidencia a añadir
     */
    public void añadirIncidencia(IncidenciaBean incidencia);

    /**
     * Metodo que modifica de la lista de Incidencias un objeto IncidenciaBean
     * @param incidencia incidencia a modificar
     */
    public void modificarIncidencia(IncidenciaBean incidencia);

    /**
     * Metodo que elimina de la lista de Incidencias un objeto IncidenciaBean
     * @param incidencia incidencia a eliminar
     */
    public void elimiarIncidencia (IncidenciaBean incidencia);

    /**
     * Metodo publico que recibe un objeto de IncidenciaBean
     * @param incidencia incidencia seleccionada
     */
    public void setSelectedIncidencia(IncidenciaBean incidencia);

    /**
     * Metodo publico que devuelve una oleccion de IncidenciaBean
     * @return Collection 
     */
    public Collection getSelectedIncidencia();
}
