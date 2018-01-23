/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.ui.controllers;

import ggim.beans.EstadoIncidencia;
import ggim.model.MaquinasManagerTestGenerator;
import ggim.model.MaquinasManager;
import ggim.beans.IncidenciaBean;
import ggim.beans.MaquinaBean;
import ggim.model.IncidenciasManager;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Clase controladora de ventana GI01
 *
 * @author Ismael Molano
 */
public class GI01Controller {

    private Stage stage;
    private static final Logger LOGGER = Logger.getLogger(GI01Controller.class.getName());
    private final String dateFormat = "dd/MM/yyyy";
    private IncidenciasManager man;
    @FXML
    private TableView tablaIncidencias;
    @FXML
    private TableColumn tcolumId;
    @FXML
    private TableColumn tcolumMaq;
    @FXML
    private TableColumn tcolumRev;
    @FXML
    private TableColumn tcolumEst;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnAniadir;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnFiltrar;
    @FXML
    private ComboBox comboMaquinas;
    @FXML
    private ComboBox comboEstados;
    @FXML
    private TextField txtID;
    @FXML
    private DatePicker txtDate;
    private ObservableList<IncidenciaBean> incidencias;

    /**
     * Metodo publico que recibe un objeto Stage
     *
     * @param stage Stage de la ventana
     *
     */
    public void setStage(Stage stage, IncidenciasManager man) {
        this.stage = stage;
        this.man = man;
    }

    /**
     * Metodo que devuelve el stage de esta ventana
     *
     * @return devuelve el stage de la ventana
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Metodo publico que inicializa la ventana, que recibe como parametro un
     * objeto Parent
     *
     * @param root objeto Parent de la ventana
     */
    public void initStage(Parent root) {

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        cargarDatos();
        stage.setTitle("Gestión de incidencias");
        stage.show();
        LOGGER.info("ventana cargada, con datos");

    }

    /**
     * Metodo publico que prepara la los datos de la ventana al cargar
     */
    private void cargarDatos() {

        //Deshabilitar los botones de Eliminar, Modificar, Filtrar, Limpiar estan deshabilitados
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
        btnFiltrar.setDisable(true);
        btnLimpiar.setDisable(true);

        //Se definen los tipos de datos que va a contener cada fila de la tabla
        tcolumId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcolumMaq.setCellValueFactory(new PropertyValueFactory<>("maquina"));
        tcolumRev.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tcolumEst.setCellValueFactory(new PropertyValueFactory<>("estado"));

        //Se buscan todas las incidencias con el metodo getAllIncidencias
        incidencias
                = FXCollections.observableArrayList(man.getAllIncidencias());

        //Se asignan las incidencias a la tabla 
        tablaIncidencias.setItems(incidencias);

        //Se define un listener para la seleccion de filas en la tabla
        tablaIncidencias.getSelectionModel()
                .selectedItemProperty()
                .addListener(this::handleIncidenciasTableSelectionChange);

        //Se cargan los datos de los ComboBox
        //Se cargan los estados
        AniadirEstados();
        //Se cargan las Maquinas
        AniadirMaquinas();

        //Se añaden los escuchadores de eventos a los botones 
        
        btnEliminar.setOnAction(this::buttonOnClick);
        btnAniadir.setOnAction(this::buttonOnClick);
        btnModificar.setOnAction(this::buttonOnClick);
        btnFiltrar.setOnAction(this::buttonOnClick);
        btnLimpiar.setOnAction(this::buttonOnClick);
        btnVolver.setOnAction(this::buttonOnClick);
        
        //Se añaden los escuchadores de cambios de textos a los campos de ID, Fecha, ComboBox Estados y ComboBox Maquinas
        comboEstados.valueProperty().addListener(this::comboEstadosChangeListener);
        comboMaquinas.valueProperty().addListener(this::comboMaquinasChangeListener);
        txtID.textProperty().addListener(this::textChangeListener);
        txtDate.getEditor().textProperty().addListener(this::dateChangeListener);
        //Convertidor de fecha 
        txtDate.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

            {
                txtDate.setPromptText(dateFormat);
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

    }

    /**
     * Metodo publico utilizado para cargar las maquinas en un comboBox
     */
    private void AniadirMaquinas() {
        ObservableList<MaquinaBean> maquinas = FXCollections.observableArrayList(man.getAllMaquinas());

        comboMaquinas.setItems(maquinas);
        comboMaquinas.getSelectionModel().selectFirst();

        LOGGER.info("Maquinas añadidas");

    }

    /**
     * Metodo publico utilizado para cargar los estados en un ComboBox
     */
    private void AniadirEstados() {
        ObservableList<EstadoIncidencia> estados = FXCollections.observableArrayList(man.getAllEstados());
        comboEstados.setItems(estados);
        comboEstados.getSelectionModel().selectFirst();
        LOGGER.info("Estados añadidos");
    }

    /**
     * Metodo listener que es llamado al hacerclick en alguna de las filas de la
     * tabla, habilita los botones de Eliminar, Añadir y Modificar tabla
     *
     * @param observable valor que puede cambiar
     * @param oldValue antiguo valor
     * @param newValue nuevo valor
     */
    public void handleIncidenciasTableSelectionChange(ObservableValue observable, Object oldValue, Object newValue) {
        if (newValue != null) {
            btnEliminar.setDisable(false);
            btnAniadir.setDisable(false);
            btnModificar.setDisable(false);
            LOGGER.info("bonones " + btnAniadir + ", " + btnEliminar + "y " + btnModificar + " habilitados");
        }
    }

    /**
     * Metodo publico que es llamado cuando hay una modifocacion en un campo de
     * fecha
     *
     * @param observable valor que puede cambiar
     * @param oldValue antiguo valor
     * @param newValue nuevo valor
     */
    public void dateChangeListener(ObservableValue observable, String oldValue, String newValue) {
        if (!(newValue.trim().equals(""))) {
            txtID.setDisable(true);
            btnFiltrar.setDisable(false);
            btnLimpiar.setDisable(false);
            LOGGER.info(txtID + " deshabilitado," + btnLimpiar + " y " + btnFiltrar + " habilitados");
        } else {
            EstadoIncidencia estado = (EstadoIncidencia) comboEstados.getSelectionModel().getSelectedItem();
            MaquinaBean maquina = (MaquinaBean) comboMaquinas.getSelectionModel().getSelectedItem();
            if (estado == null && maquina == null) {
                txtID.setDisable(false);
                btnFiltrar.setDisable(true);
                LOGGER.info(txtID + " habilitado, " + btnFiltrar + " deshabilitado");
            }
        }
    }

    /**
     * Metodo publico que es llamado cuando hay una modifocacion en un campo de
     * texto
     *
     * @param observable valor que puede cambiar
     * @param oldValue antiguo valor
     * @param newValue nuevo valor
     */
    public void textChangeListener(ObservableValue observable, String oldValue, String newValue) {
        if (!(newValue.trim().equals(""))) {
            btnFiltrar.setDisable(false);
            comboEstados.setDisable(true);
            comboMaquinas.setDisable(true);
            txtDate.setDisable(true);
            LOGGER.info(btnFiltrar + " habilitado, " + comboEstados + ", " + comboMaquinas + " y " + txtDate + " deshabilitados");
        } else {
            btnFiltrar.setDisable(true);
            comboEstados.setDisable(false);
            comboMaquinas.setDisable(false);
            txtDate.setDisable(false);
            btnLimpiar.setDisable(false);
            LOGGER.info(btnFiltrar + " deshabilitado, " + comboEstados + ", " + comboMaquinas + " y " + txtDate + " habilitados");
        }
    }

    /**
     * Metodo publico que es llamado cuando hay una modifocacion en una
     * seleccion de un comboBox
     *
     * @param observable valor que puede cambiar
     * @param oldValue antiguo valor
     * @param newValue nuevo valor
     */
    public void comboEstadosChangeListener(ObservableValue observable, String oldValue, String newValue) {
        if (!(newValue.equals("Sin selección"))) {
            txtID.setDisable(true);
            btnFiltrar.setDisable(false);
            btnLimpiar.setDisable(false);
            LOGGER.info(txtID + " deshabilitado, " + btnFiltrar + " y " + btnLimpiar + " habilitados");
        } else {
            String date = txtDate.getEditor().getText().trim();
            MaquinaBean maquina = (MaquinaBean) comboMaquinas.getSelectionModel().getSelectedItem();
            if (date.equals("") && maquina == null) {
                txtID.setDisable(false);
                btnFiltrar.setDisable(true);
                LOGGER.info(btnFiltrar + " deshabilitado, " + txtID + " habilitado");
            }
        }
    }

    /**
     * Metodo publico que es llamado cuando hay una modifocacion en una
     * seleccion de un comboBox
     *
     * @param observable valor que puede cambiar
     * @param oldValue antiguo valor
     * @param newValue nuevo valor
     */
    public void comboMaquinasChangeListener(ObservableValue observable, String oldValue, String newValue) {
        if (!(newValue.equals("Sin selección"))) {
            txtID.setDisable(true);
            btnFiltrar.setDisable(false);
            btnLimpiar.setDisable(false);
            LOGGER.info(txtID + " deshabilitado, " + btnFiltrar + " y " + btnLimpiar + " habilitados");
        } else {
            String date = txtDate.getEditor().getText().trim();
            EstadoIncidencia estado = (EstadoIncidencia) comboEstados.getSelectionModel().getSelectedItem();
            try {
                if (date.equals("") && estado != null && estado == null) {
                    txtID.setDisable(false);
                    btnFiltrar.setDisable(true);
                    LOGGER.info(txtID + " deshabilitado, " + btnFiltrar + " habilitado");
                }
            } catch (Exception e) {
                LOGGER.info("Error ocurrido en comboMaquinasChangeListener(ObservableValue observable, String oldValue, String newValue)");

            }

        }
    }

    /**
     * Metodo publico que es llamado cuando hay una accion de click en un boton
     *
     * @param e evento
     */
    public void buttonOnClick(ActionEvent e) {

        if (e.getSource().equals(btnFiltrar)) {
            filtrar();
            LOGGER.info(btnFiltrar + " pulsado");
        } else if (e.getSource().equals(btnLimpiar)) {
            limpiar();
            LOGGER.info(btnLimpiar + " pulsado");
        } else if (e.getSource().equals(btnEliminar)) {
            IncidenciaBean incidencia = (IncidenciaBean) tablaIncidencias.getSelectionModel().getSelectedItem();
            eliminar(incidencia);
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
            LOGGER.info(btnEliminar + " pulsado");
        } else if (e.getSource().equals(btnAniadir)) {
            aniadir();
            LOGGER.info(btnAniadir + " pulsado");
        } else if (e.getSource().equals(btnModificar)) {
            IncidenciaBean incidencia = (IncidenciaBean) tablaIncidencias.getSelectionModel().getSelectedItem();
            modificar(incidencia);
            LOGGER.info(btnModificar + " pulsado");
        } else if (e.getSource().equals(btnVolver)) {
            volver();
            LOGGER.info(btnVolver + " pulsado");
        }
    }

    /**
     * Metodo publico que filtra por distintos campos los objetos de una lista
     */
    private void filtrar() {
        try {
            ObservableList<IncidenciaBean> filtro = incidencias;
            if (txtID.isDisabled()) {
                //Buscamos por fecha
                if (!(txtDate.getEditor().getText().trim().equals(""))) {
                    SimpleDateFormat dma = new SimpleDateFormat("dd/MM/yyyy");
                    filtro = FXCollections.observableArrayList(man.getFiltradasFecha(dma.parse(txtDate.getEditor().getText())));
                }
                //Buscamos por nombre de maquina
                if (!(comboMaquinas.getSelectionModel().getSelectedItem() == null)) {
                    MaquinaBean maquina = (MaquinaBean) comboMaquinas.getSelectionModel().getSelectedItem();
                    filtro = FXCollections.observableArrayList(man.getFiltradasMaquinas(maquina));
                }
                //Buscamos por estado
                if (!(comboEstados.getSelectionModel().getSelectedItem().equals("Sin selección"))) {
                    EstadoIncidencia estado = (EstadoIncidencia) comboEstados.getSelectionModel().getSelectedItem();
                    filtro = FXCollections.observableArrayList(man.getFiltradasEstados(estado));
                }
                LOGGER.info("Filtros realizados");
            } else {
                //Buscamos por id
                filtro = FXCollections.observableArrayList(man.getFiltradasID(Integer.parseInt(txtID.getText())));
                LOGGER.info("Filtro por id realizado");
            }

            tablaIncidencias.setItems(filtro);
            LOGGER.info("Insertando datos filtrados a la tabla");
        } catch (ParseException e) {
            //Aqui avisar al usuario que la fecha no es correcta
        }
    }

    /**
     * Metodo publico que se encarga de resetear los campos de la ventana, a
     * como estaban anteriormente
     */
    private void limpiar() {
        tablaIncidencias.setItems(incidencias);
        txtDate.getEditor().setText("");
        txtID.setText("");
        comboEstados.getSelectionModel().selectFirst();
        comboMaquinas.getSelectionModel().selectFirst();
    }

    /**
     * Metodo publico que elimina un objeto incidencia
     *
     * @param incidencia
     */
    private void eliminar(IncidenciaBean incidencia) {
        man.elimiarIncidencia(incidencia);
        tablaIncidencias.getItems().remove(incidencia);
        tablaIncidencias.refresh();
    }

    /**
     * Metodo publico que carga la ventana de Aladir incidencias
     */
    private void aniadir() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ggim/ui/fxml/GI03.fxml"));
            Parent root = loader.load();
            GI03Controller controller = loader.getController();
            controller.setMan(man);
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException e) {
            LOGGER.info("Error en aniadir()");

        }

    }

    /**
     * Metodo publico que se encarga de cargar la ventana de modificar una
     * incidencia
     *
     * @param incidencia incidencia a modificar
     */
    private void modificar(IncidenciaBean incidencia) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/ggim/ui/fxml/GI02.fxml"));
            Parent root = (Parent) loader.load();
            MaquinasManager MaquiMan = new MaquinasManagerTestGenerator();
            GI02Controller controller = loader.getController();
            controller.setMan(man);
            controller.setMaquiMan(MaquiMan);
            controller.setIncidenciaSelected(incidencia);
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException e) {
            LOGGER.info("Error en modificar()");
        }
    }

    /**
     * Metodo publico para volver a la ventana l01
     */
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ggim/ui/fxml/MA00.fxml"));
            Parent root = (Parent) loader.load();
            MACcontroller controller = (MACcontroller) loader.getController();
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException e) {
            LOGGER.info("Error en volver()");
        }
    }

}
