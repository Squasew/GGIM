/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.GM01TextGenInterface;
import ggim.beans.MaquinaBeanPedro;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

/**
 * Clase controladora de la ventana GM01
 * 
 * @author Pedro Alonso Montejo
 */
public class GM01Controller {
    
    //Definimos los elemntos de la ventana
        //Primero los botones
    
    @FXML
            private Button volver;
    @FXML
            private Button limpiar;
    @FXML
            private Button filtrar;
    @FXML
            private Button eliminar;
    @FXML
            private Button modificar;
    @FXML
            private Button añadir;
    
        //Ahora definimos el resto de los campos
    
    @FXML
            private TextField idText;
    @FXML
            private ComboBox <String> maquinaCombo;
    @FXML
            private DatePicker fechaPicker;
    @FXML
            private ComboBox <String> estadoCombo;
    
        //Definimos los elementos de la tabla
    
    @FXML
            private TableView tabla;
    @FXML
            private TableColumn tbColID;
    @FXML
            private TableColumn tbColMaq;
    @FXML
            private TableColumn tbColRev;
    @FXML
            private TableColumn tbColEst;
    
    //Definimos las variables extra que necesitaremos
    
    private static final Logger LOGGER = Logger.getLogger( GM01Controller.class.getName() );
    private Stage stage;
    private GM01TextGenInterface gm01;
    
    ObservableList maquinasList;
    
    /**
     * Metodo que asigna al stage de la ventana el stage recibido 
     * 
     * @param primaryStage es el stage en el que se inicializará la ventana
     * @param gm01 es el administrador de datos de la ventana
     */
    public void setStage(Stage primaryStage,GM01TextGenInterface gm01) {
        this.stage = primaryStage;
        this.gm01 = gm01;
    }
    
    /**
     *Metodo que devuelve el stage de esta ventana
     * 
     * @return devuelve el stage de la ventana
     */
    public Stage getStage() {
        return this.stage;
    }
    
    /**
     * Metodo que inicializa la ventana
     * 
     * @param root
     */
    public void initStage(Parent root) {
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        handleWindowShowing();
        this.stage.setTitle("Gestión de máquinas");
        stage.show();    
        
    }
    
    /**
     * Metodo que se ocupa de inicializar el contenido de la ventana cada vez
     * que esta se muestra
     */
    public void handleWindowShowing () {
        
        //Definimos el estado de los elemtentos de la ventana
        
        limpiar.setDisable(true);
        filtrar.setDisable(true);
        eliminar.setDisable(true);
        modificar.setDisable(true);
        
        //Definimos que tipo de datos va a ser asignado a cada fila de la tabla
        
        tbColID.setCellValueFactory(new PropertyValueFactory<>("iD"));
        tbColMaq.setCellValueFactory(new PropertyValueFactory<>("maquina"));
        tbColRev.setCellValueFactory(new PropertyValueFactory<>("revision"));
        tbColEst.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        //Generamos los datos mediante el metodo .getAllMaquinas
        
        maquinasList =
                FXCollections.observableArrayList(gm01.getAllMaquinas());
        
        //Asignamos los datos creados a la tabla
        
        tabla.setItems(maquinasList);
    
        //Definimos un listener para los cambios de selección en la tabla
        
        tabla.getSelectionModel()
                .selectedItemProperty()
                .addListener(this::handleCambioSeleccionMaquina);
        
        //Definimos los datos de los combos maquina y estado
            //Primero definimos los del combo maquina
            
        ObservableList modelosList =
                FXCollections.observableArrayList(gm01.getAllModelos());
        
        maquinaCombo.setItems(modelosList);
        maquinaCombo.getSelectionModel().selectFirst();
        
            //Ahora definimos los del combo estado
            
        ArrayList <String> estados = new ArrayList();
            estados.add("Sin estado");
            estados.add("Usable");
            estados.add("Fuera de uso");
        
        ObservableList estadosList =
                FXCollections.observableArrayList(estados);
        
        estadoCombo.setItems(estadosList);
        estadoCombo.getSelectionModel().selectFirst();
        
        //Definimos listeners para cambios en los campos de busqueda avanzada
        
        idText.textProperty().addListener(this::idTextListener);
        fechaPicker.getEditor().textProperty().addListener(this::fechaChangeListener);
        maquinaCombo.valueProperty().addListener(this::maquinaChangeListener);
        estadoCombo.valueProperty().addListener(this::estadoChangeListener);
        
        fechaPicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd/MM/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                fechaPicker.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        
        LOGGER.info("LOG: Se muestra la ventana de control de maquinas 01");
        
    }
    
        
    /**
     * Metodo que devuelve al administrador a la ventana de menú de administrador
     */
    public void bttnVolver (){
        
        try {
            
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/ggim/ui/fxml/MA00.fxml"));
            Parent root= (Parent) loader.load();
            MACcontroller controller= (MACcontroller)loader.getController();
            controller.setStage(stage);
            controller.initStage(root);
            
            LOGGER.info("LOG: Se cierra la ventana de control de maquinas 01");
            
        } catch (IOException e) {
            
            LOGGER.info("LOG: Se ha producido un error al cerrar la ventana de"
                    + "control de maquinas 01");
            
        }
        
    }
    
    /**
     * Metodo que controla el botón limpiar. Limpia los campos de busqueda y
     * reinicia los filtros de esta.
     */
    public void handleBotonLimpiar () {
        
        idText.textProperty().setValue("");
        fechaPicker.getEditor().setText("");
        maquinaCombo.getSelectionModel().selectFirst();
        estadoCombo.getSelectionModel().selectFirst();
        
        tabla.setItems(maquinasList);
        
        limpiar.setDisable(true);
        
        LOGGER.info("LOG: Se limpian los filtros y campos de busqueda");
        
    }
    
    /**
     * Metodo que controla el botón filtrar. Filtra la tabla con los datos
     * introducidos en los campos de busqueda avanzada.
     */
    public void handleBotonFiltrar () {
        
        ObservableList <MaquinaBeanPedro> maquinasFilter = maquinasList;
        
        LOGGER.info("LOG: Se va a realizar un filtrado");
        
        if (idText.textProperty().getValue().trim().equals("")) {
            
            if (!maquinaCombo.getSelectionModel().getSelectedItem().equals("Sin modelo")) {
                
                maquinasFilter = gm01.filterModelo(maquinasFilter,maquinaCombo.getSelectionModel().getSelectedItem());
                
                LOGGER.info("LOG: Se filtra por modelo");
                
            }
            
            if (!fechaPicker.getEditor().getText().trim().equals("")) {
                
                maquinasFilter = gm01.filterFecha(maquinasFilter,fechaPicker.getEditor().getText().trim());
                
                LOGGER.info("LOG: Se filtra por fecha");
                
            }
            
            if (!estadoCombo.getSelectionModel().getSelectedItem().equals("Sin estado")) {
                
                maquinasFilter = gm01.filterEstado(maquinasFilter,estadoCombo.getSelectionModel().getSelectedItem());
                
                LOGGER.info("LOG: Se filtra por estado");
                
            }

            tabla.setItems(maquinasFilter);
            tabla.refresh();
            
        } else {
            
            maquinasFilter = gm01.filterID(maquinasFilter,idText.textProperty().getValue());
            tabla.setItems(maquinasFilter);
            tabla.refresh();
            
            LOGGER.info("LOG: Se filtra por id");
            
        }
        
        limpiar.setDisable(false);
        
    }
    
    /**
     * Metodo que controla los cambios de selección en la tabla de la ventana.
     * Habilita los campos "botón Eliminar" y "boton Modificar" cuando hay un
     * cambio de slección.
     * 
     * @param observable es el valor que puede ser modificado
     * @param oldValue es el valor anterior al actual
     * @param newValue es el nuevo valor adoptado
     */
    public void handleCambioSeleccionMaquina (ObservableValue observable,
            Object oldValue, Object newValue) {
        
        eliminar.setDisable(false);
        modificar.setDisable(false);
        
        LOGGER.info("LOG: Se ha cambiado la selección en la máquina");
        
    }  
        
    /**
     * Metodo que controla los cambios en el campo de texto editable de la
     * busqueda avanzada. Habilita o deshabilita los campos "botón Filtrar" así
     * como el resto de campos del apartado de busqueda avanzada.
     * 
     * @param observable es el valor que puede ser modificado
     * @param oldValue es el valor anterior al actual
     * @param newValue es el nuevo valor adoptado
     */
    public void idTextListener (ObservableValue observable,
            String oldValue, String newValue) {
        
        if (!newValue.trim().equals("")){
            
            maquinaCombo.setDisable(true);
            fechaPicker.setDisable(true);
            estadoCombo.setDisable(true);
            filtrar.setDisable(false);
            
            LOGGER.info("LOG: El texto id tiene un id");
            
        } else {
            
            maquinaCombo.setDisable(false);
            fechaPicker.setDisable(false);
            estadoCombo.setDisable(false);
            filtrar.setDisable(true);
            
            LOGGER.info("LOG: El texto id no tiene un id");
            
        }
        
    }
    
    /**
     * Metodo que controla los cambios de selección en el comboBox de las
     * distintas maquinas. Habilita o deshabilita
     * los campos "botón Filtrar" y "textField ID" dependiendo del contenido de
     * los campos "combo Estado", "fecha Revision" y este mismo campo.
     * 
     * @param observable es el valor que puede ser modificado
     * @param oldValue es el valor anterior al actual
     * @param newValue es el nuevo valor adoptado
     */
    public void maquinaChangeListener (ObservableValue observable,
            String oldValue, String newValue){
    
        if (!newValue.equals("Sin modelo")){
            
            idText.setDisable(true);
            filtrar.setDisable(false);
            
            LOGGER.info("LOG: El combo modelo tiene un modelo");
            
        } else if (newValue.equals("Sin modelo")) {
            
            
            if (fechaPicker.getEditor().getText().trim().equals("")
                && estadoCombo.getSelectionModel().getSelectedItem().equals("Sin estado")) {
              
                idText.setDisable(false);
                filtrar.setDisable(true);
                
                LOGGER.info("LOG: El combo modelo está sin modelo");
                
            }
            
        }
        
    }
    
        
    /**
     * Metodo que comtrola los campos de selección de fecha en el selector de
     * fechas. Habilita o deshabilita
     * los campos "botón Filtrar" y "textField ID" dependiendo del contenido de
     * los campos "combo Estado", "comboMaquina" y este mismo campo.
     * 
     * @param observable es el valor que puede ser modificado
     * @param oldValue es el valor anterior al actual
     * @param newValue es el nuevo valor adoptado
     */
    public void fechaChangeListener (ObservableValue observable,
            String oldValue, String newValue) {
        
        if (!newValue.trim().equals("")){
            
            idText.setDisable(true);
            filtrar.setDisable(false);
            
            LOGGER.info("LOG: El combo estado tiene una fecha");
            
        } else if (newValue.trim().equals("")
                    && maquinaCombo.getValue().equals("Sin modelo")
                    && estadoCombo.getValue().equals("Sin estado")) {
            
            idText.setDisable(false);
            filtrar.setDisable(true);
            
            LOGGER.info("LOG: El datePicker revisión está sin fecha");
            
        }
        
    }
    
    /**
     * Metodo que controla los cambios de seleccion en el comboBox de los
     * distintos estados. Habilita o deshabilita
     * los campos "botón Filtrar" y "textField ID" dependiendo del contenido de
     * los campos "combo Maquina", "fecha Revision" y este mismo campo.
     * 
     * @param observable es el valor que puede ser modificado
     * @param oldValue es el valor anterior al actual
     * @param newValue es el nuevo valor adoptado
     */
    public void estadoChangeListener (ObservableValue observable,
            String oldValue, String newValue){
    
        if (!newValue.equals("Sin estado")){
            
            idText.setDisable(true);
            filtrar.setDisable(false);
            
            LOGGER.info("LOG: El combo estado tiene un estado");
            
        } else if (newValue.equals("Sin estado")) {
            
            if (fechaPicker.getEditor().getText().trim().equals("")
                && maquinaCombo.getSelectionModel().getSelectedItem().equals("Sin modelo")) {
              
                idText.setDisable(false);
                filtrar.setDisable(true);
                
                LOGGER.info("LOG: El combo estado está sin estado");
                
            }
            
        }
        
    }
    
    /**
     * Metodo que controla el botón eliminar. Elimina el registro seleccionado
     * en la tabla de los registros de maquinas.
     */
    public void bttnEliminarHandler () {
    
        gm01.eliminarMaquina((MaquinaBeanPedro)tabla.getSelectionModel().getSelectedItem());
        
        tabla.getItems().remove(tabla.getSelectionModel().getSelectedItem());
        
        tabla.refresh();
        
        LOGGER.info("LOG: Se eliminan registros de la tabla");
        
    }

        
    /**
     * Metodo que controla el botón modificar. Abre una nueva ventana con los
     * datos que se envían como parametro (Un MaquinaBean recogido de la tabla)
     * que permite modificar el registro.
     */
    public void bttnModificarHandler () {
        
        try {
            FXMLLoader loader = 
                    new FXMLLoader(getClass().getResource("/ggim/ui/fxml/GM02.fxml"));
            Parent root =
                    (Parent) loader.load();
            GM02Controller gm02 =
                    ((GM02Controller)loader.getController());
            gm02.setStage(stage, gm01, (MaquinaBeanPedro)tabla.getSelectionModel().getSelectedItem(),"Modificar");
            gm02.initStage(root);
            
            LOGGER.info("LOG: Se cierra la ventana de control de maquinas 01");  
            
        } catch (IOException e) {
            
            LOGGER.info("LOG: Error al cerrar la ventana de control de maquinas"
                    + " 01 mediante el botón modificar"); 
            
        }
        
        
            
    }
     
    /**
     * Metodo que controla el botón añadir. Abre una nueva ventana con datos
     * generados automaticamente que permite añadir un nuevo registro.
     */
    public void bttnAñadirHandler (){
        
            try {
                FXMLLoader loader = 
                    new FXMLLoader(getClass().getResource("/ggim/ui/fxml/GM02.fxml"));
                Parent root =
                        (Parent) loader.load();
                GM02Controller gm02 =
                        ((GM02Controller)loader.getController());
                gm02.setStage(stage, gm01, null,"Añadir");
                gm02.initStage(root);
                
                LOGGER.info("LOG: Se cierra la ventana de control de maquinas 01");
                
            } catch (IOException e) {
                
                LOGGER.info("LOG: Error al cerrar la ventana de control de maquinas"
                    + " 01 mediante el botón añadir"); 
                
            }
           
    }
    
}
