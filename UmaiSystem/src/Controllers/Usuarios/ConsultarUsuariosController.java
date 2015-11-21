/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Usuarios;

import Helpers.db;
import Models.ModelUsuarios;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author 
 */
public class ConsultarUsuariosController implements Initializable {

   
    @FXML
    private TextField txtNomUsu;
    @FXML
    private TextField txtCalleUsu;
    @FXML
    private TextField txtColoniaUsu;
    @FXML
    private TextField txtCiudadUsu;
    @FXML
    private TextField txtCpUsu;
    @FXML
    private TextField txtTelefonoUsu;
    @FXML
    private TextField txtSueldoUsu;
    @FXML
    private TextField txtPuntosUsu;
    @FXML
    private TextField txtPermisoUsu;
    @FXML
    private TextField txtContratoUsu;
    @FXML
    private TextField txtIdUsu;
     
    private ModelUsuarios selectedUsu;

    @FXML
    private GridPane gridContenedorDatos;
    @FXML
    private CustomTextField autoComTxt;
    
    AutoCompletionBinding<ModelUsuarios> autoCompletionBinding;
    
     
      
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ResultSet rs;
        try {
            List<ModelUsuarios> usuarios = new ArrayList<ModelUsuarios>();
           int i = 0; 
           rs = db.getDbCon().query("Select * from usuarios;");
           rs.last();
          
           rs.beforeFirst();
           while (rs.next()) {
               usuarios.add(new ModelUsuarios(rs.getInt(1),
                        rs.getString("nombreUsu"),rs.getString(3),rs.getString(4),
                       rs.getString(5),rs.getString(6),rs.getString(7),
                       rs.getString(8),rs.getString(9),
                       rs.getInt(10),rs.getInt(11),
                       rs.getInt(12),rs.getInt(13)
               
               ));

           }
           
              //AGregamos el bind para autocomepletar
        //AutoCompletionBinding<ModelUsuarios> autoCompletionBinding = TextFields.bindAutoCompletion(autoComTxt,usuarios); 
         autoCompletionBinding=TextFields.bindAutoCompletion(autoComTxt,usuarios);
           
         //autoCompletionBinding.setOnAutoCompleted(e-> selectedUsu= e.getCompletion());   
           
            
         autoCompletionBinding.setOnAutoCompleted(e->Cargar(selectedUsu=e.getCompletion())); 
              
          //autoCompletionBinding.setOnAutoCompleted(e-> selectedUsu = e.getCompletion());
     //    autoCompletionBinding.setOnAutoCompleted((EventHandler<AutoCompletionBinding.AutoCompletionEvent<ModelUsuarios>>));
          
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    

    private void Cargar(ModelUsuarios completion) 
    {
      gridContenedorDatos.setVisible(true);
        txtNomUsu.setText(selectedUsu.getNombreUsu());
       
        txtCalleUsu.setText(selectedUsu.getCalleUsu());
        txtCiudadUsu.setText(selectedUsu.getCiudUsu());
        txtColoniaUsu.setText(selectedUsu.getColoUsu());
        int contrato=selectedUsu.getEstadoContrado();
        if(contrato==0){
            txtContratoUsu.setText("Terminado");
        }
        else{
            txtContratoUsu.setText("Activo");
        }
        txtCpUsu.setText(selectedUsu.getCodigoPostal());
        txtIdUsu.setText(String.valueOf(selectedUsu.getIdUsu()));
        int permiso=selectedUsu.getTipoPermiso();
        if(permiso==3){
            txtPermisoUsu.setText("alto");
        }
        else if(permiso==2)
        {
            txtPermisoUsu.setText("medio");
        }else 
        {
            txtPermisoUsu.setText("bajo");
        }
              
        txtPuntosUsu.setText(String.valueOf(selectedUsu.getPuntosUsu()));
        txtSueldoUsu.setText(String.valueOf(selectedUsu.getSueldoUsu()));
        txtTelefonoUsu.setText(selectedUsu.getTelefonoUsu());
    }

}
