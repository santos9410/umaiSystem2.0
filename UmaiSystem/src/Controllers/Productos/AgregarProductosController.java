
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
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author 
 */
public class AgregarProductosController implements Initializable {
    @FXML
    private TextField txtNombreProd;
    @FXML
    private TextArea txtDescProd;
    @FXML
    private ComboBox<String> cboxUnidadProd;
    @FXML
    private Label lblError;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAceptar;
    @FXML
    private TextField precioTxt;
    @FXML
    private CustomTextField autoCompProv;
     private ModelProveedores selectedProv;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cboxUnidadProd.getItems().addAll("Kilogramos","Gramos","Caja","Lata","Litro","Mililitro","Pieza","Galon 20lts","Galon");
        
        txtNombreProd.textProperty().addListener(new ChangeListener<String>(){
        
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if(newValue.matches("^[A-Za-z]*(\\s?[A-Za-z]*)*") && newValue.length()<30){
                txtNombreProd.setText(newValue);
            }
            else
            {
                txtNombreProd.setText(oldValue);
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
        
        ResultSet rs;
        try 
        {
            List<ModelProveedores> proveedor = new ArrayList<ModelProveedores>();
           //int i = 0; 
           rs = db.getDbCon().query("Select * from proveedor;");
           rs.last();
          
           rs.beforeFirst();
           while (rs.next()) 
           {
               proveedor.add(new ModelProveedores(rs.getInt(1),rs.getString(2),rs.getString(3),
                       rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getInt(9)));
            }
           
              //AGregamos el bind para autocomepletar
        AutoCompletionBinding<ModelProveedores> autoCompletionBinding = TextFields.bindAutoCompletion(autoCompProv,proveedor); 
       
        autoCompletionBinding.setOnAutoCompleted(e->selectedProv=e.getCompletion()); 

        
        } catch (SQLException ex) {
            Logger.getLogger(AgregarProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }    

    @FXML
    private void btnCancelarClick(ActionEvent event) {
    }

    /**
     *Agregamos el producto la base de datos  
     * @param event 
     */
    @FXML
    private void btnAceptarClick(ActionEvent event) {
        if(txtDescProd.getText().isEmpty() == true || txtNombreProd.getText().isEmpty() == true || precioTxt.getText().isEmpty() == true )
        {
            Helpers.AlertBox.display("Umai System", "Todos los campos son requeridos para guardar un nuevo producto");
            
        }
        else
        {
            int idProv;
            
            ModelProductos p  =  new ModelProductos(0,selectedProv.getIdProv(), txtNombreProd.getText(),txtDescProd.getText(),(String)cboxUnidadProd.getSelectionModel().getSelectedItem(),Integer.parseInt(precioTxt.getText()),1);
            p.save();
            Limpiar();
            Helpers.AlertBox.display("Umai System", "Guardado con exito");
            
        }
    }

    @FXML
    private void eventValidarNombre(KeyEvent event) {
       
    }

    @FXML
    private void eventValidarDescr(KeyEvent event) {
    
    }

    private void Limpiar() {
            autoCompProv.setText("");
            txtDescProd.setText("");
            txtNombreProd.setText("");
            cboxUnidadProd.setValue(null);
            precioTxt.setText("");
    
    }

    
    
}
