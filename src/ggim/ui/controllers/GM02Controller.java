/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.MaquinaBean;
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
 *
 * @author Charly
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
    
    private Logger logger;
    private Stage stage;
    private GM01TextGenInterface gm01;
    private MaquinaBean mb;
    private String accion;
    
    ObservableList maquinasList;
    
    public void setStage(Stage primaryStage,GM01TextGenInterface gm01, MaquinaBean mb, String accion) {
        
        if (accion.equals("Añadir")) {
            this.mb = gm01.makeNew(mb);
        } else {
            this.mb = mb;
        }
        this.accion = accion;
        this.stage = primaryStage;
        this.gm01 = gm01;
    }
    
    public Stage getStage() {
        return this.stage;
    }
    
    public void initStage(Parent root) {
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        handleWindowShowing();
        this.stage.setTitle("Gestión de maquinas (Maquina específica)");
        stage.show();    
        
    }
    
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
        
        
    }
    
    public void bttnVolverHandler() throws IOException {
        
        FXMLLoader loader = 
                    new FXMLLoader(getClass().getResource("/ggim/ui/fxml/GM01.fxml"));
            Parent root =
                    (Parent) loader.load();
            GM01Controller gm01 =
                    ((GM01Controller)loader.getController());
            gm01.setStage(stage,this.gm01);
            gm01.initStage(root);
        
    }
    
    public void modeloChangeListener (ObservableValue observable,
            String oldValue, String newValue) {
        
         if (newValue.equals(mb.getMaquina())){
            
            añadir.setDisable(true);
            
        } else {
            
            añadir.setDisable(false);
            
        }
        
        modeloText.setText(gm01.getModeloName(newValue));
        modeloArea.setText(gm01.getModeloText(newValue));
        
    }
    
    public void bttnAñadirHandler() {
        
        mb.setMaquina(modeloCombo.getSelectionModel().getSelectedItem());
        añadir.setDisable(true);
        
    }
    
    public void bttnGuardarHandler() throws IOException {
        
        if (accion.equals("Modificar")) {
            
            gm01.modificarMaquina(mb);
            bttnVolverHandler();
            
        } else {
            
            gm01.getAllMaquinas().add(mb);
            bttnVolverHandler();
            
        }
        
    }
    
}
