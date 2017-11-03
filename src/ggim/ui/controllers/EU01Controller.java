/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author ubuntu
 */
public class EU01Controller {
    
    @FXML
            private TableView tabla;
    @FXML
            private TableColumn tbColNom;
    @FXML
            private TableColumn tbColEntr;
    @FXML
            private TableColumn tbColFech;
    @FXML
            private TableColumn tbColDur;
          
    Logger logger;
    private Stage stage;
    private EU01TextGenInterface eu01;
    
    public void setStage(Stage primaryStage,EU01TextGenInterface eu01) {
        this.stage = primaryStage;
        this.eu01 = eu01;
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
        
        //TODO Ocuparse mas tarde del estadod de creacion
        
        tbColNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbColEntr.setCellValueFactory(new PropertyValueFactory<>("entrenador"));
        tbColFech.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tbColDur.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        
        ObservableList entrenamientosList =
                FXCollections.observableArrayList(eu01.getAllEntrenamientos());
        
        tabla.setItems(entrenamientosList);
        
    }
    

    
}
