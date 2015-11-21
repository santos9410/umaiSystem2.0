/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Receta;


import Helpers.db;
import Models.ModelProductos;
import Models.ModelRecetaTV;
import Models.RecetaModel;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;


/**
 * FXML Controller class
 *
 * @author antoniocg
 */
public class AgregarRecetasController implements Initializable {
    @FXML
    private TextField txtNombreReceta;
   
    @FXML
    private TextField txtTamanoReceta;
    @FXML
    private TextField txtPorcionReceta;
    @FXML
    private TextField txtTiempoReceta;
    @FXML
    private Button btnAceptarReceta;
    @FXML
    private TextField txtCostoTotal;
    @FXML
    private ComboBox<String> cbTipo;
    @FXML
    private ComboBox<String> cbDificultad;
    
    @FXML
    private TextField txtCantidadIngrediente;
    
    @FXML
    private Button btnAgregarProducto;
    
        private final ObservableList<ModelRecetaTV> data =
        FXCollections.observableArrayList(
        );
    @FXML
    private TableView<ModelRecetaTV> TVProductos;
    @FXML
    private TableColumn<ModelRecetaTV, String> ingredienteCol;
    @FXML
    private TableColumn<ModelRecetaTV, String> PesoNCol;
    @FXML
    private TableColumn<ModelRecetaTV, String> UnidadCol;
    @FXML
    private TableColumn<ModelRecetaTV, String> CostoUni;
    
    @FXML
    private TextArea preparacionTxt;
    @FXML
    private CustomTextField autoComTxt;
    private ModelProductos selectedProd;

           
   
    /**
     * Inicializamos los componentes necesarios
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] options;

        ResultSet rs;
         
        //Cargamos todos los productos existentes
        //TODO:
        //Podemos con un timer hace la busqueda cada 3 seg que deje de escribir
        try {
            List<ModelProductos> productos = new ArrayList<ModelProductos>();
           int i = 0; 
           rs = db.getDbCon().query("Select * from Productos;");
           rs.last();
            options = new String[rs.getRow()];
           rs.beforeFirst();
           while (rs.next()) {
               productos.add(new ModelProductos(rs.getInt("idProd"),
                              rs.getInt("idProv"),rs.getString("nombreProd"),
                              rs.getString("descripcionProd"),rs.getString("unidadMedidadProd"),
                               rs.getInt("precioCompra"),rs.getInt(7)));   // se hizo una moficacion porque se incluyo el estado del producto
               
           
          
           }
           
              //AGregamos el bind para autocomepletar
        AutoCompletionBinding<ModelProductos> autoCompletionBinding = TextFields.bindAutoCompletion(autoComTxt, productos); 
        
           
          autoCompletionBinding.setOnAutoCompleted(e-> selectedProd = e.getCompletion());
          
        } catch (SQLException ex) {
            Logger.getLogger(AgregarRecetasController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        //Agregamos regex de solo numeros
        txtPorcionReceta.textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(oldValue + " " + newValue);
                if(newValue.matches("\\d*"))
                {
                   txtPorcionReceta.setText(newValue);
                }
                else
                {
                     txtPorcionReceta.setText(oldValue);
                }
            }
        
 });
        txtTamanoReceta.textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(oldValue + " " + newValue);
                if(newValue.matches("\\d*"))
                {
                   txtTamanoReceta.setText(newValue);
                }
                else
                {
                     txtTamanoReceta.setText(oldValue);
                }
            }
        
 });
        txtTiempoReceta.textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                if(newValue.matches("\\d*"))
                {
                   txtTiempoReceta.setText(newValue);
                }
                else
                {
                   txtTiempoReceta.setText(oldValue);
                }
            }
        
 });
        
       
        //Ingresamos las opciones del tipo de Receta.
        cbTipo.getItems().addAll("Platillo","Complemento");
        
        cbDificultad.getItems().addAll("Dificil","Medio","Facíl");
        
        //Inicializamos las columnas del TableView
        ingredienteCol.setCellValueFactory(new PropertyValueFactory<ModelRecetaTV,String>("Ingrediente"));
      
        PesoNCol.setCellValueFactory(new PropertyValueFactory<ModelRecetaTV,String>("Cantidad"));
        UnidadCol.setCellValueFactory(new PropertyValueFactory<ModelRecetaTV,String>("Unidad"));
        CostoUni.setCellValueFactory(new PropertyValueFactory<ModelRecetaTV,String>("Costo"));
       
        TVProductos.setItems(data);
    }    

    @FXML
    private void esNumero(InputMethodEvent event) {
    }

    @FXML
    private void tamPorcionRegex(KeyEvent event) {
    }


    @FXML
    private void btnAceptarRecetaClick(ActionEvent event) {
        //GUARDAMOS LA RECETA EN LA BASE DE DATOS
        //RecetaModel(int idRec, String nombreRec, String tipoRec, int tamañoPorcion, int numeroPorcion, int tiempoPreparacion, String dificultad, String procedimiento, ObservableList<ModelRecetaTV> data) {
       
        RecetaModel receta = new RecetaModel(0,txtNombreReceta.getText(),
                                            (String)cbTipo.getSelectionModel().getSelectedItem(),
                                             Integer.parseInt(txtTamanoReceta.getText()),Integer.parseInt(txtTamanoReceta.getText()),
                                               Integer.parseInt(txtTiempoReceta.getText()),(String)cbDificultad.getSelectionModel().getSelectedItem(),
                                            preparacionTxt.getText(),data);
        
    }

    @FXML
    private void soloDecimal(InputMethodEvent event) {
    }

    @FXML
    private void btnAgregaProductoClick(ActionEvent event) {
        double cantidad = Double.parseDouble(txtCantidadIngrediente.getText());
        //TODO Obtener producto
        data.add(new ModelRecetaTV(selectedProd.getNombreProd(),cantidad,selectedProd.getUnidadMedidaProd(),(double)selectedProd.getPrecioCompra()));
        
            
    }
    
}


