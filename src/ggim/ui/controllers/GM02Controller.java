/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.GM01TextGenInterface;
import ggim.beans.MaquinaBeanPedro;
import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Clase controladora de la ventana GM02
 * 
 * @author Pedro Alonso Montejo
 */
public class GM02Controller {
    
    //Definimos los elemntos de la ventana
        //Primero los botones
    
    @FXML
            private Button añadir;
    @FXML
            private Button guardar;
    
    //Ahora definimos el resto de los campos
    
    @FXML
            private TextField modeloText;
    @FXML
            private ComboBox <String> modeloCombo;
    @FXML
            private TextArea modeloArea;
    
        //Definimos los elementos de la tabla
    
    @FXML
            private TableView tabla;
    @FXML
            private TableColumn tbColID;
    @FXML
            private TableColumn tbColMod;
    @FXML
            private TableColumn tbColRev;
    @FXML
            private TableColumn tbColPrev;
    @FXML
            private TableColumn tbColEst;
    
    private static final Logger LOGGER = Logger.getLogger( GM02Controller.class.getName() );
    private Stage stage;
    private GM01TextGenInterface gm01;
    private MaquinaBeanPedro mb;
    private String accion;
    
    ObservableList maquinasList;
    
    /**
     * Metodo que asigna al stage de la ventana el stage recibido
     * 
     * @param primaryStage es el stage que se le establecerá a la ventana
     * @param gm01 es el gestor de datos que se le establecerá a la ventana
     * @param mb es el maquina bean que se utilizará en esta ventana
     * @param accion es la acción que se está realizando en esta ventana
     */
    public void setStage(Stage primaryStage,GM01TextGenInterface gm01, MaquinaBeanPedro mb, String accion) {
        
        if (accion.equals("Añadir")) {
            this.mb = gm01.makeNew(mb);
        } else {
            this.mb = mb;
        }
        this.accion = accion;
        this.stage = primaryStage;
        this.gm01 = gm01;
    }
    
    /**
     * Metodo que devuelve el stage de la ventana actual
     * 
     * @return devuelve el stage de la ventana actual
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
        this.stage.setTitle("Gestión de maquinas (Maquina específica)");
        stage.show();    
        
    }
    
    /**
     * Metodo que establece el estado de los elementos en la creación de la
     * ventana
     */
    public void handleWindowShowing () {
        
        //Definimos que tipo de datos va a ser asignado a cada fila de la tabla
        
        tbColID.setCellValueFactory(new PropertyValueFactory<>("iD"));
        tbColMod.setCellValueFactory(new PropertyValueFactory<>("maquina"));
        tbColEst.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tbColRev.setCellValueFactory(new PropertyValueFactory<>("revision"));
        tbColPrev.setCellValueFactory(new PropertyValueFactory<>("prevision"));

        //Insertamos en la tabla el elemento que nos interesa
        
        tabla.setItems(gm01.getCertain(mb));   
        
        //Insertamos los modelos en el comboBox
        
        ObservableList modelosList =
                FXCollections.observableArrayList(gm01.getAllModelos2());
        
        modeloCombo.setItems(modelosList);
        modeloCombo.getSelectionModel().select(gm01.getIndex(mb));
        
        //Marcamos el estado de los demás elementos
        modeloText.setDisable(true);
        modeloArea.setDisable(true);
        añadir.setDisable(true);
        
        modeloText.setText(gm01.getModeloName(mb.getMaquina()));
        modeloArea.setText(gm01.getModeloText(mb.getMaquina()));
        
        //Añadirmos listeners para el combo
        modeloCombo.valueProperty().addListener(this::modeloChangeListener);
        
        LOGGER.info("LOG: Se muestra la ventana de control de maquinas 02");
        
    }
    
    /**
     * Metodo que controla el botón volver
     */
    public void bttnVolverHandler(){
        
        try {
            
            FXMLLoader loader = 
                    new FXMLLoader(getClass().getResource("/ggim/ui/fxml/GM01.fxml"));
            Parent root =
                    (Parent) loader.load();
            GM01Controller gm01 =
                    ((GM01Controller)loader.getController());
            gm01.setStage(stage,this.gm01);
            gm01.initStage(root);
            
            LOGGER.info("LOG: Se cierra la ventana de control de maquinas 02");
            
        } catch (IOException e) {
            
            LOGGER.info("LOG: Se ha producido un error al cerrar la ventana de"
                    + "control de maquinas 02");
            
        }
        
    }
    
    /**
     * Metodo que controla los cambios en el combo Modelo. Si el modelo
     * seleccionado es distinto al actual, permite añadirlo modificando
     * el modelo de la maquina actual.
     * 
     * @param observable es el valor que puede ser modificado
     * @param oldValue es el valor anterior al actual
     * @param newValue es el nuevo valor adoptado
     */
    public void modeloChangeListener (ObservableValue observable,
            String oldValue, String newValue) {
        
         if (newValue.equals(mb.getMaquina())){
            
            añadir.setDisable(true);
            LOGGER.info("LOG: El nuevo valor de modelo es igual al actual");
            
        } else {
            
            añadir.setDisable(false);
            LOGGER.info("LOG: El nuevo valor de modelo es diferente al actual");
            
        }
        
        modeloText.setText(gm01.getModeloName(newValue));
        modeloArea.setText(gm01.getModeloText(newValue));
    }
    
    /**
     * Metodo que controla el botón modificar. Modifica el modelo actual de la
     * máquina por el nuevo modelo seleccionado
     */
    public void bttnModificarHandler() {
        
        mb.setMaquina(modeloCombo.getSelectionModel().getSelectedItem());
        añadir.setDisable(true);
        tabla.refresh();
        
        LOGGER.info("LOG: El valor del modelo ha sido actualizado");
        
    }
    
    /**
     * Metodo que controla el botón guardar. Guarda los cambios realizados o
     * añade el registro de una máquina nueva.
     * 
     * @throws IOException
     */
    public void bttnGuardarHandler() throws IOException {
        
        if (accion.equals("Modificar")) {
            
            gm01.modificarMaquina(mb);
            bttnVolverHandler();
            
            LOGGER.info("LOG: Se ha modificado un registro de máquina");
            
        } else {
            
            gm01.getAllMaquinas().add(mb);
            bttnVolverHandler();
            
            LOGGER.info("LOG: Se ha añadido un registro de máquina");
            
        }
        
    }
    
}
