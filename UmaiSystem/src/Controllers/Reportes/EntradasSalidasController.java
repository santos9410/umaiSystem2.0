/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Reportes;

import Helpers.AlertBox;
import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.awt.Color;
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
public class EntradasSalidasController implements Initializable {
    @FXML
    private VBox ContenedorCampos;
    @FXML
    private VBox ContenedorGrupos;
    @FXML
    private Button btnGenerar;
    CheckBox cb[];
    CheckBox grupos[];
    String camposQuery[];
    @FXML
    private Label lblTabla;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         String []campos =new String[]{"Nombre_Producto","Tipo_de_producto","Fecha_de_Venta","Precio_Venta","cantidad","totalVenta"};
        
         String[] nombresCampos=new String[]{"Nombre Producto","Tipo de producto","Fecha de Venta","Precio Venta","cantidad","total Venta"};
        
        camposQuery=new String[]{" receta.nombreRec AS Nombre_Producto "," receta.tipoRec AS Tipo_de_producto "
            ," STR_TO_DATE(ventas.fechaVenta,'%d-%m-%Y') AS Fecha_de_Venta "," receta.precio_venta AS Precio_Venta "
            ,"detalle_ventas.cantidad AS cantidad "," (receta.precio_venta*Sum(detalle_ventas.cantidad)) AS totalVenta "};
                
             
        
        lblTabla.setText("Ventas detalladas");
          cb=new CheckBox[campos.length];
         grupos=new CheckBox[campos.length];
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
            
            ContenedorCampos.setMargin(cb[i],new Insets(10,0,10,100));
            ContenedorCampos.getChildren().add(cb[i]);
           grupos[i]=new CheckBox();
           grupos[i].setText(nombresCampos[i]);
           
           if(i==0){grupos[i].setSelected(true); grupos[i].setDisable(true);}
           grupos[i].setId(campos[i]);
                grupos[i].setStyle(
                    "-fx-border-color: lightblue; "
                            + "-fx-font-size: 16;"
                            + "-fx-border-insets: -2;"
                            + "-fx-border-radius: 2;"
                            + "-fx-border-style: dotted;"
                            + "-fx-border-width: 1;"          
            );      
            ContenedorGrupos.setMargin(grupos[i],new Insets(10,0,10,100));
            ContenedorGrupos.getChildren().add(grupos[i]);
            }
    }    

    @FXML
    private void eventGenerar(ActionEvent event) throws JRException {
           Style titleStyle = new Style();
 		titleStyle.setFont(new Font(18,Font._FONT_VERDANA,true));
  		Style amountStyle = new Style();
  		amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
  		Style oddRowStyle = new Style();
  		oddRowStyle.setBorder(Border.NO_BORDER());
  		Color veryLightGrey = new Color(230,230,230);
  		oddRowStyle.setBackgroundColor(veryLightGrey);oddRowStyle.setTransparency(ar.com.fdvs.dj.domain.constants.Transparency.OPAQUE);
        int cont=0;
          String selecCampo="";
        for (int i = 0; i <cb.length; i++) {
            if(cb[i].isSelected()){
                  cont++;
            }
              
        }
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
                     if(i==3 || i==5)
                    {
                        nombres[pun]=ColumnBuilder.getNew()
                            .setColumnProperty(cb[i].getId(),Integer.class.getName())
                            .setTitle(cb[i].getText()).setWidth(ancho)
                            .setPattern("$ 0.00")		//defines a pattern to apply to the values swhown (uses TextFormat)
                    //.setStyle(amountStyle)		//special style for this column (align right)
                            .build();
                        pun++; 
                        
                    }else
                    {
                        nombres[pun]=ColumnBuilder.getNew()
                            .setColumnProperty(cb[i].getId(),String.class.getName())
                            .setTitle(cb[i].getText()).setWidth(ancho)
                            .build();
                        pun++;
                  
                    }
                     // formando el query // parte del select 
                    
                    if(i==4){
                        if(selecCampo.equals("")){
                            selecCampo=" Sum(detalle_ventas.cantidad) AS cantidad ";
                        }else{
                            selecCampo+=", Sum(detalle_ventas.cantidad) AS cantidad ";
                        }
                          
                    }
                    
                    else{
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
               drb.addColumn(nombres[i]);
            }
            Calendar c = Calendar.getInstance();
            String dia = Integer.toString(c.get(Calendar.DATE))+"/";
            dia+= Integer.toString(c.get(Calendar.MONTH)+1)+"/";
            dia+= Integer.toString(c.get(Calendar.YEAR));
            drb
               // .addGroups(2)
                .addImageBanner("./img/umaiSystem.png",70,70,ImageBanner.ALIGN_RIGHT)
                .setTitle("Reporte de Productos más vendidos").setTitleStyle(titleStyle)
                .setSubtitle("Fecha de creacion: " + dia)
                .setDetailHeight(15)
                .setMargins(30, 20, 30, 15)
                .setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true)
                .setColumnsPerPage(1);
            String grup=" receta.nombreRec ";
            
            for (int i = 0; i <grupos.length; i++) {
                if(grupos[i].isSelected()){
                 
                     grup+=","+grupos[i].getId();   
                }
            }
           
            
             String sql;
             sql="SELECT  "+selecCampo +" FROM\n"+
                 "ventas inner join  detalle_ventas  on ventas.idVenta=detalle_Ventas.idVenta inner join receta on detalle_ventas.idRec=receta.idRec \n" +
                 " GROUP BY "+ grup+
                    " order by ventas.idVenta;";
            System.out.println("sql: "+sql);
            Connection conexion = null;
        //conexion=Helpers.db.db.conn;
        conexion=Helpers.db.getDbCon().conn;
        
         drb.setQuery(sql, DJConstants.QUERY_LANGUAGE_SQL);
          dr = drb.build(); //Finally build the report! 
  //       JasperPrint jp=DynamicJasperHelper.generateJasperPrint(dr,new ClassicLayoutManager(),ds);
       JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(),conexion,null);
        
        JasperViewer.viewReport(jp,false);    //finally display the report report
        }else{
            AlertBox.display("Error","Debe seleccionar minimo 1 campo");
        }
    }
    
}
