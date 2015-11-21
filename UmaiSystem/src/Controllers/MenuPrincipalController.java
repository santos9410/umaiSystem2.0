
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class MenuPrincipalController implements Initializable {
    @FXML
    private Label lblNomVista;
    @FXML
    private Button btnAgregarUsuario1;
    @FXML
    private Button btnConsultarUsuario1;
    @FXML
    private Button btnModificarUsuario1;
    @FXML
    private Button btnEliminarUsuario1;
    @FXML
    private Button btnAgregarProducto1;
    @FXML
    private Button btnConsultarProducto1;
    @FXML
    private Button btnModificarProducto1;
    @FXML
    private Button btnEliminarProducto1;
    @FXML
    private Button btnAgregarProveedor1;
    @FXML
    private Button btnConsultarProveedor1;
    @FXML
    private Button btnModificarProveedor1;
    @FXML
    private Button btnEliminarProveedor1;
    @FXML
    private Button btnAgregarReceta1;
    @FXML
    private Button btnConsultarReceta1;
    @FXML
    private Button btnModificarReceta1;
    @FXML
    private Button btnEliminarReceta1;
    @FXML
    private AnchorPane EscenaPrincipal;
    
    Controllers.ControllerStage cs=new Controllers.ControllerStage();
    @FXML
    private Button btnAgregarCompra;
    @FXML
    private Button btnPisoVisible;
    @FXML
    private Button btnCrearReporte;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cs.setPaneAnchor(EscenaPrincipal);
       lblNomVista.setText("Umai System");
    }    

    @FXML
    private void btnAgregarUsuarioclick(ActionEvent event) 
    {
     cs.openNewWindow("/Views/Usuarios/AgregarUsuario.fxml");   
    }

    @FXML
    private void btnConsultarUsuarioClick(ActionEvent event) {
    cs.openNewWindow("/Views/Usuarios/ConsultarUsuario.fxml"); 
    }

    @FXML
    private void btnModificarUsuarioClick(ActionEvent event) {
    cs.openNewWindow("/Views/Usuarios/ModificarUsuario2.fxml"); 
    }

    @FXML
    private void btnEliminarUsuarioClick(ActionEvent event) {
        cs.openNewWindow("/Views/Usuarios/EliminarUsuario.fxml"); 
    }

    @FXML
    private void btnPisoVisibleClick(ActionEvent event) {
        cs.openNewWindow("/Views/Piso/Piso.fxml");
        
    }

    @FXML
    private void btnAgregarProductoClick(ActionEvent event) {
      //  cs.setPaneAnchor(EscenaPrincipal);
        cs.openNewWindow("/Views/Productos/AgregarProducto.fxml");
    }

    @FXML
    private void btnConsultarProductoClick(ActionEvent event) {
        //cs.setPaneAnchor(EscenaPrincipal);
        cs.openNewWindow("/Views/Productos/ConsultarProducto.fxml");
        
    }

    @FXML
    private void btnModificarProductoClick(ActionEvent event) {
        //cs.setPaneAnchor(EscenaPrincipal);
        cs.openNewWindow("/Views/Productos/ModificarProducto2.fxml");
    }

    @FXML
    private void btnEliminarProductoClick(ActionEvent event) {
        //cs.setPaneAnchor(EscenaPrincipal);
        cs.openNewWindow("/Views/Productos/EliminarProducto.fxml");
    }

    @FXML
    private void btnAgregarProveedorClick(ActionEvent event) {
        cs.openNewWindow("/Views/Proveedor/AgregarProveedor.fxml");
    }

    @FXML
    private void btnConsultarProveedorClick(ActionEvent event) {
        cs.openNewWindow("/Views/Proveedor/ConsultarProveedor.fxml");
    }

    @FXML
    private void btnModificarProveedorClick(ActionEvent event) {
        cs.openNewWindow("/Views/Proveedor/ModificarProveedor.fxml");
    }

    @FXML
    private void btnEliminarProveedorClick(ActionEvent event) {
       cs.openNewWindow("/Views/Proveedor/EliminarProveedor.fxml");
    }

    @FXML
    private void btnAgregarRecetaClick(ActionEvent event) {
         cs.openNewWindow("/Views/Receta/agregarRecetas.fxml");
    }

    @FXML
    private void btnConsultarRecetaClick(ActionEvent event) {
    }

    @FXML
    private void btnModificarRecetaClick(ActionEvent event) {
    }

    @FXML
    private void btnEliminarRecetaClick(ActionEvent event) {
    }

    @FXML
    private void btnAgregarCompraClick(ActionEvent event) {
         cs.openNewWindow("/Views/Productos/AgregarCompras.fxml"); 
    }

    @FXML
    private void eventCrearReporte(ActionEvent event) {
          cs.openNewWindow("/Views/Reportes/SeleccionarReporte.fxml"); 
      //    new GenerarDatos().setVisible(true);
    }
    
    
}
