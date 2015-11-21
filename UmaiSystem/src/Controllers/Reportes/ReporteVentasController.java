/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Reportes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.geometry.Insets;

/**
 * FXML Controller class
 *
 * @author lalo
 */
public class ReporteVentasController implements Initializable {
    @FXML
    private VBox contenedorCampos;
    @FXML
    private Button btnGenerar;
    @FXML
    private Label lblTabla;
     CheckBox cb[];
     CheckBox group[];
      ArrayList<String> fields=new ArrayList<>();
    @FXML
    private VBox contenedorAgrupar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        String []campos =new String[]{"idVenta","nombreUsu","nombreCli","fechaVenta","descuento","total","Num_mesa"};
        String[] nombresCampos=new String[]{"id Venta","nombre Usuario","nombre Cliente","fecha Venta","descuento","total","mesa"};
        
        lblTabla.setText("Tabla Ventas");
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
            
            contenedorCampos.setMargin(cb[i],new Insets(10,0,10,100));
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
            contenedorAgrupar.setMargin(group[i],new Insets(10,0,10,100));
            contenedorAgrupar.getChildren().add(group[i]);
            }
            loadXml1();
        
    }    

    @FXML
    public void eventReporteVentas(ActionEvent event) throws IOException {
        
       int cont=0;
       int ban=0;
       // String check="";
        ArrayList<String>check=new ArrayList<>();
        ArrayList<String>selec=new ArrayList<>();
        selec.clear();
        System.out.println("Campos seleccionados:");
        for (int i = 0; i <cb.length; i++) {
            if(cb[i].isSelected()){
                System.out.println(cb[i].getId());
            }
        }
        
        if(cb[0].isSelected()){
                fields.add("<field name=\"ventas_idVenta\" class=\"java.lang.String\"/>");
                cont++;
               // check+="ventas.`idVenta` AS ventas_idVenta";  //generando los campos
                if(check.isEmpty()){
                check.add("ventas.`idVenta` AS ventas_idVenta");
                }
                else{
                    check.add(",ventas.`idVenta` AS ventas_idVenta");
                }
                selec.add("ventas_idVenta");
        }
        if(cb[1].isSelected()){
             fields.add("<field name=\"usuarios_nombreUsu\" class=\"java.lang.String\"/>");
             selec.add("usuarios_nombreUsu");
                cont++;
               
                    //check+="usuarios.`nombreUsu` AS usuarios_nombreUsu";
                 if(check.isEmpty()){
                    check.add("usuarios.`nombreUsu` AS usuarios_nombreUsu");
                 }else{
                     check.add(",usuarios.`nombreUsu` AS usuarios_nombreUsu");
                 }
                 
        }
        if(cb[2].isSelected()){
             fields.add("<field name=\"clientes_nombreCli\" class=\"java.lang.String\"/>");
             selec.add("clientes_nombreCli");
                cont++;
                
                  //  check+="clientes.`nombreCli` AS clientes_nombreCli";
                  if(check.isEmpty()){
                    check.add("clientes.`nombreCli` AS clientes_nombreCli");
               
                  }else{
                      check.add(",clientes.`nombreCli` AS clientes_nombreCli");
                  }
                // clientes.`nombreCli` AS clientes_nombreCli,
        }
        if(cb[3].isSelected()){
             fields.add("<field name=\"ventas_fechaVenta\" class=\"java.lang.String\"/>");
             selec.add("ventas_fechaVenta");
                cont++;
                
                   // check+="STR_TO_DATE(ventas.fechaVenta,'%d-%m-%Y') AS ventas_fechaVenta";
                  if(check.isEmpty()){
                    check.add("STR_TO_DATE(ventas.fechaVenta,'%d-%m-%Y') AS ventas_fechaVenta");
                  }
                  else{
                       check.add(",STR_TO_DATE(ventas.fechaVenta,'%d-%m-%Y') AS ventas_fechaVenta");
                  }
               // STR_TO_DATE(ventas.fechaVenta,'%d-%m-%Y') AS ventas_fechaVenta,
        }
        if(cb[4].isSelected()){
             fields.add("<field name=\"ventas_descuento\" class=\"java.lang.String\"/>");
             selec.add("ventas_descuento");
                cont++;
               if(check.isEmpty()){
                    check.add("ventas.`descuento` AS ventas_descuento");
               }
               else{
                    check.add(",ventas.`descuento` AS ventas_descuento");
               }
                      
              //ventas.`descuento` AS ventas_descuento,
        }
         if(cb[5].isSelected()){
             fields.add("<field name=\"ventas_total\" class=\"java.lang.String\"/>");
             selec.add("ventas_total");
             ban=cont;   
             cont++;
             
                if(check.isEmpty()){
                check.add("ventas.`total` AS ventas_total");
                }else{
                     check.add(",ventas.`total` AS ventas_total");
                }
              //ventas.`total` AS ventas_total,
        }
        if(cb[6].isSelected()){
             fields.add("<field name=\"ventas_Num_mesa\" class=\"java.lang.String\"/>");
             selec.add("ventas_Num_mesa");
                cont++;
                  if(check.isEmpty()){
                check.add("ventas.`Num_mesa` AS ventas_Num_mesa");
                  }else{
                      check.add(",ventas.`Num_mesa` AS ventas_Num_mesa");
                  }
              // ventas.`Num_mesa` AS ventas_Num_mesa
        }
        
        System.out.println("Compruebo cauntos estan seleccionados: "+check);
        System.out.println("Compruebo cauntos tiene el list:: "+selec.size());
        for (int i = 0; i <selec.size(); i++) {
            System.out.println(selec.get(i)+" ,"+i);
        }
        
        //***********
        if(cont==0){
            Helpers.AlertBox.display("Error","Debe selecionar minimo un campo");
        }
        else{
            loadXml2();
            int i=20;
            /*
            ** se pone un nombre a cada campo de la columna header
            */
            int ancho=580/(cont+1);
            int p=0;
            for (int j = 0; j<cb.length; j++) {
                
                if(cb[j].isSelected()){
          
                        fields.add("<staticText>\n" +
"				<reportElement x=\""+i + "\" y=\"1\" width=\"80\" height=\"20\" uuid=\"42f3d6b2-739c-4f66-9197-6832fd4f9177\"/>\n" +
"				<text><![CDATA["+cb[j].getText()+"]]></text>\n" +
"                               </staticText>");
                        i+=ancho;
                        p++;
                }
            }
            /*
            *******************
            */
            loadXml3();
            /*
            ** se ponen los campos donde reciben los datos de la columna details
            */
            int j=20;
            int l=0;
            for (int k = 0; k <cb.length; k++) {
                
            
                if(cb[k].isSelected() ){
                   if(k==5){
                       fields.add("			<textField>\n" +
"                                   <reportElement x=\""+j+"\" y=\"2\" width=\"80\" height=\"20\" uuid=\"a05c33b6-f41d-4b2c-8585-d1c578197d01\"/>\n" +
"                                   <textFieldExpression><![CDATA[$F{"+selec.get(l)+"}]]></textFieldExpression>\n" +
"                           </textField>");
                       
                        int tem=j+(ancho/3);
                         j+=ancho;
                        fields.add("<staticText>\n" +
"				<reportElement x=\""+tem + "\" y=\"1\" width=\"10\" height=\"20\" uuid=\"42f3d6b2-739c-4f66-9197-6832fd4f9177\"/>\n" +
"				<text><![CDATA[$]]></text>\n" +
"                               </staticText>");
                   }else
                   {
                        fields.add("			<textField>\n" +
"                                   <reportElement x=\""+j+"\" y=\"2\" width=\"80\" height=\"20\" uuid=\"a05c33b6-f41d-4b2c-8585-d1c578197d01\"/>\n" +
"                                   <textFieldExpression><![CDATA[$F{"+selec.get(l)+"}]]></textFieldExpression>\n" +
"                           </textField>");
                       j+=ancho;
                   }
                   
                   
                    l++;
                    
                }
            }
            loadXml4();
            ArrayList<String> grupo=new ArrayList<>();
            if(group[0].isSelected()){
                
                grupo.add("ventas.idVenta");
               //grupo+=" ventas.idVenta";
                
            }
             if(group[1].isSelected()){
               //grupo+=" ventas.idVenta";
                 if(grupo.isEmpty()){
               grupo.add("usuarios.nombreUsu");
                 }else{
                      grupo.add(",usuarios.nombreUsu");
                 }
            }
             if(group[2].isSelected()){
               //grupo+=" ventas.idVenta";
                  if(grupo.isEmpty()){
               grupo.add("clientes.nombreCli");
                  }else{
                        grupo.add(",clientes.nombreCli");
                  }
                          
               
            }
             if(group[3].isSelected()){
               //grupo+=" ventas.idVenta";
                  if(grupo.isEmpty()){
               grupo.add("ventas.fechaVenta");
                  }else{
                        grupo.add(",ventas.fechaVenta");
                  }
            }
            
            if(group[4].isSelected()){
                 if(grupo.isEmpty()){
                grupo.add("ventas.descuento");
                 }else{
                      grupo.add(",ventas.descuento");
                 }
            }
            if(group[5].isSelected()){
                        if(grupo.isEmpty()){
                grupo.add("ventas.total");
                        }else{
                              grupo.add(",ventas.total");
                        }
                
            }
            if(group[6].isSelected()){
               //grupo+=" ventas.idVenta";
                   if(grupo.isEmpty()){
               grupo.add("ventas.Num_mesa");
                   }else{
                         grupo.add(",ventas.Num_mesa");
                   }
            }
            
            
            if(!grupo.isEmpty()){
                if(cb[5].isSelected()){
                    if(check.size()==1){
                    check.set(ban,"sum(ventas.`total`) AS ventas_total ");
                    }
                    else{
                         check.set(ban,",sum(ventas.`total`) AS ventas_total ");
                    }
                }
            }
            String camp="";
            
            for (int k = 0; k <check.size(); k++) {
              camp+=check.get(k);
            }
             String gru="";       
           if(!grupo.isEmpty()){
              gru+=" group by ";
           }
             for (int k = 0; k <grupo.size(); k++) {
                gru+=grupo.get(k);
                
            }
             gru+=" order by ventas.idVenta; ";
        String sql="Select "+camp+" FROM\n" +
"     `ventas` ventas INNER JOIN `detalle_ventas` detalle_ventas ON ventas.`idVenta` = detalle_ventas.`idVenta`\n" +
"     INNER JOIN `clientes` clientes ON ventas.`idCli` = clientes.`idCli`\n" +
"     INNER JOIN `usuarios` usuarios ON ventas.`idUsu` = usuarios.`idUsu`\n" +
"     INNER JOIN `receta` receta ON detalle_ventas.`idRec` = receta.`idRec`" +gru;
        grupo.clear();
        gru="";
        camp="";
        check.clear();
        
        
            System.out.println(sql);
            String nombre;
            String mensaje="Ingresa un nombre para el reporte";
            nombre=Helpers.InputBox.display("input",mensaje);
            if(!nombre.isEmpty()){
                    hacerPlantilla(nombre,sql);
                    
                
            }
            
        }
        
    }

    private void loadXml1() {
        fields.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"report name\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"535\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"1f7a20fb-943c-48e0-8cf7-8a865131ab9b\">\n" +
"	<property name=\"ireport.zoom\" value=\"1.5\"/>\n" +
"	<property name=\"ireport.x\" value=\"0\"/>\n" +
"	<property name=\"ireport.y\" value=\"0\"/>\n" +
"	<style name=\"Column header\" forecolor=\"#666666\" fontName=\"Arial\" fontSize=\"12\" isBold=\"true\"/>\n" +
"	<parameter name=\"Titulo\" class=\"java.lang.String\"/>\n");
    }
    
    private void loadXml2(){
        fields.add("<background>\n" +
"		<band splitType=\"Stretch\"/>\n" +
"	</background>\n" +
"	<title>\n" +
"		<band height=\"71\" splitType=\"Stretch\">\n" +
"			<textField>\n" +
"				<reportElement x=\"11\" y=\"19\" width=\"220\" height=\"32\" uuid=\"c73268ad-35b6-46d1-9547-530e9e3c6c91\"/>\n" +
"				<textElement>\n" +
"					<font fontName=\"Arial\" size=\"16\"/>\n" +
"				</textElement>\n" +
"				<textFieldExpression><![CDATA[$P{Titulo}]]></textFieldExpression>\n" +
"			</textField>\n" +
"			<image>\n" +
"				<reportElement x=\"433\" y=\"0\" width=\"70\" height=\"70\" uuid=\"76510011-bfc5-4c04-8aac-03468fc1cb50\"/>\n" +
"				<imageExpression><![CDATA[\"C:\\\\Users\\\\lalo\\\\Documents\\\\NetBeansProjects\\\\umai\\\\UmaiSystem\\\\img\\\\UmaiSystem.png\"]]></imageExpression>\n" +
"			</image>\n" +
"		</band>\n" +
"	</title>");
        fields.add("	<columnHeader>\n" +
"		<band height=\"31\" splitType=\"Stretch\">\n" +
"			<line>\n" +
"				<reportElement x=\"0\" y=\"27\" width=\"555\" height=\"1\" uuid=\"cd8683a6-074d-4578-9e7e-2b99647f8f7f\"/>\n" +
"			</line>\n");
    }
    
    private void loadXml3(){
         fields.add("		</band>\n" +
"	</columnHeader>\n" +
"	<detail>\n" +
"		<band height=\"27\" splitType=\"Stretch\">\n" +
"			<line>\n" +
"				<reportElement x=\"0\" y=\"21\" width=\"555\" height=\"1\" uuid=\"1a20fa04-0535-4ed5-ae3f-3b53658956e9\"/>\n" +
"			</line>\n");
    }
    
    private void loadXml4(){
        fields.add("		</band>\n" +
"	</detail>\n" +
"	<columnFooter>\n" +
"		<band height=\"16\" splitType=\"Stretch\"/>\n" +
"	</columnFooter>\n" +
"	<pageFooter>\n" +
"		<band height=\"21\" splitType=\"Stretch\">\n" +
"			<textField>\n" +
"				<reportElement style=\"Column header\" x=\"433\" y=\"0\" width=\"80\" height=\"20\" forecolor=\"#666666\" uuid=\"70d59fa6-c1d3-4c22-9c33-3ee5a820ea7c\"/>\n" +
"				<textElement>\n" +
"					<font fontName=\"Arial\" size=\"10\" isBold=\"false\"/>\n" +
"				</textElement>\n" +
"				<textFieldExpression><![CDATA[\"Page \"+$V{PAGE_NUMBER}+\" of\"]]></textFieldExpression>\n" +
"			</textField>\n" +
"			<textField>\n" +
"				<reportElement style=\"Column header\" x=\"513\" y=\"0\" width=\"42\" height=\"20\" forecolor=\"#666666\" uuid=\"b4b65a9c-9cba-45f8-b18e-0a30f1871128\"/>\n" +
"				<textElement>\n" +
"					<font fontName=\"Arial\" size=\"10\" isBold=\"false\"/>\n" +
"				</textElement>\n" +
"				<textFieldExpression><![CDATA[\" \" + $V{PAGE_NUMBER}]]></textFieldExpression>\n" +
"			</textField>\n" +
"			<textField>\n" +
"				<reportElement x=\"0\" y=\"1\" width=\"197\" height=\"20\" uuid=\"d461d965-02f6-413e-93ba-517fd047a005\"/>\n" +
"				<textFieldExpression><![CDATA[new java.util.Date()]]></textFieldExpression>\n" +
"			</textField>\n" +
"		</band>\n" +
"	</pageFooter>\n" +
"</jasperReport>");
    }
    
    private  void plantilla(String ruta) throws IOException{
       
        
        //String ruta = "src/Views/Reportes/ventas/archivo1.jrxml";
        //String ruta = "src/archivo1.jrxml";
       
        File archivo = new File(ruta);
        BufferedWriter bw;
        
            bw = new BufferedWriter(new FileWriter(archivo));
            
            for (int i = 0; i <fields.size(); i++) {
                bw.write(fields.get(i)+"\r");
        }
        
        bw.close();
        
        
    }
    
    private void hacerPlantilla(String nombre,String sql) throws IOException{
      
            //String ruta = null;
             
             File folder = new File("./reportes/ventas");
        if (!folder.exists()) {
            folder.mkdirs();
           
        }
        String ruta = "./reportes/ventas/"+nombre+".jrxml";
        
        plantilla(ruta);
            new CompiladorReportes(ruta,nombre,sql).correr();
             Controllers.ControllerStage cs=new Controllers.ControllerStage();
            cs.openNewWindow("/Views/Reportes/SeleccionarReporte.fxml");
          
            
    }
}
