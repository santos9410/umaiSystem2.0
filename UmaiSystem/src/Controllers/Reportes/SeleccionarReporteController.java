/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Reportes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.JRException;
/**
 * FXML Controller class
 *
 * @author lalo
 */
public class SeleccionarReporteController implements Initializable {
    @FXML
    private Button btnVentas;
    @FXML
    private Button btnVentasProducto;
    @FXML
    private Button btnEntradasSalidas;
    @FXML
    private Button btnProductosmasV;
    @FXML
    private Button btnInventario;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void eventGenerarVentas(ActionEvent event) throws JRException {
      
        Controllers.ControllerStage cs=new Controllers.ControllerStage();
        cs.openNewWindow("/Views/Reportes/Ventas.fxml");
         //cs.openNewWindow("/Views/Piso/Piso.fxml");
        /*
        try {
            
       
        String mensaje="Ingresa un nombre para el reporte";
      String nombre;      
 
        File folder = new File("./reportes");
        if (!folder.exists()) {
            folder.mkdirs();
           
        }
       
        //nombre=JOptionPane.showInputDialog(null, mensaje);    
        nombre=Helpers.InputBox.display("input",mensaje);
        if(!nombre.isEmpty()){
            
             
            
            
            
            File f = new File("./reportes/"+nombre+".pdf");
            
            if(!f.exists()){
    
 
        Connection conexion=db.getDbCon().conn;
  
        InputStream archivo = null;
        try {
            archivo = new FileInputStream("src/views/Reportes/Ventas.jasper");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SeleccionarReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperReport reporte = null;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
        } catch (JRException ex) {
            Logger.getLogger(SeleccionarReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,null,conexion); // este es una plantilla ya predefinida
        Exporter exporter=new JRPdfExporter();
           
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("./reportes/"+nombre+".pdf"));
       
        
        
        exporter.exportReport();   
        Helpers.AlertBox.display("Exito","pdf generado con exito");
      
        }
            else{
                Helpers.AlertBox.display("Error","el nombre ya existe");
            }
        
            
        }
    
         } catch (Exception e) {
              Helpers.AlertBox.display("Error","No se pudo generar el pdf");
        }
        */
    }


    @FXML
    private void eventGenerarProductos(ActionEvent event) {
          Controllers.ControllerStage cs=new Controllers.ControllerStage();
        cs.openNewWindow("/Views/Reportes/Productos.fxml");
        /*
        
         try {
   
        String mensaje="Ingresa un nombre para el reporte";
      String nombre;      
 
        File folder = new File("./reportes");
        if (!folder.exists()) {
            folder.mkdirs();
           
        }
       
        //nombre=JOptionPane.showInputDialog(null, mensaje);    
        nombre=Helpers.InputBox.display("input",mensaje);
        if(!nombre.isEmpty()){
            
             
            
            
            
            File f = new File("./reportes/"+nombre+".pdf");
            
            if(!f.exists()){
    
 
        Connection conexion=db.getDbCon().conn;
        
        InputStream archivo = null;
        try {
            archivo = new FileInputStream("src/views/Reportes/VentasProductos.jasper");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SeleccionarReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperReport reporte = null;
        try {
            reporte = (JasperReport) JRLoader.loadObject(archivo);
        } catch (JRException ex) {
            Logger.getLogger(SeleccionarReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,null,conexion); // este es una plantilla ya predefinida
        Exporter exporter=new JRPdfExporter();
           
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("./reportes/"+nombre+".pdf"));
       
        
        
        exporter.exportReport();   
        Helpers.AlertBox.display("Exito","pdf generado con exito");
      
        }
            else{
                Helpers.AlertBox.display("Error","el nombre ya existe");
            }
        
            
        }
    
         } catch (Exception e) {
              Helpers.AlertBox.display("Error","No se pudo generar el pdf");
        }
                */
    }

    @FXML
    private void eventEntradasSalidas(ActionEvent event) {
        
    }

    @FXML
    private void eventProductosmasV(ActionEvent event) {
           Controllers.ControllerStage cs=new Controllers.ControllerStage();
        cs.openNewWindow("/Views/Reportes/ProductosVendidos.fxml");
    }

    @FXML
    private void eventInventario(ActionEvent event) {
        Controllers.ControllerStage cs=new Controllers.ControllerStage();
        cs.openNewWindow("/Views/Reportes/Inventario.fxml");
        
        
    }
    
}
