/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import java.awt.Event;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author ubuntu
 */
public class MA01Controller {
    
    @FXML
    RadioButton gestUsuarios;
    @FXML
    RadioButton gestTarifas;
    @FXML
    RadioButton gestContratos;
    @FXML
    RadioButton gestIncidencias;
    @FXML
    RadioButton gestMaquinas;
    @FXML
    RadioButton verEntrenamientos;
    @FXML
    Button btnSeleccionar;
    
    Logger logger;
    private Stage stage;
    
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
    
    public Stage getStage() {
        return this.stage;
    }
    
    public void initStage(Parent root) {
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnShowing(this::handleWindowShowing);
        stage.show();
        
    }
    
    public void handleWindowShowing (WindowEvent e) {
        
        //Definimos el estado de la ventana cuando se muestra
        
        //Añadimos todos los radioButton al ToggleGroup
        ToggleGroup botonesAdmin = new ToggleGroup();
        gestUsuarios.setToggleGroup(botonesAdmin);
        gestTarifas.setToggleGroup(botonesAdmin);
        gestContratos.setToggleGroup(botonesAdmin);
        gestIncidencias.setToggleGroup(botonesAdmin);
        gestMaquinas.setToggleGroup(botonesAdmin);
        verEntrenamientos.setToggleGroup(botonesAdmin);
        
        //Establecemos el radioButton seleccionado por defecto
        gestUsuarios.setSelected(true);
        
    }
    
}