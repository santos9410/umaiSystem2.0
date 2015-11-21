/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Reportes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 *
 * @author lalo
 */
public class CompiladorReportes {
    String ruta;
    String nombre;
    String sql;
    public CompiladorReportes(String ruta,String nombre,String sql)
    {   
        this.ruta=ruta;
        this.nombre=nombre;
        this.sql=sql;
      
    }
   
    public void correr() {
        InputStream archivo2= null;
        String archivoC=null;
        String rutaPdf=null;
            try {
               
                
                // String archivo = "C:/Users/lalo/Documents/NetBeansProjects/umai/UmaiSystem/src/archivo1.jrxml";
                //   String archivo = "./reportes/archivo2.jrxml";
                JasperReport masterReport= null;
                try {
                    archivoC="./reportes/ventas/"+nombre+".jasper";
                    
                    masterReport= JasperCompileManager.compileReport(ruta);
                    JasperCompileManager.compileReportToFile(
                            ruta,//the path to the jrxml file to compile
                            archivoC);//the path and name we want to save the compiled file
                    System.out.println("Compilado");
                    System.out.println(Thread.currentThread().getName());
                } catch (JRException e) {
                    
                    System.err.println("error de compilacion: "+e.getCause());
                    e.printStackTrace();
                    
                }
                rutaPdf="./reportes/ventas/"+nombre+".pdf";
                ResultSet res = Helpers.db.getDbCon().query(sql);
                JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(res);
                archivo2= new FileInputStream(archivoC);
                JasperReport reporte = (JasperReport) JRLoader.loadObject(archivo2);
                Map param=new HashMap();
                param.put("Titulo","Reporte de ventas ");JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,param, resultSetDataSource); // este es para cuando hace una plantilla desde cero
                //  JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,param,conexion); // este es una plantilla ya predefinida
                Exporter exporter=new JRPdfExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(rutaPdf));
            
            
            exporter.exportReport();
            System.out.println("éxito reporte creado");
            Helpers.AlertBox.display("Éxito","reporte creado con Éxito");
            
            } catch (FileNotFoundException ex) {
               
                Logger.getLogger(CompiladorReportes.class.getName()).log(Level.SEVERE, null, ex);
                
            } catch (JRException ex) {
            Logger.getLogger(CompiladorReportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CompiladorReportes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivo2.close();
            } catch (IOException ex) {
                Logger.getLogger(CompiladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    
}
