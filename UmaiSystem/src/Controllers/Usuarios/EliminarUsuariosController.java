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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class EliminarUsuariosController implements Initializable {
    
   
    @FXML
    private TextField txtNombreUsu;
    @FXML
    private TextField txtIdUsu;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnDesactivar;
   
   
    @FXML
    private Label lblMensaje;
    @FXML
    private HBox ContenedorDatos;

     private ModelUsuarios selectedUsu;
    @FXML
    private CustomTextField autoComTxt;
    
    private AutoCompletionBinding<ModelUsuarios> autoCompletionBinding;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
            //cargamos el custom de autocompletar
        cargarbind();
   
    }
    private void  cargarbind(){
         ResultSet rs;
       
        
        try 
        {
             List<ModelUsuarios> usuarios=new ArrayList<>();
            
            
           rs = db.getDbCon().query("Select * from usuarios where estadoContrato=1;");
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
           
           rs.close();
            
         autoCompletionBinding=  TextFields.bindAutoCompletion(autoComTxt,usuarios);
         autoCompletionBinding.setOnAutoCompleted(e->Cargar(selectedUsu=e.getCompletion())); 
         
         
     
          
        } catch (SQLException ex) {
            Logger.getLogger(ModificarUsuarios2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   


    @FXML
    private void btnCancelarClick(ActionEvent event) {
    }

    @FXML
    private void btnDesactivarClick(ActionEvent event) {
             ModelUsuarios modUsu=new ModelUsuarios(Integer.parseInt(txtIdUsu.getText()), null, null, null, null, null, null, null, null,0,0,0,0);
             modUsu.update2();
             Limpiar();
             Helpers.AlertBox.display("Éxito", "Desactivado correctamente");
             autoCompletionBinding.dispose();
             cargarbind();
           
             
    }

   
    
    
     private void vaciarDatosTxt(ModelUsuarios usu) {
       
       
        txtIdUsu.setText(String.valueOf(usu.getIdUsu()));
        txtNombreUsu.setText(usu.getNombreUsu());
       
    }
     
     private void Limpiar() {
            
        txtIdUsu.setText("");
        autoComTxt.setText("");
       txtNombreUsu.setText("");
       ContenedorDatos.setVisible(false);
        
}

    private void Cargar(ModelUsuarios completion) {
        ContenedorDatos.setVisible(true);
            txtIdUsu.setText(String.valueOf(selectedUsu.getIdUsu()));
            txtNombreUsu.setText(selectedUsu.getNombreUsu());
    }

    

}