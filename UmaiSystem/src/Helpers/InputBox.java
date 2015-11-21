/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
/**
 *
 * @author lalo
 */
public class InputBox {

    public static String display(String title, String message) {
        Stage window = new Stage();
        
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        
        Label label = new Label();
        label.setText(message);
        TextField txtnombre=new TextField();
        txtnombre.textProperty().addListener(new ChangeListener<String>()
        {
        
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if(newValue.matches("^[A-Za-z]*(\\s?[A-Za-z0-9.-]*)*"))
                {
                    txtnombre.setText(newValue);
                }
                else
                {
                    txtnombre.setText(oldValue);
                }
            }
        }); 
        Button closeButton = new Button("Aceptar");
        closeButton.setMinSize(150, 30);
       
        closeButton.setOnAction(e -> window.close());
        
        VBox layout = new VBox(15);
        layout.getChildren().addAll(label,txtnombre, closeButton);
        layout.setAlignment(Pos.CENTER);
            
        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return txtnombre.getText();
    }

}