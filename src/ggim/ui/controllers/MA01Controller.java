/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import java.awt.Event;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author ubuntu
 */
public class MA01Controller {

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
        
        //TODO estado creación de la ventana
        
        
        
    }
    
}
