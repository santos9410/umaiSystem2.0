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
public class ReporteVentas2Controller implements Initializable {
    @FXML
    private Label lblTabla;
    @FXML
    private VBox contenedorCampos;
    @FXML
    private Button btnGenerar;
    @FXML
    private VBox contenedorAgrupar;
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
     String []campos =new String[]{"ventas.idVenta","usuarios.nombreUsu","clientes.nombreCli","ventas.fechaVenta"
         ,"ventas.descuento","ventas.Num_mesa", "ventas.total"};
     
     String[] nombresCampos=new String[]{"id Venta","nombre Usuario","nombre Cliente","fecha Venta","descuento","Mesa","Total"};
        
        
        camposQuery=new String[]{" ventas.`idVenta` AS 'ventas.idVenta' "," usuarios.`nombreUsu` AS 'usuarios.nombreUsu' "
        ," clientes.`nombreCli` AS 'clientes.nombreCli' "," STR_TO_DATE(ventas.fechaVenta,'%Y-%m-%d') AS 'ventas.fechaVenta' "
        ," ventas.`descuento` AS 'ventas.descuento' "," ventas.`Num_mesa` AS 'ventas.Num_mesa' "," ventas.`total` AS 'ventas.total' "
        };
                
             
        
        lblTabla.setText("Ventas ");
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
            contenedorAgrupar.setMargin(group[i],new Insets(10,0,10,50));
            contenedorAgrupar.getChildren().add(group[i]);
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
    private void eventReporteVentas(ActionEvent event) throws JRException, ParseException {
        
        
                Style titleStyle = new Style();
 		titleStyle.setFont(new Font(18,Font._FONT_VERDANA,true));
                titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
  		Style amountStyle = new Style();
  		amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
  		Style oddRowStyle = new Style();
  		oddRowStyle.setBorder(Border.NO_BORDER());
  		//Color veryLightGrey = new Color(230,230,230);
  		//oddRowStyle.setBackgroundColor(veryLightGrey);
                oddRowStyle.setTextColor(new Color(112,112,112));
                oddRowStyle.setFont(new Font(12,Font._FONT_ARIAL,true));
                oddRowStyle.setTransparency(ar.com.fdvs.dj.domain.constants.Transparency.OPAQUE);
                oddRowStyle.setPaddingTop(2);
                oddRowStyle.setPaddingBottom(2);
               // Style HeaderStyle = new Style();
 		//HeaderStyle.setFont(new Font(12,Font._FONT_ARIAL,true));
  		
        
        
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
        
        String fecha1=checkInDatePicker.getValue().toString();
             String fecha2=checkInDatePicker2.getValue().toString();
            
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd"); 
        Date fechaDate1 = formateador.parse(fecha1);
        Date fechaDate2 = formateador.parse(fecha2);
        System.out.println("fecha1: "+fecha1);
        System.out.println("Fecha2: "+fecha2);
   
            
    
    if(cont!=0){
        if(fechaDate1.before(fechaDate2)|| fechaDate1.equals(fechaDate2)){
            nombres=new AbstractColumn[cont];
           
            for (int i = 0; i <cb.length; i++) 
            {
                if(cb[i].isSelected())
                {
                    if(i==4 || i==6)
                    {
                        nombres[pun]=ColumnBuilder.getNew()
                            .setColumnProperty(cb[i].getId(),Integer.class.getName())
                            .setTitle(cb[i].getText()).setWidth(ancho)
                            .setPattern("$ 0.00")		//defines a pattern to apply to the values swhown (uses TextFormat)
                    //.setStyle(amountStyle)		//special style for this column (align right)
                        .setHeaderStyle(oddRowStyle)
                            .build();
                        pun++; 
                        
                    }else
                    {
                        nombres[pun]=ColumnBuilder.getNew()
                            .setColumnProperty(cb[i].getId(),String.class.getName())
                            .setTitle(cb[i].getText()).setWidth(ancho)
                            .setHeaderStyle(oddRowStyle)
                            .build();
                        pun++;
                  
                    }
                    if(cont3!=0 && i==6){
                        if(selecCampo.equals("")){
                            selecCampo=" sum(ventas.`total`) AS 'ventas.total' ";
                        }else{
                            selecCampo+=" , sum(ventas.`total`) AS 'ventas.total' ";
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
                .setTitle("Reporte de ventas ").setTitleStyle(titleStyle)
                .setSubtitle("Fecha de creacion: " + dia)
                .setDetailHeight(15)
                .setMargins(30, 20, 30, 15)
                .setPrintBackgroundOnOddRows(false)
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
            String Rango=" where ventas.fechaVenta Between '"+fecha1+"' and '"+fecha2+"'  ";
              
             String sql;
             if(cont2!=0){
             sql=" SELECT "+ selecCampo+" FROM\n"
            +"  `ventas` ventas  INNER JOIN `clientes` ON clientes.`idCli` = ventas.`idCli`\n" +
"     INNER JOIN `usuarios` usuarios ON ventas.`idUsu` = usuarios.`idUsu` " +Rango
                     +" GROUP BY "+grupos +" ORDER BY ventas.idVenta ";
             }
             else{
                   sql=" SELECT "+ selecCampo+" FROM\n"
                    +"  `ventas` ventas  INNER JOIN `clientes` ON clientes.`idCli` = ventas.`idCli`\n" 
                    +"  INNER JOIN `usuarios` usuarios ON ventas.`idUsu` = usuarios.`idUsu` " + Rango
                           +" ORDER BY ventas.idVenta ";
             }
             System.out.println("sql: "+sql);
             
              Connection conexion = null;
        //conexion=Helpers.db.db.conn;
        conexion=Helpers.db.getDbCon().conn;
        
         drb.setQuery(sql, DJConstants.QUERY_LANGUAGE_SQL);
          dr = drb.build(); //Finally build the report! 
  //       JasperPrint jp=DynamicJasperHelper.generateJasperPrint(dr,new ClassicLayoutManager(),ds);
       JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(),conexion,null);
        
        JasperViewer.viewReport(jp,false);    //finally display the report report
            System.out.println(sql);
        }else{
            AlertBox.display("Error","Rango de fechas no valido");
        }
    } else{
        AlertBox.display("Error","Debe seleccionar minimo 1 campo");
    }
    }

    @FXML
    private void eventRegresar(ActionEvent event) {
          Controllers.ControllerStage cs=new Controllers.ControllerStage();
            cs.openNewWindow("/Views/Reportes/SeleccionarReporte.fxml");
        
    }
    
}
