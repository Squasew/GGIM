/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ggim.model.GestionUsuarios;
import ggim.model.GestionUsuariosTest;
import ggim.ui.controllers.GI01Controller;
import ggim.model.IncidenciasManager;
import ggim.model.IncidenciasManagerTestGenerator;
import ggim.ui.controllers.L01Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Clase aplicacion
 * @author Ismael Molano
 */
public class GIMM extends Application {
    /**
     * Metodo publico que crea la ventana L01
     * @param stage stage de la ventana
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        
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
     * Metodo main
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
