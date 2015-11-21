/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Reportes;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class ReporteInventarioController implements Initializable {
    @FXML
    private VBox contenedorCampos;
    @FXML
    private Button btnGenerar;
CheckBox cb[];
    
     String camposQuery[];
    @FXML
    private Label lblTabla;
    @FXML
    private Button btnRegresar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 lblTabla.setText("Inventario");
                 String campos[]=new String[]{"nombreProd","descripcionProd","unidadMedidadProd","precioCompra","cantidadStock"};
                 String nombresCampos[]=new String[]{"nombre Producto","descripcion Producto","unidad Medida","precio Compra","cantidad"};
                 camposQuery=new String[]{"nombreProd","descripcionProd","unidadMedidadProd","precioCompra","cantidadStock"};
          cb=new CheckBox[campos.length];
        
         for (int i = 0; i <campos.length; i++) {
                
        
           cb[i]=new CheckBox();
 
            cb[i].setText(nombresCampos[i]);
            cb[i].setId(campos[i]);
            cb[i].setStyle(
                    "-fx-border-color: lightblue; "
                            + "-fx-font-size: 16;"
                            + "-fx-border-insets: -2;"
                            + "-fx-border-radius: 2;"
                            + "-fx-border-style: dotted;"
                            + "-fx-border-width: 1;"          
            );
            
            contenedorCampos.setMargin(cb[i],new Insets(10,0,10,100));
            contenedorCampos.getChildren().add(cb[i]);
          
            }
          
    }    

    @FXML
    private void eventGenerarReporteInventario(ActionEvent event) throws JRException {
        int cont = 0;
        int cont3=0;
        String selecCampo="";
        for (int i = 0; i <cb.length; i++) {
              if(cb[i].isSelected()){
                  System.out.println(cb[i].getId());
                  cont++;
              }
              
        }
          // se debe conocer la cantidad de campos seleccionados
          
        
        int ancho=580/(cont+1);
        ArrayList<AbstractColumn>colum=new ArrayList<>();
        AbstractColumn  nombres[];
         int pun=0;
        if(cont!=0)
        {
            nombres=new AbstractColumn[cont];
            for (int i = 0; i <cb.length; i++) 
            {
                if(cb[i].isSelected())
                {
                     if(i==3){
                        nombres[pun]=ColumnBuilder.getNew()
                        .setColumnProperty(cb[i].getId(),Integer.class.getName())
                        .setTitle(cb[i].getText()).setWidth(ancho)
                        .setPattern("$ 0.00")		//defines a pattern to apply to the values swhown (uses TextFormat)
                    //.setStyle(amountStyle)		//special style for this column (align right)
                        .build();
                        pun++;
                      if(selecCampo.equals("")){
                            selecCampo=camposQuery[i];
                        }
                        else{
                            selecCampo+=","+camposQuery[i];
                        }
                     
                     } else{
                         nombres[pun]=ColumnBuilder.getNew()
                    .setColumnProperty(cb[i].getId(),String.class.getName())
                    .setTitle(cb[i].getText()).setWidth(ancho)
                    .build();
                          pun++;
                 
                 // formando el query // parte del select 
                 
                 if(selecCampo.equals("")){
                            selecCampo=camposQuery[i];
                        }
                        else{
                            selecCampo+=","+camposQuery[i];
                        }
                     }
                }
            }
            
             DynamicReportBuilder drb = new DynamicReportBuilder();
        DynamicReport dr = null;
        for (int i = 0; i <pun; i++) {
               drb.addColumn(nombres[i]).setMargins(50,50,20,20);
        }
        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE))+"/";
        dia+= Integer.toString(c.get(Calendar.MONTH)+1)+"/";
        dia+= Integer.toString(c.get(Calendar.YEAR));
        drb
               // .addGroups(2)
                .addImageBanner("./img/umaiSystem.png",70,70,ImageBanner.ALIGN_RIGHT)
                .setTitle("Reporte de Inventario")
                .setSubtitle("Fecha de creacion: " + dia)
                .setDetailHeight(15)
                .setMargins(50,50, 30, 15)
                .setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true)
                .setColumnsPerPage(1);
        
        String sql="";
        sql ="Select "+selecCampo+" FROM productos  where cantidadStock>0";
            System.out.println("sql "+sql);
        Connection conexion = null;
        //conexion=Helpers.db.db.conn;
        conexion=Helpers.db.getDbCon().conn;
        
         drb.setQuery(sql, DJConstants.QUERY_LANGUAGE_SQL);
          dr = drb.build(); //Finally build the report! 
  //       JasperPrint jp=DynamicJasperHelper.generateJasperPrint(dr,new ClassicLayoutManager(),ds);
       JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(),conexion,null);
        
        JasperViewer.viewReport(jp,false);    //finally display the report report
        
        }
    }

    @FXML
    private void eventRegresar(ActionEvent event) {
        Controllers.ControllerStage cs=new Controllers.ControllerStage();
            cs.openNewWindow("/Views/Reportes/SeleccionarReporte.fxml");
    }
    
}
