/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.MaquinaBean;
import ggim.model.MaquinasManager;
import ggim.model.IncidenciaBean;
import ggim.model.IncidenciasManager;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Clase controladora de ventana GI02
 * @author Ismael Molano
 */
public class GI02Controller {
    private IncidenciaBean incidenciaSelected;
    private Stage stage;
    private ObservableList<IncidenciaBean> incidencias;
    private ObservableList<MaquinaBean> maquinas;
    private IncidenciasManager inciMan;
    private MaquinasManager maquiMan;
    private String dateFormat="dd/MM/yyyy";
            
    @FXML
    private TableView tablaIncidencia;
    
    @FXML
    private TableColumn colID;
    @FXML
    private TableColumn colEstado;
    @FXML
    private TableColumn colFecha;
    
    @FXML
    private TableView tablaMaquina;
     @FXML
    private TableColumn colMaquina;
    @FXML
    private TableColumn colEstadoMaquina;
    @FXML
    private TableColumn colModelo;
    @FXML
    private TableColumn colUr;
    @FXML
    private TableColumn colPr;
    @FXML
    private TextArea txtDescrip;
    @FXML
    private Label txtEstadoMaquina;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnVolver;
    @FXML
    private DatePicker txtFecha;
    @FXML
    private ComboBox <String> comboEstados;
    
    /**
     * Metodo publico que devuelve un objeto Stage
     * @return Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     *  Metodo plublico que recibe un objeto Stage
     * @param stage Stage de la ventana
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * Metodo plublico que recibe un objeto MaquinasManager
     * @param maquiMan manager de MaquinasManager
     */
    public void setMaquiMan(MaquinasManager maquiMan){
        this.maquiMan= maquiMan;
    }
    
    /**
     *  Metodo publico que devuelve un objeto MaquinasManager
     * @return MaquinasManager
     */
    public MaquinasManager getMaquiMan(){
        return this.maquiMan;
    }
    
    /**
     *  Metodo publico que devuelve un objeto IncidenciasManager
     * @return IncidenciasManager
     */
    public IncidenciasManager getMan() {
        return inciMan;
    }

    /**
     *  Metodo publico que recibe un objeto IncidenciasManager
     * @param man manager de IncidenciasManager
     */
    public void setMan(IncidenciasManager man) {
        this.inciMan = man;
    }

    /**
     *  Metodo publico que devuelve un objeto IncidenciaBean
     * @return IncidenciaBean
     */
    public IncidenciaBean getIncidenciaSelected() {
        return incidenciaSelected;
    }

    /**
     *  Metodo publico que recibe un objeto IncodenciaBean
     * @param incidenciaSelected incidencia seleccionada
     */
    public void setIncidenciaSelected(IncidenciaBean incidenciaSelected) {
        this.incidenciaSelected = incidenciaSelected;
    }
    
    /**
     *  Metodo publico que inicializa la ventana, que recibe como parametro un objeto Parent
     * @param root objeto Parent de la ventana
     */
    public void initStage(Parent root) {
        Scene scene= new Scene(root);
        stage.setTitle("Modificar incidencia");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        cargarTablaIncidencia();
        cargarTablaMaquina();
        cargarEstados();
        
        btnGuardar.setOnAction(this::buttonOnClick);
        btnVolver.setOnAction(this::buttonOnClick);
        StringConverter converter = new StringConverter<LocalDate>() {
                DateTimeFormatter dateFormatter =DateTimeFormatter.ofPattern(dateFormat);
                @Override
                public String toString(LocalDate date) {
                    if (date != null) {
                       return dateFormatter.format(date);
                    } else {
                     return "";
                    }
                }
                @Override
                public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {
                       return LocalDate.parse(string, dateFormatter);
                    } else {
                        return null;
                    }
                }
        };
        txtFecha.setConverter(converter);
        txtFecha.setPromptText(dateFormat);
        
    }
    /**
     * Metodo publico que carga de datos una tabla de tipo IncidenciaBean
     */
    private void cargarTablaIncidencia() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado")); 
        ArrayList<IncidenciaBean> lista= new ArrayList<IncidenciaBean>();
        lista.add(incidenciaSelected);
        incidencias= FXCollections.observableList(lista);
        tablaIncidencia.setItems(incidencias);
    }
    /**
     * Metodo publico usado  para cargar los estados en un ComboBox
     */
    private void cargarEstados() {
        ObservableList <String> estados= FXCollections.observableArrayList(inciMan.getAllEstados());
        comboEstados.setItems(estados);
        comboEstados.getSelectionModel().select(incidenciaSelected.getEstado());
    }
    /**
     * Metodo publico usado  para cargar los datos de una tqabla de tipo MaquinaBean
     */
    private void cargarTablaMaquina() {
        
        colMaquina.setCellValueFactory(new PropertyValueFactory<>("maquina"));
        colEstadoMaquina.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colUr.setCellValueFactory(new PropertyValueFactory<>("ultimaRevision"));
        colPr.setCellValueFactory(new PropertyValueFactory<>("proximaRevision"));
        maquinas=FXCollections.observableArrayList(maquiMan.getThisMaquina(incidenciaSelected.getMaquina()));
        tablaMaquina.setItems(maquinas);
        txtDescrip.appendText(maquinas.get(0).getDescripcion());
        txtEstadoMaquina.setText(maquinas.get(0).getEstado());
    }
    
    /**
     *  Metodo que es llamado cuando hay una accion de click en un boton
     * @param e evento
     */
    public void buttonOnClick(ActionEvent e){
        if(e.getSource().equals(btnGuardar)){
            guardar();
        }else if(e.getSource().equals(btnVolver)){
            volver();
        }
    }
    /**
     * Metodo publico para guardar los datos de un objeto MaquinaBean
     */
    private void guardar() {
        if(txtFecha.getEditor().getText().trim().equals("")){
            Alert a= new Alert(Alert.AlertType.ERROR,"Error, los campos estan vacios",ButtonType.OK);
            a.show();
        }else{
            if(comboEstados.getSelectionModel().getSelectedItem().equals("Sin selección")){
                Alert a= new Alert(Alert.AlertType.ERROR,"Error, hay que definir un estado correcto",ButtonType.OK);
            a.show();
            }else{
                IncidenciaBean i= new IncidenciaBean(incidenciaSelected.getId(), incidenciaSelected.getMaquina(), incidenciaSelected.getFecha(), comboEstados.getSelectionModel().getSelectedItem());
                inciMan.modificarIncidencia(i);
                volver();
            }
            
        }
    }
    /**
     * Metodo para volver a la ventana GI01 
     */
    private void volver() {
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/ggim/ui/fxml/GI01.fxml"));
            Parent root= (Parent) loader.load();
            GI01Controller controller= loader.getController();
            controller.setManager(inciMan);
            controller.setStage(stage);
            controller.initStage(root);
        }catch(IOException e){
            Logger.getLogger(GI01Controller.class.getName()).log(Level.SEVERE, null, e);
        }
            
    }
}
