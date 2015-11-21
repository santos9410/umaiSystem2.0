/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Piso;

import Controllers.Venta.VentaController;
import Models.ModelVentaTV;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author 
 */
public class PisoController implements Initializable {

    
    @FXML
    private Button mesa1;
    @FXML
    private AnchorPane PisoContainer;
    @FXML
    private Button mesa2;
    @FXML
    private Button mesa3;
    @FXML
    private Button mesa4;
    @FXML
    private Button mesa5;
    @FXML
    private Button mesa6;
    @FXML
    private Button mesa7;
    @FXML
    private Button mesa8;
    @FXML
    private Button mesa9;
    @FXML
    private Button mesa10;
    @FXML
    private Button mesa12;
    @FXML
    private Button mesa13;
    @FXML
    private Button mesa14;
    @FXML
    private Button mesa11;
    @FXML
    private Button mesa15;
    
    private static  ObservableList<ModelVentaTV> data = //Guarda los datos del tableview
        FXCollections.observableArrayList(
        );
   private static HashMap<String,ObservableList> datosXM=new HashMap<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void TicketShow(ActionEvent event) {
        //obtemos el Boton que fue el que activo para pasarlo a la ventana de 
        Button e = (Button) event.getSource();
        
         

           FXMLLoader loader = new FXMLLoader(
        getClass().getResource(
      "/Views/Venta/Venta.fxml"
    )
  );

  Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException ex) {
            Logger.getLogger(PisoController.class.getName()).log(Level.SEVERE, null, ex);
        }

  VentaController controller = 
    loader.<VentaController>getController();
  controller.setMesa(e.getId());
  //controller
  //colocamos en forma modal la ventana 
stage.initModality(Modality.WINDOW_MODAL);
//Obtenemos el dueño que ejecuto, en este caso por medio del evento obtemos que windows es el dueño
stage.initOwner(
        ((Node)event.getSource()).getScene().getWindow() );
  stage.show();
        
    controller.setdatos(datosXM.get(e.getId())); // le manda los datos guardados temporalmente
        
    }

    
    
    
    public   void setData(String mesa,ObservableList data1){ //este metodo asigna a un observablelist lo que recibe 
        // de la clase ventasController y es para guardar el pedido temporalmente
        data=data1;
        datosXM.put(mesa, data); // cada mesa tiene sus propios datos por eso es importante guardalos sin perder ninguno
       
    }
    public void borrarData(String mesa){
        datosXM.remove(mesa);
    }
}
