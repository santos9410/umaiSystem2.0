/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Proveedor;


import Helpers.db;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class EliminarProveedorController implements Initializable {
    @FXML
    private TextField txtIdProv;
    @FXML
    private TextField txtNombreProv;
    @FXML
    private Button btnCancelar;
    @FXML
    private CustomTextField autoCompText;
    @FXML
    private Button btnEliminar;
     private ModelProveedores selectedProv;
    @FXML
    private VBox contenedor1;
    @FXML
    private VBox contenedor2;
    private AutoCompletionBinding<ModelProveedores> autoCompletionBinding;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
         
        
    }    
    private void cargarProveedor(){
         ResultSet rs;
        try 
        {
            List<ModelProveedores> proveedor = new ArrayList<ModelProveedores>();
           //int i = 0; 
           rs = db.getDbCon().query("Select * from proveedor where Contrato=1;");
           rs.last();
          
           rs.beforeFirst();
           while (rs.next()) 
           {
               proveedor.add(new ModelProveedores(rs.getInt(1),rs.getString(2),rs.getString(3),
                       rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getInt(9) ));
            }
           
              //AGregamos el bind para autocomepletar
        autoCompletionBinding = TextFields.bindAutoCompletion(autoCompText,proveedor); 
       
        autoCompletionBinding.setOnAutoCompleted(e->Cargar(selectedProv=e.getCompletion())); 
        rs.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(EliminarProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void txtIdProvClick(ActionEvent event) {
    }

    @FXML
    private void txtNombreProvClick(ActionEvent event) {
    }

    @FXML
    private void btnCancelarClick(ActionEvent event) {
    }

    @FXML
    private void eventEliminarClick(ActionEvent event) {
        
        if(!txtIdProv.getText().equals("") && !txtNombreProv.getText().equals("")){
        ModelProveedores mProv=new ModelProveedores(Integer.parseInt(txtIdProv.getText()), null, null, null, null, null, null, null,0);
        mProv.update2();
        Limpiar();
        
        autoCompletionBinding.dispose();
        
        cargarProveedor();
        Helpers.AlertBox.display("Éxito","Desactivado correctamente");
         contenedor1.setVisible(false);
        contenedor2.setVisible(false);
        }
    }

    private void Cargar(ModelProveedores completion) {
        txtIdProv.setText(String.valueOf(selectedProv.getIdProv()));
        txtNombreProv.setText(selectedProv.getNombreProv());
        
        contenedor1.setVisible(true);
        contenedor2.setVisible(true);
        btnEliminar.setDisable(false);
    }

    private void Limpiar() {

        txtIdProv.setText("");
        txtNombreProv.setText("");
        autoCompText.setText("");
        
        btnEliminar.setDisable(true);
        contenedor1.setVisible(false);
        contenedor2.setVisible(false);
    }
    
    
}
