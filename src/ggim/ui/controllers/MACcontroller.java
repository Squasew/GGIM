/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.control.GestionUsuarios;
import ggim.control.GestionUsuariosTest;
import ggim.control.IncidenciasManager;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Charly
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
    private IncidenciasManager man;
    private GM01TextGenInterface gm01;
    private ToggleGroup tg;
    
    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setManager(IncidenciasManager inciMan, GM01TextGenInterface gm01) {
        if(inciMan != null)
            this.man= inciMan;
        if(gm01 != null)
            this.gm01 = gm01;
    }

    void initStage(Parent root) {
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        handleWindowShowing();
        this.stage.setTitle("Menu administrador");
        stage.show();    
        
    }
    
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
    
   public void btnHandler() throws IOException {
       
       if (tg.getSelectedToggle().equals(b4)) {
           
        FXMLLoader loader= new FXMLLoader(
                    getClass().getResource("/ggim/ui/fxml/GI01.fxml"));
        Parent root= (Parent)loader.load();
        GI01Controller controller= (GI01Controller)loader.getController();
        controller.setStage(stage);
        controller.setManager(man);
        controller.initStage(root);
           
       } else if (tg.getSelectedToggle().equals(b5)) {
           
        FXMLLoader loader= new FXMLLoader(
                    getClass().getResource("/ggim/ui/fxml/GM01.fxml"));
        Parent root= (Parent)loader.load();
        GM01Controller controller= (GM01Controller)loader.getController();
        controller.setStage(stage,gm01);
        controller.initStage(root);
           
       }
       
   }
   
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
   
}
