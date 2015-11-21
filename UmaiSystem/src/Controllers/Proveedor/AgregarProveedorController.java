/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Proveedor;

import Models.ModelProveedores;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class AgregarProveedorController implements Initializable {
    @FXML
    private TextField txtNombreProv;
    @FXML
    private TextField txtTelefonoProv;
    @FXML
    private TextField txtEmailProv;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAceptar;
    @FXML
    private Label lblError;
    @FXML
    private ComboBox<String> cboxCodigoProveedor;
    @FXML
    private ComboBox<String> cboxCiudadProv;
    @FXML
    private TextField txtCalleProv;
    @FXML
    private ComboBox<String> cboxColoniaProv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Cargamos todos los datos 
        ResultSet resul = null;
        try {
            resul = Helpers.db.getDbCon().query("Select CodigoPostal from CodigosPostales where Estado='Jalisco'");
            while(resul.next()){
                        cboxCodigoProveedor.getItems().add(resul.getString(1));
                    }
                    resul.close();
        } catch (SQLException ex) {
            Logger.getLogger(AgregarProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        //Agregamos los filtros a los textbox
        txtNombreProv.textProperty().addListener(new ChangeListener<String>(){
           public void changed(ObservableValue<? extends String> observable,String oldValue,String newValue){
               if(newValue.matches("^[A-Za-z]*(\\s?[A-Za-z]*)*")&& newValue.length()<30){
                   txtNombreProv.setText(newValue);
               }
                else{
                   txtNombreProv.setText(oldValue);
               }
           } 
        });
        txtTelefonoProv.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.matches("[0-9]*")&& newValue.length()<20){
                    txtTelefonoProv.setText(newValue);
                }
                else{
                    txtTelefonoProv.setText(oldValue);
                }
            }
        });
        txtEmailProv.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.matches("[A-Za-z0-9._-]*@?[A-Za-z0-9]*.?[A-Za-z]*")&& newValue.length()<50){
                    txtEmailProv.setText(newValue);
                }
                else{
                    txtEmailProv.setText(oldValue);
                }
            }        
        });
        txtCalleProv.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.matches("([A-Za-z0-9]*\\s?)*#?[A-Za-z0-9]*")&& newValue.length()<30){
                    txtCalleProv.setText(newValue);
                }
                else{
                    txtCalleProv.setText(oldValue);
                }
            }        
        });
    }    
    
    @FXML
    private void btnCancelarClick(ActionEvent event) {
        
    }

    @FXML
    private void btnAceptarClick(ActionEvent event) {
         String mensageE="Campo: ";
          if(txtNombreProv.getText().equals("")){
              mensageE+="Nombre";
          }
          
          if(txtCalleProv.getText().equals("")){
              mensageE+=", Calle";
          }
          if(cboxColoniaProv.getValue()==null){
              mensageE+=", Colonia";
          }
         if(cboxCiudadProv.getValue()==null){
              mensageE+=", Ciudad";
          }
         if(cboxCodigoProveedor.getValue()==null){
              mensageE+=", C.P.";
          }
         if(txtTelefonoProv.getText().equals("")){
              mensageE+=", Telefono";
          }
         if(txtEmailProv.getText().equals("")){
              mensageE+=", Email";
         }
        
         
        mensageE+=" vacio";
        lblError.setText(mensageE);
        lblError.setVisible(true);
            if(   !txtNombreProv.getText().equals("")
           
           && !txtCalleProv.getText().equals("")
           && cboxColoniaProv.getValue()!=null
           && cboxCiudadProv.getValue()!=null
           && cboxCodigoProveedor.getValue()!=null
           && !txtTelefonoProv.getText().equals("")
           && !txtEmailProv.getText().equals("") 
        
           
          )
        {
            ModelProveedores mProv=new ModelProveedores(0,txtNombreProv.getText(),cboxCodigoProveedor.getValue(),
              cboxCiudadProv.getValue(),cboxColoniaProv.getValue(),txtCalleProv.getText(),
                    txtTelefonoProv.getText(),txtEmailProv.getText(),1);
            
            mProv.save();
            lblError.setText("Agregado correcto");
            lblError.setVisible(true);
            Limpiar();
        }
    }
    
    @FXML
    private void eventCodigoProveedor(ActionEvent event) {
        if(cboxCodigoProveedor.getValue()!=null){
           
            try {
                
                ResultSet resul;
                resul=Helpers.db.getDbCon().query("Select Municipio from CodigosPostales where CodigoPostal='"+cboxCodigoProveedor.getValue()+"'");
                cboxCiudadProv.getItems().clear();
                 while(resul.next()){
                        cboxCiudadProv.getItems().add(resul.getString(1));
                        cboxCiudadProv.setValue(resul.getString(1));
                    }
                 resul.close();
                 AñadirColonias();
               
            } catch (SQLException ex) {
                Logger.getLogger(AgregarProveedorController.class.getName()).log(Level.SEVERE,null,ex);
           }
        }
    }
    
    @FXML
    private void eventCiudadProv(ActionEvent event){
        
    }

    private void AñadirColonias() 
    {
        if(cboxCiudadProv.getValue()!=null){
           
          ResultSet resul=null;  
          String [] Colon=null;
            try {
                resul= Helpers.db.getDbCon().query("Select Colonia from CodigosPostales where Municipio='"+cboxCiudadProv.getValue() 
                        +  "' and CodigoPostal='" + cboxCodigoProveedor.getValue()+"'");
           
                   cboxColoniaProv.getItems().clear();
                while(resul.next()){
                    String temp=resul.getString(1);
                    Colon=separarColonias(temp);
                   
                    //cbColonia.getItems().add(resul.getString(1));
                    cboxColoniaProv.setValue(Colon[0]);
                for (int i = 0; i <Colon.length; i++) {
                    cboxColoniaProv.getItems().add(Colon[i]);
                }
                }
                
                resul.close();
                
                
            } catch (SQLException ex) {
                Logger.getLogger(AgregarProveedorController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
        }
    
    
    }

    private String[] separarColonias(String temp) {
        
        java.util.StringTokenizer tokens = new java.util.StringTokenizer(temp,";",false);
        String separados[]=new String[tokens.countTokens()];
        int i=0;
        while (tokens.hasMoreTokens()) 
        {
            separados[i]=tokens.nextToken();
            i++;
        }
        return separados;
    }

    private void Limpiar() 
    {
        txtCalleProv.setText("");
        txtEmailProv.setText("");
        txtNombreProv.setText("");
        txtTelefonoProv.setText("");
        cboxCiudadProv.setValue(null);
        cboxCodigoProveedor.setValue(null);
        cboxColoniaProv.setValue(null);
        
    
    }
}
