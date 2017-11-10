/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

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
            private ComboBox maquinaCombo;
    @FXML
            private DatePicker fechaPicker;
    @FXML
            private ComboBox estadoCombo;
    
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
        
    }
    
    public void handleCambioSeleccionMaquina (ObservableValue observable,
            Object oldValue, Object newValue){
    
        
        
}
    

    
}
