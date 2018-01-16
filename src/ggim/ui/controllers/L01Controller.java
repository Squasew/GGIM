/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.IncidenciasManagerTestGenerator;
import ggim.model.IncidenciasManager;
import ggim.model.GestionUsuarios;
import ggim.model.GestionUsuariosTest;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author isma
 */
public class L01Controller implements Initializable {
   //Logger logger;
    private Stage stage;
    private GestionUsuarios man;
    @FXML
    private Button btEntrar;
    @FXML
    private TextField txtUsu;
    @FXML
    private PasswordField txtPass;
    
    /**
     * Metodo plublico que recibe un objeto GestionUsuarios
     * @param man manager de GestionUsuarios
     */
    public void setManager(GestionUsuarios man){
        this.man=man;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    /**
     * Metodo publico que inicializa la ventana, que recibe como parametro un objeto Parent
     * @param root objeto Parent de la ventana 
     */
    public void initStage(Parent root) {
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        btEntrar.setDisable(false);
        txtPass.setEditable(true);
        txtUsu.setEditable(true);
    }
    /**
     * Metodo plublico que recibe un objeto Stage
     * @param stage Stage de la ventana
     */
    public void setStage(Stage stage) {
        this.stage=stage;
    }
    /**
     *  Metodo publico que devuelve un objeto Stage
     * @return Stage
     */
    public Stage getStage(){
        return this.stage;
    }
    /**
     * Metodo publico que comprueba si coinciden los datos de usuario y contraseña
     * @return boolean
     */
    private boolean comprobarDatos() {
        boolean ok=man.existUser(txtUsu.getText().toString(), txtPass.getText().toString());
        return ok;
    }
    /**
     * Metodo publico que comprueba si un usuario es administrador
     * @return boolean
     */
    private boolean isAdmin() {
        boolean ok=man.isAdmin(txtUsu.getText().toString());
        return ok;
    }
    /**
     * Metodo publico que carga la ventana de menu de administrador
     */
    private void cargarMenuAdmin() {
        try{
             
            FXMLLoader loader= new FXMLLoader(
                    getClass().getResource("/ggim/ui/fxml/MA00.fxml"));
            Parent root= (Parent)loader.load();
            MACcontroller controller= ((MACcontroller)loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
        }catch(IOException e){
            Logger.getLogger(GI01Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     * Metodo publico que carga la ventana de menu de usuario 
     */
    private void cargarMenuUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Metodo que es llamado cuando hay una accion de click en un boton
     * @param e evento
     */
    public void handleButtonClick(Event e){
        //se comprueban que los campos “texto Usuario” y “texto Contraseña” 
        //estén rellenados
        String u=txtUsu.getText().trim();
        String p=txtPass.getText().trim();
        boolean ok=false;
        if(u.isEmpty()||p.isEmpty()){
            Alert a= new Alert(Alert.AlertType.ERROR,"Error, los campos estan vacios",ButtonType.OK);
            a.show();
        }else{
            ok=comprobarDatos();
            if(ok){
           ok=isAdmin();
           if(ok){
               cargarMenuAdmin();
           }else {
               cargarMenuUser();
           }
        }else{
             Alert a= new Alert(Alert.AlertType.ERROR,"Error, los campos no coinciden",ButtonType.OK);
             a.show();
             txtUsu.setText("");
             txtPass.setText("");
        }
            
        }
        
        
    }
    
}
