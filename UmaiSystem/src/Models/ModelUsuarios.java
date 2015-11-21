/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lalo
 */
public class ModelUsuarios {

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getPswUsu() {
        return pswUsu;
    }

    public void setPswUsu(String pswUsu) {
        this.pswUsu = pswUsu;
    }

    public String getCalleUsu() {
        return calleUsu;
    }

    public void setCalleUsu(String calleUsu) {
        this.calleUsu = calleUsu;
    }

    public String getColoUsu() {
        return coloUsu;
    }

    public void setColoUsu(String coloUsu) {
        this.coloUsu = coloUsu;
    }

    public String getCiudUsu() {
        return ciudUsu;
    }

    public void setCiudUsu(String ciudUsu) {
        this.ciudUsu = ciudUsu;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefonoUsu() {
        return telefonoUsu;
    }

    public void setTelefonoUsu(String telefonoUsu) {
        this.telefonoUsu = telefonoUsu;
    }

    public String getAvatarUsu() {
        return avatarUsu;
    }

    public void setAvatarUsu(String avatarUsu) {
        this.avatarUsu = avatarUsu;
    }

    public int getSueldoUsu() {
        return sueldoUsu;
    }

    public void setSueldoUsu(int sueldoUsu) {
        this.sueldoUsu = sueldoUsu;
    }

    public int getPuntosUsu() {
        return puntosUsu;
    }

    public void setPuntosUsu(int puntosUsu) {
        this.puntosUsu = puntosUsu;
    }

    public int getTipoPermiso() {
        return tipoPermiso;
    }

    public void setTipoPermiso(int tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public int getEstadoContrado() {
        return estadoContrado;
    }

    public void setEstadoContrado(int estadoContrado) {
        this.estadoContrado = estadoContrado;
    }

    public ModelUsuarios(){}
    
    public ModelUsuarios(int idUsu, String nombreUsu, String pswUsu, String calleUsu, 
            String coloUsu, String ciudUsu, String codigoPostal, String telefonoUsu, 
            String avatarUsu, int sueldoUsu, int puntosUsu, int tipoPermiso, int estadoContrado) {
        this.idUsu = idUsu;
        this.nombreUsu = nombreUsu;
        this.pswUsu = pswUsu;
        this.calleUsu = calleUsu;
        this.coloUsu = coloUsu;
        this.ciudUsu = ciudUsu;
        this.codigoPostal = codigoPostal;
        this.telefonoUsu = telefonoUsu;
        this.avatarUsu = avatarUsu;
        this.sueldoUsu = sueldoUsu;
        this.puntosUsu = puntosUsu;
        this.tipoPermiso = tipoPermiso;
        this.estadoContrado = estadoContrado;
       
    }
    
   private int idUsu;
   private String nombreUsu;
   private String pswUsu;
   private String calleUsu;
   private String coloUsu;
   private String ciudUsu;
   private String codigoPostal;
   private String telefonoUsu;
   private String avatarUsu;
   private int sueldoUsu;
   private int puntosUsu;
   private int tipoPermiso;
   private int estadoContrado;
  
  
   @Override
  public String toString()
  {
    
      return nombreUsu;
      
      
       
  }
  
  
   
   
   public void save()
   {
        String sql = String.format("Insert into usuarios values(null,'%s','%s','%s','%s','%s','%s','%s','%s',%d,%d,%d,%d)",nombreUsu,pswUsu,calleUsu,coloUsu,ciudUsu,codigoPostal,telefonoUsu,avatarUsu,sueldoUsu,puntosUsu,tipoPermiso,estadoContrado); 
        try {
            Helpers.db.getDbCon().insert(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ModelUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
   }
   
   public void updatePass()
   {
       String sql = String.format("update usuarios set nombreUsu='%s', pswUsu='%s',calleUsu='%s',coloUsu='%s',ciudUsu='%s',codigoPostal='%s',telefonoUsu='%s',avatarUsu='%s',sueldoUsu=%d,puntosUsu=%d,tipoPermiso=%d,estadoContrato=%d WHERE idUsu=%d",nombreUsu,pswUsu,calleUsu,coloUsu,ciudUsu,codigoPostal,telefonoUsu,avatarUsu,sueldoUsu,puntosUsu,tipoPermiso,estadoContrado,idUsu); 
        try {
            Helpers.db.getDbCon().insert(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(ModelUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
   public void update()
   {
       String sql = String.format("update usuarios set nombreUsu='%s',calleUsu='%s',coloUsu='%s',ciudUsu='%s',codigoPostal='%s',telefonoUsu='%s',avatarUsu='%s',sueldoUsu=%d,puntosUsu=%d,tipoPermiso=%d,estadoContrato=%d WHERE idUsu=%d",nombreUsu,calleUsu,coloUsu,ciudUsu,codigoPostal,telefonoUsu,avatarUsu,sueldoUsu,puntosUsu,tipoPermiso,estadoContrado,idUsu); 
        try {
            Helpers.db.getDbCon().insert(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(ModelUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
   public void update2()
   {
       String sql = String.format("update usuarios set estadoContrato=%d WHERE idUsu=%d",estadoContrado,idUsu); 
        try {
            Helpers.db.getDbCon().insert(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(ModelUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
   
   
   public void delete()
   {
       String sql = String.format("DELETE FROM usuarios where idUsu=%d",idUsu);
       try{     
            Helpers.db.getDbCon().insert(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ModelUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

   
   
   

   
    
}
