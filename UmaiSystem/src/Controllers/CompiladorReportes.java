package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author lalo
 */
public class CompiladorReportes{

  
    public void compilar() {
        String archivo = "src/archivo1.jrxml";
                
                //JasperReport masterReport= null;
              JasperReport masterReport= null;
            File fichero = new File(archivo);  
            if (fichero.exists()){
                System.out.println("El fichero " + archivo + " existe");
            try {
                System.out.println("ruta:"+archivo);
                
                masterReport= JasperCompileManager.compileReport(archivo);
            }catch (JRException e) {
               
                System.err.println("error de compilacion: "+e.getCause());
                e.printStackTrace();
            }
            }else
                System.out.println("Pues va a ser que no");
                
    }
    
    
}
