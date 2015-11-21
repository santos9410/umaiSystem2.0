/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author lalo
 */
public class ModelProveedores {
    private int idProv;
    private String nombreProv;
    private String codigoProv;
    private String colonProv;
    private String ciudadProv;
    private String calleProv;
    private String telefonoProv;
    private String emailProv;
    private int contrato;
    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public String getCiudadProv() {
        return ciudadProv;
    }

    public void setCiudadProv(String ciudadProv) {
        this.ciudadProv = ciudadProv;
    }

    public String getTelefonoProv() {
        return telefonoProv;
    }

    public void setTelefonoProv(String telefonoProv) {
        this.telefonoProv = telefonoProv;
    }

    public String getEmailProv() {
        return emailProv;
    }

    public void setEmailProv(String emailProv) {
        this.emailProv = emailProv;
    }

    public ModelProveedores(int idProv, String nombreProv,String codigoProv, String ciudadProv, 
            String coloProv,String calleProv,String telefonoProv, String emailProv,int contrato) {
        this.idProv = idProv;
        this.nombreProv = nombreProv;
        this.codigoProv=codigoProv;
        this.ciudadProv = ciudadProv;
        this.colonProv=coloProv;
        this.calleProv=calleProv;
        this.telefonoProv = telefonoProv;
        this.emailProv = emailProv;
        this.contrato=contrato;
    }
  
    @Override
    public String toString(){
        return nombreProv;
    }
    public void save()
    {
        try {
            Helpers.db.getDbCon().insert("INSERT INTO proveedor values(null,'"+nombreProv+"','"+codigoProv+"','"+ciudadProv+"','"
                    +colonProv+"','"+ calleProv+"','"+telefonoProv+"','"+emailProv +"',"+contrato+");");
        } catch (SQLException ex) {
            Logger.getLogger(ModelProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        idProv int auto_increment primary key,
		nombreProv varchar(30) not null,
        codigoProv varchar(20),
		ciudadProv varchar(50),
        coloniaProv varchar(50),
        calleProv varchar(30),
		telefonoProv  varchar(20),
		emailProv varchar(50)
        */
        
    }
    
    public void update()
    {
        try {
            String sql="UPDATE proveedor set nombreProv='"+nombreProv+ "',codigoProv='"+codigoProv+"',ciudadProv='"+ciudadProv
                                        + "',coloniaProv='"+colonProv+ "',telefonoProv='"+telefonoProv+"',calleProv='"+calleProv
                                        +"',emailProv='"+emailProv+"',Contrato="+ contrato+"  WHERE idProv="+idProv+";";
           
           Helpers.db.getDbCon().insert(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ModelProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void update2()
    {
        try {
            String sql="UPDATE proveedor set Contrato=0 WHERE idProv="+idProv+";";
           
           Helpers.db.getDbCon().insert(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ModelProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void delete()
    {
     try {
            Helpers.db.getDbCon().insert("DELETE FROM proveedor where idProv="+idProv);
        } catch (SQLException ex) {
            Logger.getLogger(ModelProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the codigoProv
     */
    public String getCodigoProv() {
        return codigoProv;
    }

    /**
     * @param codigoProv the codigoProv to set
     */
    public void setCodigoProv(String codigoProv) {
        this.codigoProv = codigoProv;
    }

    /**
     * @return the colonProv
     */
    public String getColonProv() {
        return colonProv;
    }

    /**
     * @param colonProv the colonProv to set
     */
    public void setColonProv(String colonProv) {
        this.colonProv = colonProv;
    }

    /**
     * @return the calleProv
     */
    public String getCalleProv() {
        return calleProv;
    }

    /**
     * @param calleProv the calleProv to set
     */
    public void setCalleProv(String calleProv) {
        this.calleProv = calleProv;
    }

    /**
     * @return the contrato
     */
    public int getContrato() {
        return contrato;
    }

    /**
     * @param contrato the contrato to set
     */
    public void setContrato(int contrato) {
        this.contrato = contrato;
    }
}
