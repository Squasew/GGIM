/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.control.IncidenciaBean;
import ggim.control.IncidenciasManager;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Ismael Molano
 */
public class GI03Controller {
    private Stage stage;
    private IncidenciasManager man;
    private ObservableList<IncidenciaBean> incidencias;
    private SimpleDateFormat dma= new SimpleDateFormat("dd/MM/yyyy");
    
    @FXML
    ComboBox <String> comboEstados;
    @FXML
    ComboBox <String> comboMaquinas;
    @FXML
    TextField txtID;
    @FXML
    TextField txtFecha;
    @FXML
    Button btnAccept;
    @FXML
    Button btnComp;
    @FXML
    Button btnVolver;
    @FXML
    private TableView tablaIncidencia;
    @FXML
    private TableColumn tcolumId;
    @FXML
    private TableColumn tcolumMaq;
    @FXML
    private TableColumn tcolumRev;
    @FXML
    private TableColumn tcolumEst;
    
    /**
     *  Metodo publico que devuelve un objeto Stage
     * @return
     */
    public Stage getStage() {
        return stage;
    }

    /**
     *  Metodo plublico que recibe un objeto Stage
     * @param stage stage Stage de la ventana
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Metodo publico que devuelve un objeto IncidenciasManager
     * @return IncidenciasManager
     */
    public IncidenciasManager getMan() {
        return man;
    }

    /**
     * Metodo publico que recibe un objeto IncidenciasManager
     * @param man manager de IncidenciasManager
     */
    public void setMan(IncidenciasManager man) {
        this.man = man;
    }
    
    /**
     * Metodo publico que inicializa la ventana, que recibe como parametro un objeto Parent
     * @param root objeto Parent de la ventana
     */
    public void initStage(Parent root){
        Scene scene= new Scene(root);
        stage.setTitle("Alta incidencias");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        cargarDatos();
        btnAccept.setDisable(false);
        btnAccept.setOnAction(this::buttonOnClick);
        btnComp.setDisable(false);
        btnComp.setOnAction(this::buttonOnClick);
        btnVolver.setOnAction(this::buttonOnClick);
        
    }
    /**
     * Metodo publico que carga los datos de la venta al inicializar
     */
    private void cargarDatos() {
        ObservableList <String> maquinas= FXCollections.observableArrayList(man.getAllMaquinas());
        comboMaquinas.setItems(maquinas);
        comboMaquinas.getSelectionModel().selectFirst();
        ObservableList <String> estados= FXCollections.observableArrayList(man.getAllEstados());
        comboEstados.setItems(estados);
        comboEstados.getSelectionModel().selectFirst();
        txtID.setText(man.getMaxID());
        txtID.setDisable(true);
        Date date= new Date();
        txtFecha.setText(dma.format(date));
        txtFecha.setDisable(true);
        
       
    }
    
    /**
     *  Metodo que es llamado cuando hay una accion de click en un boton
     * @param e evento
     */
    public void buttonOnClick(ActionEvent e){
        
        if(e.getSource().equals(btnAccept)){
            boolean ok=aniadirATabla(0);
            if(ok){
                volver();
            }
            
        }
        else if(e.getSource().equals(btnComp)){
            aniadirATabla(1);
        }
        else if(e.getSource().equals(btnVolver)){
            volver();
        }
    }
    /**
     * Metodo publico que almacena datos de tipo IncidenciaBean y devuelve un boolean a 
     * true si lo ha hechpo bien o false si no lo ha conseguido. Recibe un Integer
     * que sera 0 o 1 depende del boton que le ha llamado  
     * @param control integer que controla que boton a realizado el evento
     * @return boolean
     */
    private boolean aniadirATabla(int control) {
        boolean ok=false;
        String estado=comboEstados.getSelectionModel().getSelectedItem();
        String maquina=comboMaquinas.getSelectionModel().getSelectedItem();
        if(maquina.equals("Sin selección")||estado.equals("Sin selección")){
            Alert a= new Alert(Alert.AlertType.ERROR,"Error, estado o maquina seleccionado es incorrecto",ButtonType.OK);
            a.show();
        }else {
            IncidenciaBean i= new IncidenciaBean(Integer.parseInt(txtID.getText().toString()), maquina, txtFecha.getText().toString(), estado);
            tcolumId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcolumMaq.setCellValueFactory(new PropertyValueFactory<>("maquina"));
            tcolumRev.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            tcolumEst.setCellValueFactory(new PropertyValueFactory<>("estado"));
            ArrayList<IncidenciaBean> lista= new ArrayList<IncidenciaBean>();
            lista.add(i);
            incidencias= FXCollections.observableList(lista);
            tablaIncidencia.setItems(incidencias);
            if(control==0){
                man.añadirIncidencia(i);
            }
           ok=true;
        }
            
        return ok;
    }
    /**
     * Metodo publico que carga  la ventana GI01
     */
    private void volver() {
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/ggim/ui/fxml/GI01.fxml"));
            Parent root= (Parent) loader.load();
            GI01Controller controller= loader.getController();
            controller.setManager(man);
            controller.setStage(stage);
            controller.initStage(root);
        }catch(IOException e){
            Logger.getLogger(GI01Controller.class.getName()).log(Level.SEVERE, null, e);
        }
            
    }
    
}
