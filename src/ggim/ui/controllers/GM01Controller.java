/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import java.util.ArrayList;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author ubuntu
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
    
    Logger logger;
    private Stage stage;
    private GM01TextGenInterface gm01;
    
    public void setStage(Stage primaryStage,GM01TextGenInterface gm01) {
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
        stage.setOnShowing(this::handleWindowShowing);
        this.stage.setTitle("Gestión de máquinas");
        stage.show();    
        
    }
    
    public void handleWindowShowing (WindowEvent e) {
    
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
        
        ObservableList maquinasList =
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
        
    }
    
    public void handleCambioSeleccionMaquina (ObservableValue observable,
            Object oldValue, Object newValue) {
        
        eliminar.setDisable(false);
        modificar.setDisable(false);
        
    }
    
    public void maquinaChangeListener (ObservableValue observable,
            String oldValue, String newValue){
    
        if (!newValue.trim().equals("Sin modelo")){
            
            idText.setDisable(true);
            filtrar.setDisable(false);
            
        } else if (newValue.trim().equals("Sin modelo")
                    && fechaPicker.getValue().toString().trim().equals("")
                    && estadoCombo.getValue().equals("Sin estado")) {
            
            idText.setDisable(false);
            filtrar.setDisable(true);
            
        }
        
    }
    
    public void estadoChangeListener (ObservableValue observable,
            String oldValue, String newValue){
    
        if (!newValue.trim().equals("Sin estado")){
            
            idText.setDisable(true);
            filtrar.setDisable(false);
            
        } else if (newValue.trim().equals("Sin estado")
                    && fechaPicker.getValue().toString().trim().equals("")
                    && maquinaCombo.getValue().equals("Sin modelo")) {
            
            idText.setDisable(false);
            filtrar.setDisable(true);
            
        }
        
    }
    
    public void bttnEliminarHandler () {
    
        tabla.getItems().remove(tabla.getSelectionModel().getSelectedItem());
        
        tabla.refresh();
        
    }
    
    public void idTextListener (ObservableValue observable,
            String oldValue, String newValue) {
        
        if (!newValue.trim().equals("")){
            
            maquinaCombo.setDisable(true);
            fechaPicker.setDisable(true);
            estadoCombo.setDisable(true);
            filtrar.setDisable(false);
            
        } else {
            
            maquinaCombo.setDisable(false);
            fechaPicker.setDisable(false);
            estadoCombo.setDisable(false);
            filtrar.setDisable(true);
            
        }
        
    }
    
    public void fechaChangeListener (ObservableValue observable,
            String oldValue, String newValue) {
        
        if (!newValue.trim().equals("")){
            
            idText.setDisable(true);
            filtrar.setDisable(false);
            
        } else if (newValue.trim().equals("")
                    && maquinaCombo.getValue().equals("Sin modelo")
                    && estadoCombo.getValue().equals("Sin estado")) {
            
            idText.setDisable(false);
            filtrar.setDisable(true);
            
        }
        
    }
    
}
