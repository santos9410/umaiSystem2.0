
package Controllers;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *Clase ControllerStage nos ayuda para abrir un fxml y pasarlo a un anchorpane
 * 
 * @author Chillorios
 */
public class ControllerStage {
    
   private  static Stage stage;
   private static Scene scene;
   private static AnchorPane panelP;
   
  
  //Getter and setters  
  public void setStage(Stage stage){
       ControllerStage.stage=stage;
      
  }
  public Stage getStage(){
      
      return stage;
      
  }

    /**
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @param scene the scene to set
     */
    public void setScene(Scene scene) {
        ControllerStage.scene = scene;
    }
    
    
    
    public void setPaneAnchor(AnchorPane pane){
         this.panelP=pane;
    }
    
    /**
     * El método openNewWindow Recibe como parametro un String que indica la direccion 
     * de donde se encuentra el archivo FXML a cargar.
     * @param FXMLFile 
     */
    public void openNewWindow(String FXMLFile)
    {
        try 
        {            
            URL url = getClass().getResource(FXMLFile);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 
            panelP.getChildren().clear();
            panelP.getChildren().add(page);
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    
    

}
