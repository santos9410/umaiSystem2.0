/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Usuarios;

import java.sql.ResultSet;
import Models.ModelUsuarios;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author erika
 */
public class AgregarUsuariosController implements Initializable {
    @FXML
    private TextField txtNomUsu;
    
    @FXML
    private TextField txtTelefonoUsu;
    @FXML
    private TextField txtSueldoUsu;
    @FXML
    private TextField txtPuntosUsu;
    
    @FXML
    private Label lblError;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAceptar;
    @FXML
    private PasswordField txtContra;
    @FXML
    private ComboBox<String> cbCodigoPostal;
    @FXML
    private ComboBox<String> cbCiudad;
    @FXML
    private ComboBox<String> cbColonia;
    @FXML
    private TextField txtCalle;
    @FXML
    private ComboBox<String> cbPermiso;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

         //Agregamos los permisos al combobox
        cbPermiso.getItems().addAll("alto","medio","bajo");
       
         ResultSet rs = null;
        try 
        {
            rs=Helpers.db.getDbCon().query("select CodigoPostal from CodigosPostales "
                    + "where Estado='Jalisco'; ");
             while(rs.next())
                {
                   
                 cbCodigoPostal.getItems().add(rs.getString(1));
                 
                }
    
        } catch (SQLException ex) 
        {
            Logger.getLogger(AgregarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
        
           //Agregamos el filtro para que solo sean letras
        txtNomUsu.textProperty().addListener(new ChangeListener<String>()
        {
        
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if(newValue.matches("^[A-Za-z]*(\\s?[A-Za-z]*)*") && newValue.length()<30)
                {
                    txtNomUsu.setText(newValue);
                }
                else
                {
                    txtNomUsu.setText(oldValue);
                }
            }
        });
        txtContra.textProperty().addListener(new ChangeListener<String>() 
        {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                if(newValue.matches("([A-Za-z]||[0-9])*") && newValue.length()<30)
                {
                    txtContra.setText(newValue);
                }
                else
                {   
                    txtContra.setText(oldValue);
                }
            }
        });      
        txtCalle.textProperty().addListener(new ChangeListener<String>()
        {
             @Override
             public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
             {
                if(newValue.matches("([A-Za-z0-9]*\\s?)*#?[A-Za-z0-9]*") && newValue.length()<50)
                {
                    txtCalle.setText(newValue);
                }else
                {
                    txtCalle.setText(oldValue);
                }
             }
            
        });
        txtTelefonoUsu.textProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.matches("[0-9]*")&& newValue.length()<20){
                    txtTelefonoUsu.setText(newValue);
                }else{
                    txtTelefonoUsu.setText(oldValue);
                }
             }
        });
        txtSueldoUsu.textProperty().addListener(new ChangeListener<String>()
        {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                if(newValue.matches("[0-9]*") && newValue.length()<11)
                {
                    txtSueldoUsu.setText(newValue);
                }else
                {
                    txtSueldoUsu.setText(oldValue);
                }
             }
        });
        txtPuntosUsu.textProperty().addListener(new ChangeListener<String>()
        {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                if(newValue.matches("[0-9]*") && newValue.length()<11)
                {
                    txtPuntosUsu.setText(newValue);
                }else
                {
                    txtPuntosUsu.setText(oldValue);
                }
             }
        });
     
    }   
    @FXML
    private void btnCancelarClick(ActionEvent event) {
    }

    @FXML
    private void btnAceptarClick(ActionEvent event) 
    {
        boolean ban=false;
     
        String mensageE="Campo: ";
          if(txtNomUsu.getText().equals("")){
              mensageE+="Nombre";
              ban=true;
          }
          if(txtContra.getText().equals("")){
              mensageE+=", Contraseña";
               ban=true;
          }
          if(txtCalle.getText().equals("")){
              mensageE+=", Calle";
               ban=true;
          }
          if(cbColonia.getValue()==null){
              mensageE+=", Colonia";
               ban=true;
          }
         if(cbCiudad.getValue()==null){
              mensageE+=", Ciudad";
               ban=true;
          }
         if(cbCodigoPostal.getValue()==null){
              mensageE+=", C.P.";
               ban=true;
          }
         if(txtTelefonoUsu.getText().equals("")){
              mensageE+=", Telefono";
               ban=true;
          }
         if(txtSueldoUsu.getText().equals("")){
              mensageE+=", Sueldo";
               ban=true;
          }
         if(txtPuntosUsu.getText().equals("")){
              mensageE+=", Puntuación";
               ban=true;
          }
         if(cbPermiso.getValue()==null){
              mensageE+=", Permiso";
               ban=true;
          }
         
          mensageE+=" vacio";
          //lblError.setText(mensageE);
          //lblError.setVisible(true);
          if(ban){
          Helpers.AlertBox.display("Error", mensageE);
          ban=false;
          }
           if(   !txtNomUsu.getText().equals("")
           && !txtContra.getText().equals("")
           && !txtCalle.getText().equals("")
           && cbColonia.getValue()!=null
           && cbCiudad.getValue()!=null
           && cbCodigoPostal.getValue()!=null
           && !txtTelefonoUsu.getText().equals("")
           && !txtSueldoUsu.getText().equals("")
           && !txtPuntosUsu.getText().equals("") 
           && cbPermiso.getValue()!=null
           
          )
        {
            int Permiso;
            if(cbPermiso.getValue().equals("alto")){
                Permiso=3;
            }else if(cbPermiso.getValue().equals("medio")){
                Permiso=2;
            }else {
                Permiso=1;
            }
            String pass=Helpers.Crypto.MD5(txtContra.getText());
                ModelUsuarios modUsu=new ModelUsuarios(0,txtNomUsu.getText(), 
                        pass,txtCalle.getText(),cbColonia.getValue(), 
                        cbCiudad.getValue(),cbCodigoPostal.getValue(),txtTelefonoUsu.getText(),
                        null, (int) Double.parseDouble(txtSueldoUsu.getText()), (int) Double.parseDouble(txtPuntosUsu.getText()),Permiso,
                        1);
                modUsu.save();
                
            //lblError.setText("Agregado Correcto");
            
          limpiar();
          Helpers.AlertBox.display("Éxito","Agregado Correcto");
            
            
            
    }
    }
    private void limpiar(){
        
            txtNomUsu.setText("");
            txtContra.setText("");
            txtCalle.setText("");
            cbColonia.setValue("");
            cbCiudad.setValue("");
            cbCodigoPostal.setValue("");
            txtTelefonoUsu.setText("");
            txtSueldoUsu.setText("");
            txtPuntosUsu.setText("");
            cbPermiso.setValue("");
    }

    @FXML
    private void txtNomUsuarioClick(ActionEvent event) {
    }


    @FXML
    private void txtTelefonoUsuClick(ActionEvent event) {
    }

    @FXML
    private void txtSueldoUsuClick(ActionEvent event) {
    }

    @FXML
    private void txtPuntosUsuClick(ActionEvent event) {
    
    
    }

    @FXML
    private void eventCodigoPostal(ActionEvent event) {
        
        if(cbCodigoPostal.getValue()!=null){
       
          ResultSet resul=null;  
            try {
                resul=Helpers.db.getDbCon().query("Select Municipio from CodigosPostales where CodigoPostal='"+cbCodigoPostal.getValue() +"'");
            } catch (SQLException ex) {
                Logger.getLogger(AgregarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            cbCiudad.getItems().clear();
            try {
                  
                while(resul.next()){
                      cbCiudad.getItems().add(resul.getString(1));
                        cbCiudad.setValue(resul.getString(1));
                }
                resul.close();
               AñadirColonias();
            } catch (SQLException ex) {
                Logger.getLogger(AgregarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
        }
    }
    
    
    public void AñadirColonias(){
        if(cbCiudad.getValue()!=null){
           
          ResultSet resul=null;  
          String [] Colon=null;
            try {
                resul= Helpers.db.getDbCon().query("Select Colonia from CodigosPostales where Municipio='"+cbCiudad.getValue() 
                        +  "' and CodigoPostal='" + cbCodigoPostal.getValue()+"'");
           
                   cbColonia.getItems().clear();
                while(resul.next()){
                    String temp=resul.getString(1);
                    Colon=separarColonias(temp);
                   
                    //cbColonia.getItems().add(resul.getString(1));
                    cbColonia.setValue(Colon[0]);
                for (int i = 0; i <Colon.length; i++) {
                    cbColonia.getItems().add(Colon[i]);
                }
                }
                
                resul.close();
                
                
            } catch (SQLException ex) {
                Logger.getLogger(AgregarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
        }
    }

    @FXML
    private void eventCiudad(ActionEvent event) {
       
    }

    @FXML
    private void eventColonia(ActionEvent event) {
        
        
    }

    @FXML
    private void eventPermiso(ActionEvent event) {
    }

     private static String[] separarColonias(String texto)
    {
        java.util.StringTokenizer tokens = new java.util.StringTokenizer(texto,";",false);
        String separados[]=new String[tokens.countTokens()];
        int i=0;
        while (tokens.hasMoreTokens()) 
        {
            separados[i]=tokens.nextToken();
            i++;
        }
        return separados;
    }
}
