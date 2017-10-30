/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.control;

import ggim.ui.controllers.MU01Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author ubuntu
 */
public class GGIM extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
    
        FXMLLoader loader =
                FXMLLoader.load(getClass().getResource("ggim/ui/fxml/MenuAdministrados.fxml"));
        
        Parent root =
                (Parent) loader.load();
        MU01Controller mu01 =
                ((MU01Controller)loader.getController());
        mu01.setStage(primaryStage);
        mu01.initStage(root);
        
    }
  
    
    
}
