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
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.awt.Color;
import java.awt.Transparency;
import java.net.URL;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class ReporteProductosController implements Initializable {
    @FXML
    private Label lblTabla;
    @FXML
    private VBox contenedorCampos;
    @FXML
    private VBox contenedorGrupos;
    @FXML
    private Button btnGenerar;
    CheckBox cb[];
     CheckBox group[];
     String camposQuery[];
    @FXML
    private Button btnRegresar;
    @FXML
    private VBox contenedorFecha1;
    @FXML
    private VBox contenedorFecha2;
       DatePicker checkInDatePicker;
     DatePicker checkInDatePicker2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         String []campos =new String[]{"detalle_ventas.idVenta","receta.nombreRec","ventas.fechaVenta","ventas.Num_mesa"
         ,"detalle_ventas.cantidad","detalle_ventas.precioProd","ventas.total"};
      
         String[] nombresCampos=new String[]{"id Venta","Nombre Producto","fecha Venta","Mesa"
         ,"cantidad Productos","precio Producto","total venta"};
        
        camposQuery=new String[]{" detalle_ventas.`idVenta` AS 'detalle_ventas.idVenta' "," receta.`nombreRec` AS 'receta.nombreRec' "
        ," STR_TO_DATE(ventas.fechaVenta,'%Y-%m-%d') AS 'ventas.fechaVenta' "," ventas.`Num_mesa` AS 'ventas.Num_mesa' "
        ," detalle_ventas.`cantidad` AS 'detalle_ventas.cantidad' "," detalle_ventas.`precioProd` AS 'detalle_ventas.precioProd' "
        ,"ventas.`total` AS 'ventas.total'"};
                
             
        
        lblTabla.setText("Ventas detalladas");
          cb=new CheckBox[campos.length];
         group=new CheckBox[campos.length];
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
            
            contenedorCampos.setMargin(cb[i],new Insets(10,0,10,50));
            contenedorCampos.getChildren().add(cb[i]);
           group[i]=new CheckBox();
           group[i].setText(nombresCampos[i]);
           group[i].setId(campos[i]);
                group[i].setStyle(
                    "-fx-border-color: lightblue; "
                            + "-fx-font-size: 16;"
                            + "-fx-border-insets: -2;"
                            + "-fx-border-radius: 2;"
                            + "-fx-border-style: dotted;"
                            + "-fx-border-width: 1;"          
            );      
            contenedorGrupos.setMargin(group[i],new Insets(10,0,10,50));
            contenedorGrupos.getChildren().add(group[i]);
            }
         checkInDatePicker = new DatePicker();
            
         GridPane gridPane = new GridPane();
         gridPane.setHgap(10);
         gridPane.setVgap(10);
         
         Label checkInlabel = new Label("DESDE:");
         gridPane.add(checkInlabel, 0, 0);
         
         checkInDatePicker.setValue(LocalDate.now());
         
         GridPane.setHalignment(checkInlabel, HPos.LEFT);
         gridPane.add(checkInDatePicker, 0, 1);
         contenedorFecha1.getChildren().add(gridPane);
          //////////////////////////////
         
         checkInDatePicker2 = new DatePicker();
            
         GridPane gridPane2 = new GridPane();
         gridPane2.setHgap(10);
         gridPane2.setVgap(10);
         
         Label checkInlabel2 = new Label("HASTA:");
         gridPane2.add(checkInlabel2, 0, 0);
         
         checkInDatePicker2.setValue(LocalDate.now());
         
         GridPane.setHalignment(checkInlabel2, HPos.LEFT);
         gridPane2.add(checkInDatePicker2, 0, 1);
         
         //vbox.getChildren().add(gridPane);        
        contenedorFecha2.getChildren().add(gridPane2);
    }    

    @FXML
    private void eventGenerarPDf(ActionEvent event) throws ColumnBuilderException, ClassNotFoundException, JRException, ParseException {
        Style titleStyle = new Style();
 		titleStyle.setFont(new Font(18,Font._FONT_VERDANA,true));
  		Style amountStyle = new Style();
  		amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
  		Style oddRowStyle = new Style();
  		oddRowStyle.setBorder(Border.NO_BORDER());
  		Color veryLightGrey = new Color(230,230,230);
  		oddRowStyle.setBackgroundColor(veryLightGrey);oddRowStyle.setTransparency(ar.com.fdvs.dj.domain.constants.Transparency.OPAQUE);
        String fecha1=checkInDatePicker.getValue().toString();
             String fecha2=checkInDatePicker2.getValue().toString();
            
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd"); 
        Date fechaDate1 = formateador.parse(fecha1);
        Date fechaDate2 = formateador.parse(fecha2);
        
        
        int cont = 0;
        int cont3=0;
        String selecCampo="";
        for (int i = 0; i <cb.length; i++) {
              if(cb[i].isSelected()){
                  cont++;
              }
              
        }
        for (int i = 0; i <group.length; i++) {
            if(group[i].isSelected()){
                cont3++;
            }
        }
                
        int ancho=580/(cont+1);
        ArrayList<AbstractColumn>colum=new ArrayList<>();
        AbstractColumn  nombres[];
        int pun=0;
        if(cont!=0)
        {
            if(fechaDate1.before(fechaDate2)|| fechaDate1.equals(fechaDate2)){
            nombres=new AbstractColumn[cont];
           
            for (int i = 0; i <cb.length; i++) 
            {
                if(cb[i].isSelected())
                {
                    System.out.println(cb[i].getId());
                 
                    if(i==5 || i==6)
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
                    
                    if(i==4 && cont3!=0){
                        
                        if(selecCampo.equals("")){
                            selecCampo=" sum(detalle_ventas.`cantidad`) AS 'detalle_ventas.cantidad' ";
                        }
                        else{
                            selecCampo+=","+" sum(detalle_ventas.`cantidad`) AS 'detalle_ventas.cantidad' ";
                        }
                        
                    }
                    else if(i==6 && cont3!=0){
                        //( sum(detalle_ventas.`cantidad`)*detalle_ventas.`precioProd`) AS ventas_total
                        if(selecCampo.equals("")){
                            selecCampo=" ( sum(detalle_ventas.`cantidad`)*detalle_ventas.`precioProd`) AS 'ventas.total' ";
                        }
                        else{
                            selecCampo+=","+"( sum(detalle_ventas.`cantidad`)*detalle_ventas.`precioProd`) AS 'ventas.total' ";
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
            
            
        //FastReportBuilder drb = new FastReportBuilder();
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
                .setTitle("Reporte de ventas detalladas").setTitleStyle(titleStyle)
                .setSubtitle("Fecha de creacion: " + dia)
                .setDetailHeight(15)
                .setMargins(30, 20, 30, 15)
                .setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true)
                .setColumnsPerPage(1);
        String grupos="";
        int cont2=0;
            for (int i = 0; i <group.length; i++) {
                if(group[i].isSelected()){
                 if(grupos.equals("")){
                    grupos=group[i].getId();
                 }else{
                     grupos+=","+group[i].getId();
                 }
                 
                 cont2++;
                }
            }
            System.out.println("grupos: "+grupos);
        String Rango=" where ventas.fechaVenta Between '"+fecha1+"' and '"+fecha2+"'  ";
        String sql;
        
            if(cont2!=0){
            
        
         sql=" select " +selecCampo+" FROM\n" +
    "     `receta` receta INNER JOIN `detalle_ventas` detalle_ventas ON receta.`idRec` = detalle_ventas.`idRec`\n" +
    "     INNER JOIN `ventas` ventas ON detalle_ventas.`idVenta` = ventas.`idVenta`\n" +Rango
                 + "group by "+grupos+
        "    order by ventas.idVenta ";
        }
            else{
                sql=" select " +selecCampo+" FROM\n" +
        "     `receta` receta INNER JOIN `detalle_ventas` detalle_ventas ON receta.`idRec` = detalle_ventas.`idRec`\n" +
        "     INNER JOIN `ventas` ventas ON detalle_ventas.`idVenta` = ventas.`idVenta`\n" + Rango
        +"    order by ventas.idVenta ";
            }
            System.out.println(sql);
        Connection conexion = null;
        //conexion=Helpers.db.db.conn;
        conexion=Helpers.db.getDbCon().conn;
        
         drb.setQuery(sql, DJConstants.QUERY_LANGUAGE_SQL);
          dr = drb.build(); //Finally build the report! 
  //       JasperPrint jp=DynamicJasperHelper.generateJasperPrint(dr,new ClassicLayoutManager(),ds);
       JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(),conexion,null);
        
        JasperViewer.viewReport(jp,false);    //finally display the report report
         System.out.println(sql);
        }
        else{
            AlertBox.display("Error","Rango de fechas no valido");
        }
        }
        else{
            AlertBox.display("Error","Debe seleccionar minimo 1 campo");
        }
        
        
    }

    @FXML
    private void eventRegresar(ActionEvent event) {
          Controllers.ControllerStage cs=new Controllers.ControllerStage();
            cs.openNewWindow("/Views/Reportes/SeleccionarReporte.fxml");
        
    }
    
}
