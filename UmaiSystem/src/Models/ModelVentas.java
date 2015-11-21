/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Helpers.db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class ModelVentas {
    
    private int idDetalle;
    private int idVenta;
    private int idReceta;
    private int precio;
    private int cantidad;
   
    private int descuento;
    
    public ModelVentas(){
        
    }
    
    public ModelVentas(int idDetalle,int idVenta,int precio,int cantidad,int idRec){
        this.idDetalle=idDetalle;
        this.idVenta=idVenta;
        this.precio=precio;
        this.cantidad=cantidad;
        this.idReceta=idRec;
        
    }

    
    
    
    public void saveVenta(String fecha,int total,String mesa){
        try {
            db.getDbCon().insert("insert into ventas values(null,1,2,'"+fecha+"',null,"+ total+",'"
                    + mesa+ "',"+ "0" +")");
            
        } catch (SQLException ex) {
            Logger.getLogger(ModelVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void saveDetalleVentas(){
        try {
                db.getDbCon().insert("insert into detalle_ventas values("+idDetalle+"," +idVenta+","+idReceta+","
                        +precio+","+cantidad  +")");
            } catch (SQLException ex) {
                Logger.getLogger(ModelVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    public int getidventaMax(){
        ResultSet rs = null;
        int idventa=0;
        try {
            rs=db.getDbCon().query("SELECT MAX(idVenta) AS id FROM ventas;");
             if(rs.next()){
               idventa=rs.getInt(1);
            }
             rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
       return idventa;     
    }
    
    public int getidDetalleV(){
        ResultSet rs=null;
        int idD = 1;
        try {
            rs=db.getDbCon().query("SELECT MAX(idDetalle) AS id FROM detalle_ventas;");
            if(rs.next()){
               idD=rs.getInt(1)+1;
            }
             
             rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
         return idD;   
    }
    
    /**
     * @return the idDetalle
     */
    public int getIdDetalle() {
        return idDetalle;
    }

    /**
     * @param idDetalle the idDetalle to set
     */
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    /**
     * @return the idVenta
     */
    public int getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    /**
     * @return the idReceta
     */
    public int getIdReceta() {
        return idReceta;
    }

    /**
     * @param idRec the idProd to set
     */
    public void setIdReceta(int idRec) {
        this.idReceta = idRec;
    }

    /**
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    /**
     * @return the descuento
     */
    public int getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    
}
