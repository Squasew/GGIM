/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.model.EstadoMaquina;
import ggim.model.GM01TextGenInterface;
import ggim.model.MaquinaBeanPedro;
import ggim.model.ModeloBean;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * Clase controladora de la ventana GM02
 * 
 * @author Pedro Alonso Montejo
 */
public class GM02Controller {
    
    //Definimos los elemntos de la ventana
        
    @FXML
        private TextField txtID;
    @FXML
        private TextField txtRevision;
    @FXML
        private TextField txtPrevision;
    @FXML
        private TextField txtEstado;
    @FXML
        private TextField txtModelo;
    @FXML
        private TextArea txtModo;
    @FXML
        private DatePicker dateRevision;
    @FXML
        private DatePicker datePrevision;
    @FXML
        private ComboBox comboEstado;
    @FXML
        private ComboBox comboModelo;
    
    private static final Logger LOGGER = Logger.getLogger( GM02Controller.class.getName() );
    private Stage stage;
    private GM01TextGenInterface gm01;
    private MaquinaBeanPedro mb;
    private MaquinaBeanPedro mb2;
    private String accion;
    
    
    
    /**
     * Metodo que asigna al stage de la ventana el stage recibido
     * 
     * @param primaryStage es el stage que se le establecerá a la ventana
     * @param gm01 es el gestor de datos que se le establecerá a la ventana
     * @param mb es el maquina bean que se utilizará en esta ventana
     * @param accion es la acción que se está realizando en esta ventana
     */
    public void setStage(Stage primaryStage,GM01TextGenInterface gm01, MaquinaBeanPedro mb, String accion) {
        
        if (accion.equals("Añadir")) {
            
            this.mb = new MaquinaBeanPedro();
            
            int maxID = 0;
            ArrayList <MaquinaBeanPedro> maquinas =
                    (ArrayList <MaquinaBeanPedro>) gm01.getAllMaquinas();
            
            for (MaquinaBeanPedro m : maquinas) {
                
                if (m.getID()>=maxID) {
                    maxID = m.getID()+1;
                }
                
            }
            
            this.mb.setID(maxID);
            
        } else {
            this.mb = mb;
        }
        
        this.accion = accion;
        this.stage = primaryStage;
        this.gm01 = gm01;
        this.mb2 = new MaquinaBeanPedro();
        
    }
    
    /**
     * Metodo que devuelve el stage de la ventana actual
     * 
     * @return devuelve el stage de la ventana actual
     */
    public Stage getStage() {
        return this.stage;
    }
    
    /**
     * Metodo que inicializa la ventana
     * 
     * @param root
     */
    public void initStage(Parent root) {
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        handleWindowShowing();
        this.stage.setTitle("Gestión de maquinas (Maquina específica)");
        stage.show();    
        
    }
    
    /**
     * Metodo que establece el estado de los elementos en la creación de la
     * ventana
     */
    public void handleWindowShowing () {
        
        txtID.setEditable(false);
        txtRevision.setEditable(false);
        txtPrevision.setEditable(false);
        txtEstado.setEditable(false);
        txtModelo.setEditable(false);
        txtModo.setEditable(false);
        
        txtID.setText(String.valueOf(mb.getID()));
        
        if (this.accion.equals("Modificar")) {
            txtRevision.setText(mb.getRevision());
            txtPrevision.setText(mb.getPrevision());
            txtEstado.setText(mb.getEstado().toString());
            txtModelo.setText(mb.getMaquina().toString());
            txtModo.setText(mb.getMaquina().getModoEmp());
        } else {
            txtRevision.setText("No hay datos disponibles");
            txtPrevision.setText("No hay datos disponibles");
            txtEstado.setText("No hay datos disponibles");
            txtModelo.setText("No hay datos disponibles");
            txtModo.setText("No hay datos disponibles");
        }
        
        ArrayList <ModeloBean> modelos =
                (ArrayList <ModeloBean>) gm01.getAllModelos2();
        
        ObservableList modelosList =
                FXCollections.observableArrayList(modelos);
        
        comboModelo.setItems(modelosList);
        comboModelo.getSelectionModel().selectFirst();
        
        ArrayList <String> estados = new ArrayList();
            estados.add("Reparación");
            estados.add("Reparada");
        
        ObservableList estadosList =
                FXCollections.observableArrayList(estados);
        
        comboEstado.setItems(estadosList);
        comboEstado.getSelectionModel().selectFirst();
        
        datePrevision.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd/MM/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                datePrevision.setPromptText(pattern.toLowerCase());
            }

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
        });
        
        dateRevision.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd/MM/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                dateRevision.setPromptText(pattern.toLowerCase());
            }

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
        });
        
        LOGGER.info("LOG: Se muestra la ventana de control de maquinas 02");
        
    }
    
    /**
     * Metodo que controla el botón volver
     */
    public void bttnVolverHandler(){
        
        try {
            
            FXMLLoader loader = 
                    new FXMLLoader(getClass().getResource("/ggim/ui/fxml/GM01.fxml"));
            Parent root =
                    (Parent) loader.load();
            GM01Controller gm01 =
                    ((GM01Controller)loader.getController());
            gm01.setStage(stage,this.gm01);
            gm01.initStage(root);
            
            LOGGER.info("LOG: Se cierra la ventana de control de maquinas 02");
            
        } catch (IOException e) {
            
            LOGGER.info("LOG: Se ha producido un error al cerrar la ventana de"
                    + "control de maquinas 02");
            
        }
        
    }
    
    /**
     * Metodo que controla el botón guardar. Guarda los cambios realizados o
     * añade el registro de una máquina nueva.
     * 
     * @throws IOException
     */
    public void bttnGuardarHandler() throws IOException {
        
        if (dateRevision.getEditor().getText().trim().equals("")
                    || datePrevision.getEditor().getText().trim().equals("")) {
                
            JOptionPane.showMessageDialog(null, "Hay campos sin completar");
            
        } else {
            
            mb2.setID(mb.getID());
            
            if (comboEstado.getSelectionModel().getSelectedItem().equals(EstadoMaquina.Reparación)) {
                mb2.setEstado(EstadoMaquina.Reparación);
            } else {
                mb2.setEstado(EstadoMaquina.Reparada);
            }
            
            mb2.setPrevision(datePrevision.getValue().toString());
            mb2.setRevision(dateRevision.getValue().toString());
            mb2.setMaquina((ModeloBean) comboModelo.getSelectionModel().getSelectedItem());
            
            
            if (accion.equals("Modificar")) {

                gm01.modificarMaquina(mb2);
                bttnVolverHandler();

                LOGGER.info("LOG: Se ha modificado un registro de máquina");
            
            } else {

                gm01.makeNew(mb2);
                bttnVolverHandler();
                
            }
        
            
        }
        
    }
    
}
