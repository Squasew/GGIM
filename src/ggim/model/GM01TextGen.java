/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

import ggim.model.MaquinaBeanPedro;
import ggim.model.ModeloBean;
import ggim.rest.MaquinaRestClient;
import ggim.rest.ModeloRestClient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.ws.rs.core.GenericType;

/**
 * Clase que se encarga de manejar los datos de las ventanas de Máquinas
 * 
 * @author Pedro Alonso Montejo
 */
public class GM01TextGen implements GM01TextGenInterface{

    private static final Logger LOGGER = Logger.getLogger( GM01TextGen.class.getName() );
    
    private MaquinaRestClient maquinaClient;
    private ModeloRestClient modeloClient;
    
    /**
     * Metodo costructor que añade valores a los diferentes ArrayList que serán
     * necesarios en las ventanas
     */
    public GM01TextGen(){
        
        maquinaClient = new MaquinaRestClient();
        modeloClient = new ModeloRestClient();
        
        LOGGER.info("Gestión: Se han inicializado los valores del gestor de datos");
            
    }
    
    /**
     * Metodo que devuelve todos los registros de máquinas existentes
     * 
     * @return devuelve todos los registros de máquinas existentes
     */
    @Override
    public Collection getAllMaquinas() {
        
        LOGGER.info("Gestión: Se van a cargar los datos de las maquinas");
        
        List<MaquinaBeanPedro> list = maquinaClient.findAll_XML(new GenericType<List<MaquinaBeanPedro>> () {});
        
        LOGGER.info("Gestión: Se han enviado todas las maquinas");
        
        return list;
        
    }
    
    /**
     * Metodo que devuelve todos los modelos para el arraylist de la ventana gm01
     * 
     * @return devuelve todos los modelos para el arraylist de la ventana gm01
     */
    @Override
    public Collection getAllModelos() {
        
        LOGGER.info("Gestión: Se van a cargar todos los modelos + Sin selección");
        
        ArrayList <String> modelos = new ArrayList <> ();
        modelos.add("Sin selección");
        
        List <ModeloBean> list = modeloClient.findAll_XML(new GenericType<List<ModeloBean>> () {});
        for (ModeloBean l : list) {
            modelos.add(l.getModelo());
        }
        
        LOGGER.info("Gestión: Se han cargado todos los modelos + Sin selección");
        
        return modelos;
        
    }

    /**
     * Metodo que devuelve todos los modelos para el arraylist de la ventana gm02
     * @return devuelve todos los modelos para el arraylist de la ventana gm02
     */
    @Override
    public Collection getAllModelos2() {
        
        LOGGER.info("Gestión: Se van a cargar todos los modelos");
        
        List <ModeloBean> list = modeloClient.findAll_XML(new GenericType<List<ModeloBean>> () {});
        LOGGER.info("Gestión: Se han cargado todos los modelos");
        
        return list;
        
    }
    
    /**
     * Metodo que filtra por el ID recibido como parametro
     * 
     * @param maquinasFilter es la OL que se filtrará
     * @param value es el ID por el que se filtrará
     * @return una OL filtrada por el ID
     */
    @Override
    public ObservableList<MaquinaBeanPedro> filterID(ObservableList<MaquinaBeanPedro> maquinasFilter, String value) {
        
        int id = Integer.parseInt(value);
        
        Collection <MaquinaBeanPedro> maquinaFiltered =
                maquinasFilter.stream().filter(maquina -> maquina.getID() == id).collect(Collectors.toList());
        
        ObservableList <MaquinaBeanPedro> maquinasFilteredFinished =
                FXCollections.observableArrayList(maquinaFiltered);
        
        return maquinasFilteredFinished;
        
    }

    /**
     * Metodo que filtra por el estado recibido como parametro
     * 
     * @param maquinasFilter es la OL que se filtrará
     * @param selectedItem es el estado por el que se filtrará
     * @return una OL filtrada por el estado
     */
    @Override
    public ObservableList<MaquinaBeanPedro> filterEstado(ObservableList<MaquinaBeanPedro> maquinasFilter, String selectedItem) {
        
        Collection <MaquinaBeanPedro> maquinaFiltered =
                maquinasFilter.stream().filter(maquina -> maquina.getEstado().equals(selectedItem)).collect(Collectors.toList());
        
        ObservableList <MaquinaBeanPedro> maquinasFilteredFinished =
                FXCollections.observableArrayList(maquinaFiltered);
        
        return maquinasFilteredFinished;
        
    }

    /**
     * Metodo que filtra por la fecha recibida como parametro
     * 
     * @param maquinasFilter es la OL que se filtrará
     * @param trim es la fecha por la que se filtrará
     * @return una OL filtrada por la fecha
     */
    @Override
    public ObservableList<MaquinaBeanPedro> filterFecha(ObservableList<MaquinaBeanPedro> maquinasFilter, String trim) {
        
        Collection <MaquinaBeanPedro> maquinaFiltered =
                maquinasFilter.stream().filter(maquina -> maquina.getRevision().equals(trim)).collect(Collectors.toList());
        
        ObservableList <MaquinaBeanPedro> maquinasFilteredFinished =
                FXCollections.observableArrayList(maquinaFiltered);
        
        return maquinasFilteredFinished;
        
    }

    /**
     * Metodo que filtra por la máquina recibido como parametro
     * 
     * @param maquinasFilter es la OL que se filtrará
     * @param selectedItem es la máquina por la que se filtrará
     * @return una OL filtrada por la máquina
     */
    @Override
    public ObservableList<MaquinaBeanPedro> filterModelo(ObservableList<MaquinaBeanPedro> maquinasFilter, String selectedItem) {
        
        Collection <MaquinaBeanPedro> maquinaFiltered =
                maquinasFilter.stream().filter(maquina -> maquina.getMaquina().equals(selectedItem)).collect(Collectors.toList());
        
        ObservableList <MaquinaBeanPedro> maquinasFilteredFinished =
                FXCollections.observableArrayList(maquinaFiltered);
        
        return maquinasFilteredFinished;
        
    }

    /**
     * Metodo que crea una observable list de un elemento para la tabla de la
     * ventana gm02
     * 
     * @param mb el elemento que contendrá la OL
     * @return una observable list de un elemento para la tabla de la ventana gm02
     */
    @Override
    public ObservableList getCertain(MaquinaBeanPedro mb) {
        
        ArrayList <MaquinaBeanPedro> beans=
            new ArrayList();
               
        beans.add(mb);
        
        Collection <MaquinaBeanPedro> maquinaFiltered =
                FXCollections.observableArrayList(beans);
        
        ObservableList <MaquinaBeanPedro> maquinasFilteredFinished =
                FXCollections.observableArrayList(maquinaFiltered);
        
        LOGGER.info("Gestión: Se ha creado un registro de OL para la tabla de"
                + "la ventana gestión de máquinas 02");
        
        return maquinasFilteredFinished;
        
    }

    /**
     * Metodo que crea un nuevo MaquinaBeanPedro con un ID superior a todos los
     * ID existentes
     * 
     * @param mb el MaquinaBeanPedro que se inicializará
     * @return un nuevo MaquinaBeanPedro
     */
    @Override
    public void makeNew(MaquinaBeanPedro mb) {
        
        maquinaClient.create_XML(mb);
        
        LOGGER.info("Gestión: Se ha creado una máquina");
        
    }

    /**
     * Metodo que modifica el registro de la máquina enviada como parametro
     * 
     * @param mb es la máquina que será modificada
     */
    @Override
    public void modificarMaquina(MaquinaBeanPedro mb) {
        
        maquinaClient.edit_XML(mb, String.valueOf(mb.getID()));
        
        LOGGER.info("Gestión: Se ha modificado una máquina");
        
    }

    /**
     * Metodo que establece que elemento del combo modelo debe estar deshabilitado
     * en la ventana gm02 dependiendo del modelo de la máquina enviada como
     * parametro
     * 
     * @param mb es la máquina enviada como parametro
     * @return la posición del combo modelo que se deshabilitará
     */
    @Override
    public int getIndex(MaquinaBeanPedro mb) {
        
        int index = 0;
        
        /*for (int i = 0; i < modelosCombo2.size(); i++) {
            if (modelosCombo2.get(i).equals(mb.getMaquina()))
                index = i; 
        }*/
        
        LOGGER.info("Gestión: Se ha enviado la posición del combo a deshabilitar");
        
        return index;
        
    }

    /**
     * Metodo que devuelve el nombre del modelo del identificador de modelo
     * enviado como parametro
     * 
     * @param modelo es el identificador del modelo enviado como parametro
     * @return el nombre del modelo en cuestión
     */
    @Override
    public String getModeloName(String modelo) {
        
        String name = "";
        
        /*for (int i = 0; i < modelos.size(); i++) {
            if ((modelos.get(i).getModelo()).equals(modelo))
                name = modelos.get(i).getModelo();
        }*/
        
        LOGGER.info("Gestión: Se ha enviado el nombre del modelo");
        
        return name;
        
    }

    /**
     * Metodo que devuelve el modo de empleo del identificador de modelo enviado
     * como parametro
     * 
     * @param modelo es el identificador del modelo enviado como parametro
     * @return el modo de empleo del modelo en cuestión
     */
    @Override
    public String getModeloText(String modelo) {
        
        String text = "";
        
        /*for (int i = 0; i < modelos.size(); i++) {
            if ((modelos.get(i).getModelo()).equals(modelo))
                text = modelos.get(i).getModoEmp();
        }*/
        
        LOGGER.info("Gestión: Se ha enviado el modo de empleo del modelo");
        
        return text;
        
    }
    
    /**
     * Metodo que elimina una máquina del registro de máquinas
     * 
     * @param mb la máquina que se eliminará del registro
     */
    @Override
    public void eliminarMaquina(MaquinaBeanPedro mb) {
        
        maquinaClient.remove(String.valueOf(mb.getID()));
        
        LOGGER.info("Gestión: Se ha eliminado el registro de una máquina");
        
    }

}
