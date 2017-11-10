/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.control;

import ggim.ui.controllers.EU01Controller;
import ggim.ui.controllers.EU01TextGen;
import ggim.ui.controllers.EU01TextGenInterface;
import ggim.ui.controllers.GM01Controller;
import ggim.ui.controllers.GM01TextGen;
import ggim.ui.controllers.GM01TextGenInterface;
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
        
        FXMLLoader loader = 
                    new FXMLLoader(getClass().getResource("/ggim/ui/fxml/GM01.fxml"));
            GM01TextGenInterface gm01TG =
                    new GM01TextGen();
            Parent root =
                    (Parent) loader.load();
            GM01Controller gm01 =
                    ((GM01Controller)loader.getController());
            gm01.setStage(primaryStage,gm01TG);
            gm01.initStage(root);
        
    }
  
    
    
}
