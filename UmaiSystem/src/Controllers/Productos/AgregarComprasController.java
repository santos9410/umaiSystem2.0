/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Productos;

import Helpers.db;
import Models.ModelComprasTV;
import Models.ModelProductos;
import Models.modelAgregarCompras;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class AgregarComprasController implements Initializable {
    @FXML
    private TextField txttIdCompra;
    @FXML
    private TextField txtCant;
    @FXML
    private TextField txtPrecioUnitario;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnAgregarCompra;
    @FXML
    private TableView<ModelComprasTV> tableCompras;
    @FXML
    private VBox ContFecha;
    @FXML
    private CustomTextField autoComProduc;
    DatePicker checkInDatePicker;
    @FXML
    private TableColumn<ModelComprasTV,Double> cantidadCol;
    @FXML
    private TableColumn<ModelComprasTV,String> nombreCol;
    @FXML
    private TableColumn<ModelComprasTV,Float> precioCol;
    @FXML
    private TableColumn<ModelComprasTV,Float> totalCol;
     private ModelProductos selectedProduc;
     private final ObservableList<ModelComprasTV> data =
        FXCollections.observableArrayList(
        );
    private double total, cant,unidad;
    @FXML
    private Button btnGuardar;
     List<ModelProductos> productos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
      txtCant.textProperty().addListener(new ChangeListener<String>() {

          @Override
          public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
              
                  if(newValue.matches("[0-9]*") && newValue.length()<10 ){
                txtCant.setText(newValue);
            }
            else
            {
                txtCant.setText(oldValue);
            }
              
              
          }
      });
     
      txtPrecioUnitario.textProperty().addListener(new ChangeListener<String>(){
        
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            
               if(newValue.matches("[0-9.]*") && txtPrecioUnitario.getText().length()<10){
                txtPrecioUnitario.setText(newValue);
            }
            else
            {
                txtPrecioUnitario.setText(oldValue);
            } 
            
                
            }
        });
      
      checkInDatePicker = new DatePicker();
            
         GridPane gridPane = new GridPane();
         gridPane.setHgap(10);
         gridPane.setVgap(10);
         
         Label checkInlabel = new Label("FECHA:");
         gridPane.add(checkInlabel, 0, 0);
         
         checkInDatePicker.setValue(LocalDate.now());
         
         GridPane.setHalignment(checkInlabel, HPos.LEFT);
         gridPane.add(checkInDatePicker, 0, 1);
         
         //vbox.getChildren().add(gridPane);
        ContFecha.getChildren().add(gridPane);
      
      cantidadCol.setCellValueFactory(new PropertyValueFactory<ModelComprasTV,Double>("cantidad"));
      
       nombreCol.setCellValueFactory(new PropertyValueFactory<ModelComprasTV,String>("nombre"));
        precioCol.setCellValueFactory(new PropertyValueFactory<ModelComprasTV,Float>("unidad"));
        totalCol.setCellValueFactory(new PropertyValueFactory<ModelComprasTV,Float>("costo"));
       
        tableCompras.setItems(data);
        
    ResultSet rs;
        try 
        {
          productos = new ArrayList<ModelProductos>();
           //int i = 0; 
           rs = db.getDbCon().query("Select * from  Productos;");
           rs.last();
          
           rs.beforeFirst();
           while (rs.next()) 
           {
               productos.add(new ModelProductos(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
           }
           rs.close();
              //AGregamos el bind para autocomepletar
        AutoCompletionBinding<ModelProductos> autoCompletionBinding = TextFields.bindAutoCompletion(autoComProduc,productos); 
       
        autoCompletionBinding.setOnAutoCompleted(e->selectedProduc=e.getCompletion()); 
       
        
        } catch (SQLException ex) {
            Logger.getLogger(AgregarComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs=db.getDbCon().query("SELECT MAX(idCompra) AS id FROM compras;");
             if(rs.next()){
                txttIdCompra.setText(String.valueOf(rs.getInt(1)+1));
            }else{
                txttIdCompra.setText("1");
            }
             rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AgregarComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
     
    
    
    }    

    @FXML
    private void eventPrecio(KeyEvent event) {
        if(!txtCant.getText().isEmpty() && !txtPrecioUnitario.getText().isEmpty()){
            
            cant=Float.parseFloat(txtCant.getText());
            unidad= Float.parseFloat(txtPrecioUnitario.getText());
            total=cant*unidad;
            txtTotal.setText(String.valueOf(total));
                    
            
            
        }
        
    }

    @FXML
    private void eventAgregarProducto(ActionEvent event) {
        if(!txtCant.getText().isEmpty() && !autoComProduc.getText().isEmpty() && !txtPrecioUnitario.getText().isEmpty()
                && !txtPrecioUnitario.getText().isEmpty())
        
        {
            data.add(new ModelComprasTV(cant,autoComProduc.getText(),unidad,total,selectedProduc.getIdProd()));
            tableCompras.setItems(data);
            Limpiar1();
        }
    
    
    }

    private void Limpiar1() {

        txtCant.setText("");
        autoComProduc.setText("");
        txtPrecioUnitario.setText("");
        txtTotal.setText("");
    }

    @FXML
    private void eventGuardarCompra(ActionEvent event) {
        
       
        if(!txttIdCompra.getText().isEmpty() && !data.isEmpty()){
            int idCompra=Integer.parseInt(txttIdCompra.getText());
            String fecha=checkInDatePicker.getValue().toString();
         //   fecha=fecha.replaceAll("-","\\\\");
            
            
            new modelAgregarCompras().saveCompras(idCompra,fecha);
            ResultSet rs;
            int idDetalle=0;
            try {
            rs=db.getDbCon().query("SELECT MAX(idDetalleCom) AS id FROM detalle_compras ;");
             if(rs.isFirst()){
                idDetalle=rs.getInt(1);
            }
             rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AgregarComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            for (int i = 0; i <data.size(); i++) 
            {
           modelAgregarCompras md=new modelAgregarCompras(  (idDetalle+i+1)  ,idCompra,  data.get(i).getCantidad(), data.get(i).getIndProd()
                   ,data.get(i).getUnidad(), data.get(i).getCosto());
              
                  md.saveDetalleCompras();
            }
                
               
            
            
            Limpiar2();
            Nuevo();
         
            
            
        }
        
      
    }

    private void Limpiar2() {
        data.clear();
    
    
    }

    private void Nuevo() {
        ResultSet rs;    
        try {
            rs=db.getDbCon().query("SELECT MAX(idCompra) AS id FROM compras;");
             if(rs.next()){
                txttIdCompra.setText(String.valueOf(rs.getInt(1)+1));
            }else{
                txttIdCompra.setText("1");
            }
             rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AgregarComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    
    
}
