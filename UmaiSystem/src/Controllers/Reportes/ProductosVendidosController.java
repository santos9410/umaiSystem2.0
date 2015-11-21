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
import ar.com.fdvs.dj.domain.chart.DJChartOptions;
import ar.com.fdvs.dj.domain.chart.builder.DJPieChartBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import java.awt.Color;
import java.net.URL;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
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
public class ProductosVendidosController implements Initializable {
    @FXML
    private VBox ContenedorCampos;
   
    @FXML
    private Button btnGenerar;
    CheckBox cb[];
    
    String camposQuery[];
    @FXML
    private Label lblTabla;
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
         String []campos =new String[]{"Nombre_Producto","Tipo_de_producto","Fecha_de_Venta","Precio_Venta","cantidad","totalVenta"};
        
         String[] nombresCampos=new String[]{"Nombre Producto","Tipo de producto","Fecha de Venta","Precio Venta","cantidad","total Venta"};
        
        camposQuery=new String[]{" receta.nombreRec AS Nombre_Producto "," receta.tipoRec AS Tipo_de_producto "
            ," STR_TO_DATE(ventas.fechaVenta,'%Y-%m-%d') AS Fecha_de_Venta "," receta.precio_venta AS Precio_Venta "
            ,"detalle_ventas.cantidad AS cantidad "," (receta.precio_venta*Sum(detalle_ventas.cantidad)) AS totalVenta "};
                
             
        
        lblTabla.setText("Productos más vendidos");
          cb=new CheckBox[campos.length];
        /*
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
        
            }
                */
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
    private void eventGenerar(ActionEvent event) throws JRException, ParseException {
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
        
                
                int cont=0;
          String selecCampo="";
          /*
        for (int i = 0; i <cb.length; i++) {
            if(cb[i].isSelected()){
                  cont++;
            }
              
        }
                  */
         int ancho=580/(2);
        
        //AbstractColumn  nombres[];
        int pun=0;
        
            if(fechaDate1.before(fechaDate2)|| fechaDate1.equals(fechaDate2)){
          //  nombres=new AbstractColumn[cont];
           //Nombre_Producto
             AbstractColumn nombres=ColumnBuilder.getNew()
                            .setColumnProperty("Nombre_Producto",String.class.getName())
                            .setTitle("Nombre del producto").setWidth(ancho)
                           // .setPattern("$ 0.00")		//defines a pattern to apply to the values swhown (uses TextFormat)
                    //.setStyle(amountStyle)		//special style for this column (align right)
                            .build();
             AbstractColumn cantidad=ColumnBuilder.getNew()
                            .setColumnProperty("cantidad",Integer.class.getName())
                            .setTitle("Cantidad de Productos").setWidth(ancho)
                         //  .setPattern("$ 0.00")		//defines a pattern to apply to the values swhown (uses TextFormat)
                    //.setStyle(amountStyle)		//special style for this column (align right)
                            .build();
            
            
            DynamicReportBuilder drb = new DynamicReportBuilder();
            DynamicReport dr = null;
           drb.addColumn(nombres);
           drb.addColumn(cantidad);
            
            
            Calendar c = Calendar.getInstance();
            String dia = Integer.toString(c.get(Calendar.DATE))+"/";
            dia+= Integer.toString(c.get(Calendar.MONTH)+1)+"/";
            dia+= Integer.toString(c.get(Calendar.YEAR));
            drb
               // .addGroups(2)
                .addImageBanner("./img/umaiSystem.png",70,70,ImageBanner.ALIGN_RIGHT)
                .setTitle("Reporte de Productos más vendidos ").setTitleStyle(titleStyle)
                .setSubtitle("Fecha de creacion: " + dia)
                
                .setDetailHeight(15)
                .setMargins(30, 20, 30, 15)
                .setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true)
                .setColumnsPerPage(1);
           
          
            
            
            String grup=" receta.nombreRec ";
            
            String Rango=" where ventas.fechaVenta Between '"+fecha1+"' and '"+fecha2+"'  ";
           
            
             String sql;
             sql="SELECT receta.nombreRec AS Nombre_Producto , sum(detalle_ventas.cantidad) as cantidad "+" FROM\n"+
                 "ventas inner join  detalle_ventas  on ventas.idVenta=detalle_Ventas.idVenta inner join receta on detalle_ventas.idRec=receta.idRec \n" 
                     +Rango+
                     " GROUP BY "+ grup+
                    " order by sum(detalle_ventas.cantidad) desc ;";
            System.out.println("sql: "+sql);
            /*
            
            */
            
                ar.com.fdvs.dj.domain.chart.DJChart djChart = new DJPieChartBuilder()
 		//chart		
 		.setX(20)
 		.setY(10)
 		.setWidth(500)
 		.setHeight(250)
 		.setCentered(false)
 		.setBackColor(Color.LIGHT_GRAY)
 		.setShowLegend(true)
 		.setPosition(DJChartOptions.POSITION_FOOTER)
 		.setTitle("Productos más Vendidos")
 		.setTitleColor(Color.DARK_GRAY)
 		.setTitleFont(Font.ARIAL_BIG_BOLD)
                 
 		.setSubtitle("Desde "+fecha1+" Hasta "+fecha2)
 		.setSubtitleColor(Color.DARK_GRAY)
 		.setSubtitleFont(Font.COURIER_NEW_BIG_BOLD)
 		.setLegendColor(Color.DARK_GRAY)
 		.setLegendFont(Font.COURIER_NEW_MEDIUM_BOLD)
 		.setLegendBackgroundColor(Color.WHITE)
 		.setLegendPosition(DJChartOptions.EDGE_BOTTOM)
 		.setTitlePosition(DJChartOptions.EDGE_TOP)
 		.setLineStyle(DJChartOptions.LINE_STYLE_DOTTED)
 		.setLineWidth(2)
 		.setLineColor(Color.DARK_GRAY)
                
 		.setPadding(6)
 		//dataset
 		//.setKey((PropertyColumn))
                //.setKey((PropertyColumn)cantidad)
                //.addSerie(cantidad)
                //.addSerie(cantidad)
                .setKey((PropertyColumn) nombres) // es string
                //.setKey((PropertyColumn)cantidad)
                .addSerie(cantidad)
                  //.series(djChart.serie(unitPriceColumn))
                        
 		//plot
 		.setCircular(true)
 		.build();
 		drb.addChart(djChart);
                
            /*
            
            */
            Connection conexion = null;
        //conexion=Helpers.db.db.conn;
        conexion=Helpers.db.getDbCon().conn;
        
         drb.setQuery(sql, DJConstants.QUERY_LANGUAGE_SQL);
          dr = drb.build(); //Finally build the report! 
  //       JasperPrint jp=DynamicJasperHelper.generateJasperPrint(dr,new ClassicLayoutManager(),ds);
       JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(),conexion,null);
        
        JasperViewer.viewReport(jp,false);    //finally display the report report
        }else{
            AlertBox.display("Error","El rango de fechas no es valido");
        }
        
    }

    @FXML
    private void eventRegresar(ActionEvent event) {
          Controllers.ControllerStage cs=new Controllers.ControllerStage();
            cs.openNewWindow("/Views/Reportes/SeleccionarReporte.fxml");
        
    }
    
}
