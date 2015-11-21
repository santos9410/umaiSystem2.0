/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Usuarios;



import Helpers.db;
import Models.ModelUsuarios;
import java.sql.ResultSet;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
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
public class ModificarUsuarios2 implements Initializable {
  
   
    @FXML
    private GridPane gridContenedorDatos;
    @FXML
    private TextField txtNomUsu;
    @FXML
    private PasswordField txtContraseñaUsu;
    @FXML
    private TextField txtCalleUsu;
   
    @FXML
    private TextField txtTelefonoUsu;
    @FXML
    private TextField txtSueldoUsu;
    @FXML
    private TextField txtPuntosUsu;
    
    @FXML
    private TextField txtIdUsu;
    @FXML
    private Button btnCancelarModificar;
    @FXML
    private Button btnModificarUsuario;

    
    @FXML
    private ComboBox<String> cbCodigoPostalUsu;
    @FXML
    private ComboBox<String> cbCiudadUsu;
    @FXML
    private ComboBox<String> cbColoniaUsu;
    @FXML
    private ComboBox<String> cbPermisoUsu;
    @FXML
    private Label lblError;
    @FXML
    private ComboBox<String> cbContratoUsu;
    @FXML
    private CustomTextField autoComTxt;
    
    private ModelUsuarios selectedUsu;
    private AutoCompletionBinding<ModelUsuarios> autoCompletionBinding;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          cbPermisoUsu.getItems().addAll("alto","medio","bajo");
            cbContratoUsu.getItems().addAll("Activo","Terminado");
        
       cargarDatos();
        
 
        
          ResultSet resul=null;  
        try {
            resul=Helpers.db.getDbCon().query("Select CodigoPostal from CodigosPostales where Estado='Jalisco'");
        } catch (SQLException ex) {
            Logger.getLogger(ModificarUsuarios2.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            try {
                  
                while(resul.next()){
                        cbCodigoPostalUsu.getItems().add(resul.getString(1));
                        
                }   
                resul.close();
               
            } catch (SQLException ex) {
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
        txtContraseñaUsu.textProperty().addListener(new ChangeListener<String>() 
        {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                if(newValue.matches("([A-Za-z]||[0-9])*") && newValue.length()<30)
                {
                    txtContraseñaUsu.setText(newValue);
                }
                else
                {   
                    txtContraseñaUsu.setText(oldValue);
                }
            }
        });      
        txtCalleUsu.textProperty().addListener(new ChangeListener<String>()
        {
             @Override
             public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
             {
                if(newValue.matches("([A-Za-z0-9]*\\s?)*#?[A-Za-z0-9]*") && newValue.length()<50)
                {
                    txtCalleUsu.setText(newValue);
                }else
                {
                    txtCalleUsu.setText(oldValue);
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

   

    
    private void cargarDatos()
    {
         ResultSet rs;
        try 
        {
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
           rs.close();
        autoCompletionBinding=TextFields.bindAutoCompletion(autoComTxt,usuarios);
           
         autoCompletionBinding.setOnAutoCompleted(e->Cargar(selectedUsu=e.getCompletion())); 
     
          
        } catch (SQLException ex) {
            Logger.getLogger(ModificarUsuarios2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnCancelarModificar(ActionEvent event) {
    }

    @FXML
    private void btnModificarUsuario(ActionEvent event) {
       
        boolean ban=false;
        
         String mensageE="Campo: ";
          if(txtNomUsu.getText().equals("")){
              mensageE+="Nombre";
              ban=true;
          }
          
          if(txtCalleUsu.getText().equals("")){
              mensageE+=", Calle";
              ban=true;
          }
          if(cbColoniaUsu.getValue()==null){
              mensageE+=", Colonia";
              ban=true;
          }
         if(cbCiudadUsu.getValue()==null){
              mensageE+=", Ciudad";
              ban=true;
          }
         if(cbCodigoPostalUsu.getValue()==null){
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
         if(cbPermisoUsu.getValue()==null){
              mensageE+=", Permiso";
              ban=true;
          }
         
          mensageE+=" vacio";
         // lblError.setText(mensageE);
          //lblError.setVisible(true);
          if(ban){
              Helpers.AlertBox.display("Error", mensageE);
              ban=false;
          }
           if(!txtNomUsu.getText().equals("")
           
           && !txtCalleUsu.getText().equals("")
           && cbColoniaUsu.getValue()!=null
           && cbCiudadUsu.getValue()!=null
           && cbCodigoPostalUsu.getValue()!=null
           && !txtTelefonoUsu.getText().equals("")
           && !txtSueldoUsu.getText().equals("")
           && !txtPuntosUsu.getText().equals("") 
           && cbPermisoUsu.getValue()!=null
           
          )
        {
            int Permiso;
            if(cbPermisoUsu.getValue().equals("alto")){
                Permiso=3;
            }else if(cbPermisoUsu.getValue().equals("medio")){
                Permiso=2;
            }else {
                Permiso=1;
            }
            int contrato = 1;
            if(cbContratoUsu.getValue().equals("Activo")){
                contrato=1;
            }else if(cbContratoUsu.getValue().equals("Terminado")){
                   contrato=0;
            }
            
            String pass=Helpers.Crypto.MD5(txtContraseñaUsu.getText());
                ModelUsuarios modUsu=new ModelUsuarios(Integer.parseInt(txtIdUsu.getText()),txtNomUsu.getText(), 
                        pass,txtCalleUsu.getText(),cbColoniaUsu.getValue(), 
                        cbCiudadUsu.getValue(),cbCodigoPostalUsu.getValue(),txtTelefonoUsu.getText(),
                        null, (int) Double.parseDouble(txtSueldoUsu.getText()), (int) Double.parseDouble(txtPuntosUsu.getText()),Permiso,
                        contrato);
                
                if(!txtContraseñaUsu.getText().isEmpty()){
                    modUsu.updatePass();
                }else{
                modUsu.update();
                
                    
                }
            //lblError.setText("modificado Correcto");
                
            Limpiar();
            Helpers.AlertBox.display("Éxito","Modificación correcta");
            gridContenedorDatos.setVisible(false);
            autoCompletionBinding.dispose();
            cargarDatos();
            
            
    }
     
    
    }
    
   
     

    @FXML
    private void eventCodigoPostal(ActionEvent event) 
    {
        if(cbCodigoPostalUsu.getValue()!=null){
       
          ResultSet resul=null;  
            try {
                resul=Helpers.db.getDbCon().query("Select Municipio from CodigosPostales where CodigoPostal='"+cbCodigoPostalUsu.getValue() +"'");
            } catch (SQLException ex) {
                Logger.getLogger(ModificarUsuarios2.class.getName()).log(Level.SEVERE, null, ex);
            }
            cbCiudadUsu.getItems().clear();
            try {
                  
                while(resul.next()){
                      cbCiudadUsu.getItems().add(resul.getString(1));
                        cbCiudadUsu.setValue(resul.getString(1));
                }
                resul.close();
               AñadirColonias();
            } catch (SQLException ex) {
                Logger.getLogger(AgregarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
        }
        
    
    }
    
    private void Limpiar() {
            autoComTxt.setText("");
            
            txtNomUsu.setText("");
            txtContraseñaUsu.setText("");
            txtCalleUsu.setText("");
            cbColoniaUsu.setValue("");
            cbCiudadUsu.setValue("");
            cbCodigoPostalUsu.setValue("");
            txtTelefonoUsu.setText("");
            txtSueldoUsu.setText("");
            txtPuntosUsu.setText("");
            cbPermisoUsu.setValue("");
            autoComTxt.setText("");
}

    private void Cargar(ModelUsuarios completion) 
    {
         gridContenedorDatos.setVisible(true);
        txtNomUsu.setText(selectedUsu.getNombreUsu());
       // txtContraseñaUsu.setText("*******************");
        txtCalleUsu.setText(selectedUsu.getCalleUsu());
        cbCiudadUsu.setValue(selectedUsu.getCiudUsu());
        cbColoniaUsu.setValue(selectedUsu.getColoUsu());
        int contrato=selectedUsu.getEstadoContrado();
        if(contrato==0){
            cbContratoUsu.setValue("Terminado");
        
        }
        else{
            cbContratoUsu.setValue("Activo");
          
        }
        
        cbCodigoPostalUsu.setValue(selectedUsu.getCodigoPostal());
        txtIdUsu.setText(String.valueOf(selectedUsu.getIdUsu()));
        int permiso=selectedUsu.getTipoPermiso();
        if(permiso==3){
          
            cbPermisoUsu.setValue("Alto");
        }
        else if(permiso==2)
        {
            
            cbPermisoUsu.setValue("medio");
        }else 
        {
            
            cbPermisoUsu.setValue("bajo");
        }
              
        txtPuntosUsu.setText(String.valueOf(selectedUsu.getPuntosUsu()));
        txtSueldoUsu.setText(String.valueOf(selectedUsu.getSueldoUsu()));
        txtTelefonoUsu.setText(selectedUsu.getTelefonoUsu());
        
    }

        

    private void AñadirColonias() {
              if(cbCiudadUsu.getValue()!=null){
           
          ResultSet resul=null;  
          String [] Colon=null;
            try {
                resul= Helpers.db.getDbCon().query("Select Colonia from CodigosPostales where Municipio='"+cbCiudadUsu.getValue() 
                        +  "' and CodigoPostal='" + cbCodigoPostalUsu.getValue()+"'");
           
                   cbColoniaUsu.getItems().clear();
                while(resul.next()){
                    String temp=resul.getString(1);
                    Colon=separarColonias(temp);
                   
                    //cbColonia.getItems().add(resul.getString(1));
                    cbColoniaUsu.setValue(Colon[0]);
                for (int i = 0; i <Colon.length; i++) {
                    cbColoniaUsu.getItems().add(Colon[i]);
                }
                }
                
                resul.close();
                
                
            } catch (SQLException ex) {
                Logger.getLogger(AgregarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
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

}

