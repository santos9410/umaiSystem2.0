
package Models;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class ModelProductos {

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getDescripcionProd() {
        return descripcionProd;
    }

    public void setDescripcionProd(String descripcionProd) {
        this.descripcionProd = descripcionProd;
    }

    public String getUnidadMedidaProd() {
        return unidadMedidaProd;
    }

    public void setUnidadMedidaProd(String unidadMedidaProd) {
        this.unidadMedidaProd = unidadMedidaProd;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public ModelProductos(int idProd, int idProv, String nombreProd, String descripcionProd, String unidadMedidaProd, int precioCompra,int EstadoProd) {
        this.idProd = idProd;
        this.idProv = idProv;
        this.nombreProd = nombreProd;
        this.descripcionProd = descripcionProd;
        this.unidadMedidaProd = unidadMedidaProd;
        this.precioCompra = precioCompra;
        this.EstadoProducto=EstadoProd;
       
    }
    
  private int idProd;
  private int idProv;
  private String nombreProv;
  private String nombreProd;
  private String descripcionProd;
  private String unidadMedidaProd;
  private int precioCompra;
  private int EstadoProducto;
    
 
  @Override
  public String toString()
  {
      return nombreProd;
  }
  
  public void save()
  {
        try {
            Helpers.db.getDbCon().insert("INSERT INTO Productos values (NULL,"+idProv+",'"+nombreProd+"','"+descripcionProd+"','"+unidadMedidaProd+"',"+precioCompra+","+ EstadoProducto    +",null)");
        } catch (SQLException ex) {
            Logger.getLogger(ModelProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    
  
  public void update()
  {
       try {
           String sql="UPDATE Productos SET  idProv=" +idProv+" ,nombreProd='"+nombreProd+"',descripcionProd='"+descripcionProd+"',unidadMedidadProd='"+unidadMedidaProd+"',precioCompra="+precioCompra
                                         +",ContratoProducto=" +EstadoProducto      + " where idProd="+idProd;
          
           Helpers.db.getDbCon().insert(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ModelProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  public void update2()
  {
       try {
           String sql="UPDATE Productos SET  ContratoProducto=0  where idProd="+idProd;
          
           Helpers.db.getDbCon().insert(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ModelProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  
  public void delete()
  {
   try {
            Helpers.db.getDbCon().insert("DELETE FROM Productos WHERE idProd="+idProd);
        } catch (SQLException ex) {
            Logger.getLogger(ModelProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

    /**
     * @return the nombreProv
     */
    public String getNombreProv() {
        return nombreProv;
    }

    /**
     * @param nombreProv the nombreProv to set
     */
    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    /**
     * @return the EstadoProducto
     */
    public int getEstadoProducto() {
        return EstadoProducto;
    }

    /**
     * @param EstadoProducto the EstadoProducto to set
     */
    public void setEstadoProducto(int EstadoProducto) {
        this.EstadoProducto = EstadoProducto;
    }

    
    
    
}
