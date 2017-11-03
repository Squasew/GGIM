/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.control;

import ggim.ui.controllers.EU01Controller;
import ggim.ui.controllers.EU01TextGen;
import ggim.ui.controllers.EU01TextGenInterface;
import ggim.ui.controllers.MA01Controller;
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
    
        EU01TextGenInterface eu01tg = 
                new EU01TextGen();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ggim/ui/fxml/EU01.fxml"));
        
        Parent root =
                (Parent) loader.load();
        EU01Controller mu01 =
                ((EU01Controller)loader.getController());
        mu01.setStage(primaryStage,eu01tg);
        mu01.initStage(root);
        
    }
  
    
    
}
