/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Productos;

import Helpers.db;
import Models.ModelProductos;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author
 */
public class EliminarProductosController implements Initializable {
    @FXML
    private TextField txtNombreProd;
    @FXML
    private TextField txtIdProd;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnEliminar;
   
    
    @FXML
    private GridPane gridContenedor;
    @FXML
    private Label lblMensaje;
    @FXML
    private CustomTextField autoCompProdu;
     private ModelProductos selectedProduc;
      AutoCompletionBinding<ModelProductos> autoCompletionBinding;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         cargarProducto();
        
        
    }    
    private void cargarProducto(){
         ResultSet rs;
        try 
        {
            List<ModelProductos> productos= new ArrayList<ModelProductos>();
           //int i = 0; 
           rs = db.getDbCon().query("Select * from  Productos where ContratoProducto=1;");
           rs.last();
          
           rs.beforeFirst();
           while (rs.next()) 
           {
               productos.add(new ModelProductos(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
            }
           
              //AGregamos el bind para autocomepletar
        autoCompletionBinding= TextFields.bindAutoCompletion(autoCompProdu,productos); 
       
        autoCompletionBinding.setOnAutoCompleted(e->Cargar(selectedProduc=e.getCompletion())); 
            rs.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(EliminarProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @FXML
    private void btnCancelarClick(ActionEvent event) {
    }

    @FXML
    private void btnEliminarClick(ActionEvent event) {
       
         if(!txtIdProd.getText().equals("") && !txtNombreProd.getText().equals("")){
        ModelProductos mProd=new ModelProductos(Integer.parseInt(txtIdProd.getText()),0,txtNombreProd.getText(), null, null,0,0);
        mProd.update2();
        Limpiar();
        Helpers.AlertBox.display("Éxito","desactivado correctamente");
        autoCompletionBinding.dispose();
        cargarProducto();
    
    }
    }

        
    
    
    
    
    
    
    
   

    private void Limpiar() {
            
        txtIdProd.setText("");
        txtNombreProd.setText("");
        autoCompProdu.setText("");
        gridContenedor.setVisible(false);
       
    }

    private boolean Esdigito(String text)
        {
        return text.matches("[0-9]+");
                
        }

    private void Cargar(ModelProductos completion) {

        txtIdProd.setText(String.valueOf(selectedProduc.getIdProd()));
        txtNombreProd.setText(selectedProduc.getNombreProd());
        
        gridContenedor.setVisible(true);
        
    }
}

