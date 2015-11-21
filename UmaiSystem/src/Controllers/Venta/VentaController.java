/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Venta;

import Controllers.Piso.PisoController;
import Helpers.db;
import Models.ModelVentaTV;
import Models.ModelVentas;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author antonio
 */
public class VentaController implements Initializable {
    @FXML
    private Label Mesalbl;
    
    private List<Button> categorias = new ArrayList<Button>();
    @FXML
    private ScrollPane panel;
    @FXML
    private TilePane tiled;
    
    private ObservableList<ModelVentaTV> data = //Guarda los datos del tableview
        FXCollections.observableArrayList(
        );
    
     private HashMap<String,ObservableList> datosXM=new HashMap<>();
    @FXML
    private TableColumn<ModelVentaTV, String> productoCell;
    @FXML
    private TableColumn<ModelVentaTV, Integer> precioCell;
    @FXML
    private TableColumn<ModelVentaTV, Integer> cantidadCell;
    @FXML
    private TableColumn<ModelVentaTV, Integer> totalCell;
    @FXML
    private TableView<ModelVentaTV> notaTbl;
    @FXML
    private Button deleteProduct;
    @FXML
    private Button addProduct;
    @FXML
    private Label totalLbl;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    
    String mesa="";
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCategorias(tiled);
        
        //inicializamos el datagridview
    //Inicializamos las columnas del TableView
        productoCell.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precioCell.setCellValueFactory(new PropertyValueFactory<>("precio"));
        cantidadCell.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        totalCell.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        notaTbl.setItems(data);
      
    }    
    
    private void loadCategorias(TilePane p)
    {
        ResultSet rs;    
        try {
            
            rs = db.getDbCon().query("Select * from categorias;");
            
            while(rs.next())
            {
                  Button b = new Button(rs.getString(2));
                  
                  b.setPrefSize(100,100);
                  //les agregamos un 
                  int id = rs.getInt(1);
                  b.setOnAction((event) -> {
                      p.getChildren().clear();
                      
                          
                          loadProducts(tiled,id);
                     
                     
                  });
                  p.getChildren().add(b);
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metodo que agrega los productos
     * @param p Pane en el que se agregaran los botones
     * @param idCat categoria de los productos que se cargaran
     */
    private void loadProducts(TilePane p,int idCat)
    {
        HashMap<String,String> element=new HashMap<>();
         ResultSet rs;    
        try {
            System.out.println(idCat+"Desde metodo loadproducts");
            rs = db.getDbCon().query("Select * from receta WHERE categoria="+idCat);//obtenemos las recetas
            Button Regresar = new Button("regresar"); //boton para regresara las categorias principal
            Regresar.setPrefSize(100, 100);
            Regresar.setOnAction((event) -> {
                      p.getChildren().clear();
                     
                      //cargamos categorias    
                      loadCategorias(p);
                     
                     
                  });
             p.getChildren().add(Regresar);
            while(rs.next())
            {
                 int precio = rs.getInt(11);
                 
                 String nombre = rs.getString(2);
               Button b = new Button(rs.getString(2));
                  
                  b.setPrefSize(100,100);
                  //les agregamos un 
                 int id = rs.getInt(1);
                  b.setOnAction((event) -> {
                      
                     data.add(new ModelVentaTV(idCat, nombre,precio,1,precio*1,id));
                      totalLbl.setText(String.valueOf(getTotal()));
                      new PisoController().setData(mesa, data);
                  });
                  p.getChildren().add(b);
                  
            }
        } catch (SQLException ex) {
            
        }
    }
    
    public void setMesa(String mesa)
    {
           Mesalbl.setText(mesa);
           this.mesa=mesa;
                   
    }
   
    
   
    
    private void anadeTest(ActionEvent event) {
        Button b = new Button("test");
        b.setPrefSize(100,   100);
        tiled.getChildren().add(b);
        
    }

    private void limpiar(ActionEvent event) {
        tiled.getChildren().clear();
    }
        
    @FXML
    private void addProduct(ActionEvent event) {
        ModelVentaTV selected = notaTbl.getSelectionModel().getSelectedItem();
        
       if(selected != null)
       {
           selected.setCantidad(selected.getCantidad()+1);
           
           selected.actualizaTotal();
           totalLbl.setText(String.valueOf(getTotal()));
         //  notaTbl.setItems(data);
          
           notaTbl.getProperties().put(TableViewSkinBase.RECREATE, Boolean.TRUE);
          
        //   notaTbl.refresh();
           new PisoController().setData(mesa,data);
       }
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
       ModelVentaTV selected = notaTbl.getSelectionModel().getSelectedItem();
       
       if(selected != null)
       {
           selected.setCantidad(selected.getCantidad()-1);
           selected.actualizaTotal();
           //si la cantidad es menor a 1 borrar ese item de la lista
           if(selected.getCantidad()<1)
           {
               data.remove(selected);
               new PisoController().setData(mesa,data);
           }
           totalLbl.setText("$ "+String.valueOf(getTotal()));
         //  notaTbl.refresh();
            notaTbl.getProperties().put(TableViewSkinBase.RECREATE, Boolean.TRUE);
           
           
       }
    }
    
    
   public void setdatos(ObservableList dataPiso){
       if(dataPiso!=null){ 
       data=dataPiso;
        notaTbl.setItems(data);
        totalLbl.setText(String.valueOf(getTotal()));
       }
   }
    
    private int getTotal()
    {
        int total = 0;
        for(ModelVentaTV m : data)
        {
                total+=m.getTotal();
        }
        
        return total;
    }

    @FXML
    private void eventbtnGuardar(ActionEvent event) {
        Calendar c1 = Calendar.getInstance();
        String fecha="";
         fecha+=String.valueOf(c1.get(Calendar.YEAR))+"-";
        fecha+=String.valueOf(c1.get(Calendar.MONTH)+1)+"-";
       
       fecha+=String.valueOf(c1.get(Calendar.DATE));
           
        int idventa=0,idDetalle = 0;
        if(!data.isEmpty())
        {
            ResultSet rs;
            
            new ModelVentas().saveVenta(fecha,getTotal(),Mesalbl.getText());  // se guarda la venta
            idventa=new ModelVentas().getidventaMax();
            idDetalle=new ModelVentas().getidDetalleV();
            for (int i = 0; i <data.size(); i++) {
                System.out.println((idDetalle+i)+","+ idventa+","+ data.get(i).getPrecio()+","+
                        data.get(i).getCantidad()+","+data.get(i).getIdReceta());
            ModelVentas md=new ModelVentas((idDetalle+i), idventa, data.get(i).getPrecio(),data.get(i).getCantidad(),data.get(i).getIdReceta());
            md.saveDetalleVentas();
            }
            data.clear();
            notaTbl.setItems(data);
           totalLbl.setText("");
        }
    }

    @FXML
    private void eventCancelar(ActionEvent event) {
        
       if(!data.isEmpty()){
        int confi=JOptionPane.showConfirmDialog(null,"la accion borrara el pedido!","borrar pedido",2);
        if(confi==0){
           new PisoController().borrarData(mesa);
           data.clear();
           
        }else{
            Helpers.AlertBox.display("Advertencia","Sin datos");
        }
       }
    }
    
}
