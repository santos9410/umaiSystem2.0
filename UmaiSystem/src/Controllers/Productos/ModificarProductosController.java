
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author 
 */
public class ModificarProductosController implements Initializable {
   
   
    @FXML
    private TextArea txtDescProd;
    
    
    @FXML
    private ComboBox<String> cbUnidadProd;

   
     
   
   
    @FXML
    private TextField txtIdProd;
    @FXML
    private GridPane gridContenedorDatos;
    @FXML
    private Label lblMensaje;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnModificarProd;
    
    @FXML
    private CustomTextField autoCompProduc;
    @FXML
    private TextField txtNombProducto;
    private ModelProveedores selectedProv;
    private ModelProductos selectedProduc;
    @FXML
    private CustomTextField autoCompProv;
    @FXML
    private TextField precioTxt;
    @FXML
    private ComboBox<String> cbEstado;
      private AutoCompletionBinding<ModelProductos> autoCompletionBinding;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     cbUnidadProd.getItems().addAll("Kilogramos","Gramos","Caja","Lata","Litro","Mililitro","Pieza","Galon 20lts","Galon");
         cbEstado.getItems().addAll("Existencia","Agotado");
        
         txtNombProducto.textProperty().addListener(new ChangeListener<String>(){
        
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if(newValue.matches("^[A-Za-z]*(\\s?[A-Za-z]*)*") && newValue.length()<30){
                txtNombProducto.setText(newValue);
            }
            else
            {
                txtNombProducto.setText(oldValue);
            }
            }
        });
        precioTxt.textProperty().addListener(new ChangeListener<String>(){
        
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if(newValue.matches("^[0-9]*") && newValue.length()<11){
                //txtNombreProd.setText(newValue);
                precioTxt.setText(newValue);
            }
            else
            {
                //txtNombreProd.setText(oldValue);
                precioTxt.setText(oldValue);
            }
            }
        });
        
        txtDescProd.textProperty().addListener(new ChangeListener<String>(){
        
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if(newValue.matches(".*") && newValue.length()<150){
                txtDescProd.setText(newValue);
            }
            else
            {
                txtDescProd.setText(oldValue);
            }
            }
        });
        
         cargarProductos();
        
        
        
    }    
    public void cargarProductos(){
        ResultSet rs;
        try 
        {
            List<ModelProductos> productos= new ArrayList<>();
           //int i = 0; 
           rs = db.getDbCon().query("Select * from  Productos;");
           rs.last();
          
           rs.beforeFirst();
           while (rs.next()) 
           {
               productos.add(new ModelProductos(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
            }
           
              //AGregamos el bind para autocomepletar
      autoCompletionBinding = TextFields.bindAutoCompletion(autoCompProduc,productos); 
       
        autoCompletionBinding.setOnAutoCompleted(e->Cargar(selectedProduc=e.getCompletion())); 
            rs.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(ModificarProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eventModifcarProd(KeyEvent event) {
        
         
    }
    

   
    
    private void vaciarDatosTxt(ModelProductos prod) {
          
    }

    

    
 private void Limpiar() {
            
        txtIdProd.setText("");
       autoCompProduc.setText("");
        autoCompProv.setText("");
        txtIdProd.setText("");
        txtDescProd.setText("");
        cbUnidadProd.setValue(null);
        precioTxt.setText("");
        gridContenedorDatos.setVisible(false);
    }

    @FXML
    private void btnModificarProdClick(ActionEvent event) {
       if(txtDescProd.getText().isEmpty() == true || txtNombProducto.getText().isEmpty() == true || precioTxt.getText().isEmpty() == true )
        {
            Helpers.AlertBox.display("Umai System", "Todos los campos son requeridos para guardar un nuevo producto");
            
        }
        else
        {
            int idProv;
            int Estado;
            if(cbEstado.getValue().equals("Existencia")){
                Estado=1;
            }else{
                Estado=0;
            }
            ModelProductos p  =  new ModelProductos(Integer.parseInt(txtIdProd.getText()),selectedProduc.getIdProv() ,txtNombProducto.getText(),txtDescProd.getText(),(String)cbUnidadProd.getSelectionModel().getSelectedItem(),Integer.parseInt(precioTxt.getText()),Estado);
            p.update();
            Limpiar();
            Helpers.AlertBox.display("Umai System", "Guardado con exito");
            autoCompletionBinding.dispose();
            cargarProductos();
            
        }
        
    }

    private void Cargar(ModelProductos completion) {
   
        ResultSet rs;
       // List<String> nombproveedor = null;
        String nombproveedor = null;
        try {
                
                rs = db.getDbCon().query("Select nombreProv from proveedor where idProv="+selectedProduc.getIdProv()   +";");
                rs.last();
          
           rs.beforeFirst();
           while (rs.next()) 
           {
              nombproveedor=rs.getString(1);
            }
           rs.close();
        } catch (SQLException ex) {
             Logger.getLogger(ModificarProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try 
        {
            List<ModelProveedores> proveedor = new ArrayList<ModelProveedores>();
           //int i = 0; 
            //rs = db.getDbCon().query("Select * from proveedor where idProv="+selectedProduc.getIdProv()   +";");
            rs=db.getDbCon().query("select * from proveedor;");
            rs.last();
          
           rs.beforeFirst();
           while (rs.next()) 
           {
               proveedor.add(new ModelProveedores(rs.getInt(1),rs.getString(2),rs.getString(3),
                       rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getInt(9)));
            }
           
              //AGregamos el bind para autocomepletar
        AutoCompletionBinding<ModelProveedores> autoCompletionBinding = TextFields.bindAutoCompletion(autoCompProv,proveedor); 
       
        autoCompletionBinding.setOnAutoCompleted(e->CargarProv(selectedProv=e.getCompletion())); 
        
        rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModificarProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
       
           
            txtIdProd.setText(String.valueOf(selectedProduc.getIdProd()));
            txtDescProd.setText(selectedProduc.getDescripcionProd());
            txtNombProducto.setText(selectedProduc.getNombreProd());
            cbUnidadProd.setValue(selectedProduc.getUnidadMedidaProd());
            autoCompProv.setText(nombproveedor);
            precioTxt.setText(String.valueOf(selectedProduc.getPrecioCompra()));
            
             String estado;
            if(selectedProduc.getEstadoProducto()==1){
                estado="Existencia";
            }else
            {
                estado="Agotado";
            }
           
            cbEstado.setValue(estado);
            
            gridContenedorDatos.setVisible(true);
        
        
        
    
    
    
    }

    private void CargarProv(ModelProveedores completion) {
         autoCompProv.setText(selectedProv.getNombreProv());
         selectedProduc.setIdProv(selectedProv.getIdProv());
         
    
    }

}
