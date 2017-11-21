/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.GM01TextGen;
import ggim.model.GM01TextGenInterface;
import ggim.model.GestionUsuarios;
import ggim.model.GestionUsuariosTest;
import ggim.model.IncidenciasManager;
import ggim.model.IncidenciasManagerTestGenerator;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * Clase controladora de la ventana menuAdmin
 * @author Pedro Alonso Montejo
 */
public class MACcontroller {

    @FXML
        RadioButton b1;
    @FXML
        RadioButton b2;
    @FXML
        RadioButton b3;
    @FXML
        RadioButton b4;
    @FXML
        RadioButton b5;
    
    private Stage stage;
    private ToggleGroup tg;
    private static final Logger LOGGER = Logger.getLogger( MACcontroller.class.getName() );
    
    /**
     * Metodo que asigna el stage de la ventana al stage recibido
     * 
     * @param stage es el stage en el que se mostrará la ventana
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * Metodo que se ocupa de mostrar el contenido de la venana
     * 
     * @param root
     */
    public void initStage(Parent root) {
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        handleWindowShowing();
        this.stage.setTitle("Menu administrador");
        stage.show();    
        
    }
    
       
    /**
     * Metodo que devuelve al usuario a la ventana de login cuando pulsa este
     * botón
     * 
     * @throws IOException
     */
    public void btnVolver() throws IOException {
       
       GestionUsuarios man= new GestionUsuariosTest();
        FXMLLoader loader= new FXMLLoader(
                    getClass().getResource("/ggim/ui/fxml/L01.fxml"));
        Parent root= (Parent)loader.load();
        L01Controller controller= (L01Controller)loader.getController();
        controller.setStage(stage);
        controller.setManager(man);
        controller.initStage(root);
       
   }
    
    /**
     * Metodo que inicia los elementos de la ventana
     */
    public void handleWindowShowing() {
       
       tg = new ToggleGroup();
       b1.setToggleGroup(tg);
       b2.setToggleGroup(tg);
       b3.setToggleGroup(tg);
       b4.setToggleGroup(tg);
       b5.setToggleGroup(tg);
       b1.setDisable(true);
       b2.setDisable(true);
       b3.setDisable(true);
       
   }
    
    /**
     * Metodo que abre la ventana de administración especificada en la selección
     * de los radiobotones cuando se pulsa el botón
     * 
     * @throws IOException
     */
    public void btnHandler() throws IOException {
       
       if (tg.getSelectedToggle().equals(b4)) {
           
        FXMLLoader loader= new FXMLLoader(
                    getClass().getResource("/ggim/ui/fxml/GI01.fxml"));
        Parent root= (Parent)loader.load();
        IncidenciasManager man = new IncidenciasManagerTestGenerator() {};
        GI01Controller controller= (GI01Controller)loader.getController();
        controller.setStage(stage);
        controller.setManager(man);
        controller.initStage(root);
           
       } else if (tg.getSelectedToggle().equals(b5)) {
           
        FXMLLoader loader= new FXMLLoader(
                    getClass().getResource("/ggim/ui/fxml/GM01.fxml"));
        Parent root= (Parent)loader.load();
        GM01TextGenInterface gm01 = new GM01TextGen();
        GM01Controller controller= (GM01Controller)loader.getController();
        controller.setStage(stage,gm01);
        controller.initStage(root);
           
       }
       
   }
    
}
