/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

import ggim.beans.IncidenciaBean;
import ggim.rest.IncideciasRestClient;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.ws.rs.core.GenericType;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author ubuntu
 */
public class IncdenciasManagerImplementation implements IncidenciasManager {
    private IncideciasRestClient webClient;
    private static final Logger LOGGER = Logger.getLogger( IncidenciasManagerTestGenerator.class.getName() );
    
    public IncdenciasManagerImplementation (){
        webClient= new IncideciasRestClient();
    }
    
    @Override
    public Integer getMaxIncidenciaID() {
        List <IncidenciaBean> incidencias= (List <IncidenciaBean>) getAllIncidencias();
        Integer id=0;
        LOGGER.info("IncidenciasMAnagerImplementation: getMaxIncidenciaID: buscando id incidencia maxima ");
        for(int i=0;i<incidencias.size();i++){
            if(incidencias.get(i).getId()>id){
                id=incidencias.get(i).getId();
            }
        }
        LOGGER.log(Level.INFO, "IncidenciasMAnagerImplementation: getMaxIncidenciaID: id incidencia encontrada {0}",id);
        return id+1;
    }

    @Override
    public Collection getAllMaquinas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getAllEstados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getAllIncidencias() {
        LOGGER.info("IncidenciasMAnagerImplementation: getAllIncidencias: obteniendo todas las incidencias");
        List <IncidenciaBean> incidencias=webClient.findAll(new GenericType<List<IncidenciaBean>>() {});
        LOGGER.log(Level.INFO,"IncidenciasMAnagerImplementation: getAllIncidencias: obtenidas {0} incidencias", incidencias.size());
        return incidencias;
    }

    @Override
    public Collection getFiltradasFecha(String fecha, ObservableList<IncidenciaBean> filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getFiltradasMaquinas(String maquina, ObservableList<IncidenciaBean> filtro) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getFiltradasEstados(String estado, ObservableList<IncidenciaBean> filtro) {
        LOGGER.info("IncidenciasManagerImplementation: getFiltradasEstados: buscando incidencias por estado");
        List <IncidenciaBean> incidencias= webClient.findByEstado(new GenericType<List <IncidenciaBean>>() {}, estado);
        if(incidencias.size()>0){
            LOGGER.log(Level.INFO,"IncidenciasManagerImplementation: getFiltradasEstados: encontradas {0} incidencias",incidencias.size());
        }else{
            LOGGER.info("IncidenciasManagerImplementation: getFiltradasEstados: no se han encontrado con esa id");
        }
        
        return incidencias;
    }

    @Override
    public Collection getFiltradasID(Integer id, ObservableList<IncidenciaBean> filtro) {
        LOGGER.info("IncidenciasManagerImplementation: getFiltradasID: obteniendo incidencia por id");
        List <IncidenciaBean> incidencias= webClient.find(new GenericType<List<IncidenciaBean>>() {}, id);
        if(incidencias.size()>0){
            LOGGER.log(Level.INFO,"IncidenciasManagerImplementation: getFiltradasID: encontrada {0} incidencia",incidencias.size());
        }else{
           LOGGER.info("IncidenciasManagerImplementation: getFiltradasID: no se han encontrado con esa id");
        }
        return incidencias;
    }

    @Override
    public void añadirIncidencia(IncidenciaBean incidencia) {
        LOGGER.info("IncidenciaManagerImplementation: añadirIncidencia: añadiendo incidencia con id:"+incidencia.getId());
        webClient.create(incidencia);
    }

    @Override
    public void modificarIncidencia(IncidenciaBean incidencia) {
        LOGGER.info("IncidenciaManagerImplementation: modificarIncidencia: modificando incidencia con id:"+incidencia.getId());
        webClient.update(incidencia);
    }

    @Override
    public void elimiarIncidencia(IncidenciaBean incidencia) {
        LOGGER.info("IncidenciaManagerImplementation: añadirIncidencia: añadiendo incidencia con id:"+incidencia.getId());
        webClient.delete(incidencia.getId());
    }
}
