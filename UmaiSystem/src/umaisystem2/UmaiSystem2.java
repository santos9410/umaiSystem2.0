
package umaisystem2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 
 */
public class UmaiSystem2 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       Parent root;
        try {
            
//                root = FXMLLoader.load(getClass().getResource("/Views/login.fxml"));
               root = FXMLLoader.load(getClass().getResource("/Views/principal.fxml"));
                Scene scene = new Scene(root);
                 //primaryStage.initStyle(StageStyle.UNDECORATED);

                primaryStage.setScene(scene);
                
               //primaryStage.minHeightProperty().set(1024);
                //primaryStage.minWidthProperty().set(768);
                primaryStage.show();
                Controllers.ControllerStage cs=new Controllers.ControllerStage();
                cs.setStage(primaryStage);
                
        } catch (IOException ex) {
            Logger.getLogger(UmaiSystem2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
