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
 * @author 
 */
public class modelAgregarCompras {

    private String fecha;
    private int idCompra;
    private int idDetalleCompra;
    private Double cant;
    private int  idProd;
    private Double precio;
    private Double total;

    public modelAgregarCompras() {
    }
    
    
    
    public modelAgregarCompras(int idDetalleCompra,int idCompra,Double cant,int idProd
            ,Double precio,Double total) {
    
            this.idDetalleCompra=idDetalleCompra;
            this.idCompra=idCompra;
            this.cant=cant;
            this.idProd=idProd;
            this.precio=precio;
            this.total=total;
    
    }

    public void saveCompras(int idCompra, String fecha)
  {
    
        try {
            String sql ="INSERT INTO compras values(null,'"+fecha+"');";
           
            Helpers.db.getDbCon().insert( sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(modelAgregarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
     
    
    public void saveDetalleCompras() {
        try {
            String sql="INSERT INTO detalle_compras values("+idDetalleCompra+ ","  +idCompra  
                 +","+idProd+","+cant+","+precio+","+total +");";
            
            Helpers.db.getDbCon().insert(sql);
            String sql2="UPDATE productos set cantidadStock=cantidadStock+"+cant+" where idProd="+idProd;
             Helpers.db.getDbCon().insert(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(modelAgregarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the idCompra
     */
    public int getIdCompra() {
        return idCompra;
    }

    /**
     * @param idCompra the idCompra to set
     */
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * @return the cant
     */
    public Double getCant() {
        return cant;
    }

    /**
     * @param cant the cant to set
     */
    public void setCant(Double cant) {
        this.cant = cant;
    }

    

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the idDetalleCompra
     */
    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    /**
     * @param idDetalleCompra the idDetalleCompra to set
     */
    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    /**
     * @return the idProd
     */
    public int getIdProd() {
        return idProd;
    }

    /**
     * @param idProd the idProd to set
     */
    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    
    
   
    
    
    
}


