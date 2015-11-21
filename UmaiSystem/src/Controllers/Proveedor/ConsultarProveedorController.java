/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Proveedor;


import Controllers.Usuarios.ConsultarUsuariosController;
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
public class ConsultarProveedorController implements Initializable {
    @FXML
    private GridPane contenedorCampos;
    @FXML
    private TextField txtEmailProv;
    @FXML
    private TextField txtTelefonoProv;
    @FXML
    private TextField txtCalleProv;
    @FXML
    private TextField txtCodProv;
    @FXML
    private TextField txtCiudadProv;
    @FXML
    private TextField txtNombreProv;
    @FXML
    private TextField txtIdProv;
    @FXML
    private CustomTextField autoComTxt;
     private ModelProveedores selectedProv;
    @FXML
    private TextField txtContrato;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        AutoCompletionBinding<ModelProveedores> autoCompletionBinding = TextFields.bindAutoCompletion(autoComTxt,proveedor); 
       
        autoCompletionBinding.setOnAutoCompleted(e->Cargar(selectedProv=e.getCompletion())); 

        
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void txtEmailProvClick(ActionEvent event) {
    }

    @FXML
    private void txtTelefonoProvClick(ActionEvent event) {
    }

    @FXML
    private void txtCiudadProvClick(ActionEvent event) {
    }

    @FXML
    private void txtNombreProvClick(ActionEvent event) {
    }

    @FXML
    private void txtIdProvClick(ActionEvent event) {
    }

    private void Cargar(ModelProveedores completion) {
    
        txtCalleProv.setText(selectedProv.getCalleProv());
        txtCiudadProv.setText(selectedProv.getCiudadProv());
        txtCodProv.setText(selectedProv.getCodigoProv());
        txtEmailProv.setText(selectedProv.getEmailProv());
        txtIdProv.setText(String.valueOf(selectedProv.getIdProv()));
        txtNombreProv.setText(selectedProv.getNombreProv());
        txtTelefonoProv.setText(selectedProv.getTelefonoProv());
        
     String contrato="";
        if(selectedProv.getContrato()==1){
            contrato="Activo";
        }else{
            contrato="Terminado";
        }
       
        txtContrato.setText(contrato);
        
        
        contenedorCampos.setVisible(true);
    }

    
}
