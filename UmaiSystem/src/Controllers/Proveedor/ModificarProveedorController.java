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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class ModificarProveedorController implements Initializable {
   
    @FXML
    private TextField txtIdProv;
    @FXML
    private TextField txtNombreProv;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtEmailProv;
    @FXML
    private Label lblError;
    @FXML
    private TextField txtTelefonoProv;
  
    @FXML
    private ComboBox<String> cbCodigoPostal;
    @FXML
    private ComboBox<String> cbCiudadProveedor;
    @FXML
    private ComboBox<String> cbColonia;
    @FXML
    private TextField txtCalle;
    @FXML
    private ComboBox<String> cbEstado;
 public static final ObservableList names = 
         FXCollections.observableArrayList();
     private static final ObservableList data = 
         FXCollections.observableArrayList();
     
    public static  ObservableList<?> list;
    @FXML
    private CustomTextField autoComTxt;
    private ModelProveedores selectedProv;
    @FXML
    private GridPane ContenedorCampos;
    @FXML
    private Button btnGuardar;
     private AutoCompletionBinding<ModelProveedores> autoCompletionBinding;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        cbEstado.getItems().addAll("Activo","Terminado");
        
        cargarProveedores();
        
        
       
          ResultSet resul=null;  
 
            try {
                resul = Helpers.db.getDbCon().query("Select CodigoPostal from CodigosPostales where Estado='Jalisco'");
          
                while(resul.next()){
                        cbCodigoPostal.getItems().add(resul.getString(1));
                        
                }   
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ModificarProveedorController.class.getName()).log(Level.SEVERE, null, ex);
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
        txtCalle.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.matches("([A-Za-z0-9]*\\s?)*#?[A-Za-z0-9]*")&& newValue.length()<30){
                    txtCalle.setText(newValue);
                }
                else{
                    txtCalle.setText(oldValue);
                }
            }        
        });
            
    }    

    
    private void cargarProveedores(){
        
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
                       rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getInt(9) ));
            }
           
              //AGregamos el bind para autocomepletar
            autoCompletionBinding= TextFields.bindAutoCompletion(autoComTxt,proveedor); 
       
            autoCompletionBinding.setOnAutoCompleted(e->Cargar(selectedProv=e.getCompletion())); 
                rs.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
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


   private void AñadirColonias(){
         if(cbCiudadProveedor.getValue()!=null){
           
          ResultSet resul=null;  
          String [] Colon=null;
            try {
                resul= Helpers.db.getDbCon().query("Select Colonia from CodigosPostales where Municipio='"+cbCiudadProveedor.getValue() 
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
                Logger.getLogger(AgregarProveedorController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
        }
    
    }
   public static String[] separarColonias(String texto)
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
   
   
   
   
private void Limpiar() {
        autoComTxt.setText("");
    
        txtIdProv.setText("");
        txtNombreProv.setText("");
        txtCalle.setText("");
        cbCiudadProveedor.setValue(null);
        cbColonia.setValue(null);
        
        cbEstado.setValue(null);
        cbCodigoPostal.setValue(null);
        txtTelefonoProv.setText("");
        txtEmailProv.setText("");
        ContenedorCampos.setVisible(false);
        
}

 private void vaciarDatosTxt(ModelProveedores usu) {
       

    }

    private void Cargar(ModelProveedores completion) {
          txtCalle.setText(selectedProv.getCalleProv());
        cbCiudadProveedor.setValue(selectedProv.getCiudadProv());
        cbCodigoPostal.setValue(selectedProv.getCodigoProv());
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
        cbEstado.setValue(contrato);
        
      ContenedorCampos.setVisible(true);
    
    }

    @FXML
    private void eventCodigoProveedor(ActionEvent event) {
         if(cbCodigoPostal.getValue()!=null){
           
            try {
                
                ResultSet resul;
                resul=Helpers.db.getDbCon().query("Select Municipio from CodigosPostales where CodigoPostal='"+cbCodigoPostal.getValue()+"'");
                cbCiudadProveedor.getItems().clear();
                 while(resul.next()){
                        cbCiudadProveedor.getItems().add(resul.getString(1));
                        cbCiudadProveedor.setValue(resul.getString(1));
                    }
                 resul.close();
                 AñadirColonias();
               
            } catch (SQLException ex) {
                Logger.getLogger(AgregarProveedorController.class.getName()).log(Level.SEVERE,null,ex);
           }
        }
    }

    @FXML
    private void eventGuardarClick(ActionEvent event) {
        
        boolean ban=false;
        String mensageE="Campo: ";
          if(txtNombreProv.getText().equals("")){
              mensageE+="Nombre";
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
         if(cbCiudadProveedor.getValue()==null){
              mensageE+=", Ciudad";
              ban=true;
          }
         if(cbCodigoPostal.getValue()==null){
              mensageE+=", C.P.";
              ban=true;
          }
         if(txtTelefonoProv.getText().equals("")){
              mensageE+=", Telefono";
              ban=true;
          }
         if(txtEmailProv.getText().equals("")){
              mensageE+=", Email";
              ban=true;
         }
         if(cbEstado.getValue()==null)
         {
              mensageE+=", Contrato";
              ban=true;
         }
         mensageE+=" vacio";
         if(ban){
             Helpers.AlertBox.display("Error", mensageE);
             ban=false;
         }
                 
       
       // lblError.setText(mensageE);
        //lblError.setVisible(true);
            if(   !txtNombreProv.getText().equals("")
           
           && !txtCalle.getText().equals("")
           && cbColonia.getValue()!=null
           && cbCiudadProveedor.getValue()!=null
           && cbCodigoPostal.getValue()!=null
           && !txtTelefonoProv.getText().equals("")
           && !txtEmailProv.getText().equals("") 
           && cbEstado.getValue()!=null
           
          )
        {   
            int contrato=0;
            if(cbEstado.getValue().equals("Activo")){
                contrato=1;
            }else{
                contrato=0;
            }
            ModelProveedores mProv=new ModelProveedores(Integer.parseInt(txtIdProv.getText()),txtNombreProv.getText(),cbCodigoPostal.getValue(),
              cbCiudadProveedor.getValue(),cbColonia.getValue(),txtCalle.getText(),
                    txtTelefonoProv.getText(),txtEmailProv.getText(),contrato);
            
            mProv.update();
            //lblError.setText("Modificado correcto");
            //lblError.setVisible(true);
            Helpers.AlertBox.display("Éxito","Modificación correcta");
            Limpiar();
            ContenedorCampos.setVisible(false);
            autoCompletionBinding.dispose();
            cargarProveedores();
        }
        
    }
}
