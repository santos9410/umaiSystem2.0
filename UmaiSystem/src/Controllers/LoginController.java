/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Helpers.AlertBox;
import Helpers.Crypto;
import Helpers.db;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author antonio
 */
public class LoginController implements Initializable {
    @FXML
    private Button entrarBtn;
    @FXML
    private TextField userTxt;
    @FXML
    private PasswordField passTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // userTxt = new TextField();
        //passTxt  = new PasswordField();
        userTxt.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
        @Override
        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
        {
            if (newPropertyValue)
            {
                userTxt.setStyle("-fx-border-color: #000");
                userTxt.setStyle("-fx-background-color: #fff");
                userTxt.setText("");
            }
        }


            });
    }    

    
    
    @FXML
    private void entrarBtn(ActionEvent event) throws IOException {
        try {
            String txtPass = null;
            ResultSet rs = db.getDbCon().query("Select pswUsu from usuarios where nombreUsu='"+userTxt.getText()+"' limit 1;");
            
            if(rs.next())
            {
              txtPass = rs.getString("pswUsu");
              
              if(txtPass.equals(Crypto.MD5(passTxt.getText())))
              {
                    //CARGAMOS EL ESTAGE PRINCIPAL
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/principal.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    Rectangle2D r = Screen.getPrimary().getBounds();
                    stage.setWidth(r.getWidth());
                    stage.setHeight(r.getHeight());
                    stage.setTitle("Umai System");
                    stage.setScene(new Scene(root1));  
                    stage.show();
                     Stage stage2 = (Stage) entrarBtn.getScene().getWindow();
                    // do what you have to do
                     stage2.close();
              }
              else
              {
              AlertBox.display("Umai System", "Error en la contraseña");
              }
                
            }
            else
            {
                userTxt.setStyle("-fx-background: #992e2e");
                userTxt.setStyle("-fx-background-color: #f00");
                AlertBox.display("Umai system", "El usuario ingresado no existe");
                System.out.println("No existe el usuario");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void change(InputMethodEvent event) {
    }

   
    
}
