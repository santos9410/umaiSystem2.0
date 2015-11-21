/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Productos;

import Helpers.db;
import Models.ModelProductos;
import Models.ModelProveedores;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class ConsultarProductosController implements Initializable {
    
    @FXML
    private TextField txtIdProd;
    @FXML
    private TextField txtNombreProd;
    @FXML
    private TextArea txtDescProd;
    @FXML
    private TextField txtUnidadMedida;
    
    @FXML
    private TextField txtnomProv;
    @FXML
    private CustomTextField autoCompProd;
    private ModelProveedores selectedProv;
    private ModelProductos selectedProduc;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtEstado;
    @FXML
    private GridPane gridDatos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ResultSet rs;
        try 
        {
            List<ModelProductos> productos= new ArrayList<ModelProductos>();
           //int i = 0; 
           rs = db.getDbCon().query("Select * from  Productos;");
           rs.last();
          
           rs.beforeFirst();
           while (rs.next()) 
           {
               productos.add(new ModelProductos(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
            }
           
              //AGregamos el bind para autocomepletar
        AutoCompletionBinding<ModelProductos> autoCompletionBinding = TextFields.bindAutoCompletion(autoCompProd,productos); 
       
        autoCompletionBinding.setOnAutoCompleted(e->Cargar(selectedProduc=e.getCompletion())); 

        
        } catch (SQLException ex) {
            Logger.getLogger(AgregarProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    
    
    }
    private void txtBuscarProdClick(ActionEvent event) {
         
    }

    private void vaciarDatosTxt(ModelProductos prod) {
       
                   txtDescProd.setText(prod.getDescripcionProd());
                   txtIdProd.setText(String.valueOf(prod.getIdProd()));
                   txtNombreProd.setText(prod.getNombreProd());
                   
                   
    }               

    private void Limpiar() {
            
        txtIdProd.setText("");
        txtNombreProd.setText("");
        
        txtIdProd.setText("");
        txtDescProd.setText("");
        txtUnidadMedida.setText("");
        //.setValue(null);
    }

    private void Cargar(ModelProductos completion) {

                ResultSet rs;
        try 
        {
            List<String> proveedor = new ArrayList<>();
           //int i = 0; 
           rs = db.getDbCon().query("Select nombreProv from proveedor where idProv="+selectedProduc.getIdProv()   +";");
           rs.last();
          
           rs.beforeFirst();
           while (rs.next()) 
           {
              proveedor.add(rs.getString(1));
            }
           
            txtIdProd.setText(String.valueOf(selectedProduc.getIdProd()));
            txtDescProd.setText(selectedProduc.getDescripcionProd());
            txtNombreProd.setText(selectedProduc.getNombreProd());
            txtUnidadMedida.setText(selectedProduc.getUnidadMedidaProd());
            txtnomProv.setText(proveedor.get(0));
            String estado;
            if(selectedProduc.getEstadoProducto()==1){
                estado="Existencia";
            }else
            {
                estado="Agotado";
            }
            txtEstado.setText(estado);
            
           txtPrecio.setText(String.valueOf(selectedProduc.getPrecioCompra()));
           gridDatos.setVisible(true);
        
        } catch (SQLException ex) {
            Logger.getLogger(AgregarProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }

}

    

