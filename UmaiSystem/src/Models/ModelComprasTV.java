/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * se hizo esta clase para contener los datos de la tabla que se ocupa en compras 
 * @author lalo
 */
public class ModelComprasTV {
    
         private Double cantidad;
         private String nombre;
         private Double unidad;
        private Double costo;
        private int indProd;

        public ModelComprasTV(Double cant,String nom,Double unidad,Double costo,int idProd){
            this.cantidad=cant;
            this.nombre=nom;
            this.unidad=unidad;
            this.costo=costo;
            this.indProd=idProd;
        }
        
        
        
        
    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the unidad
     */
    public Double getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(Double unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the costo
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * @return the indProd
     */
    public int getIndProd() {
        return indProd;
    }

    /**
     * @param indProd the indProd to set
     */
    public void setIndProd(int indProd) {
        this.indProd = indProd;
    }
        
    
    
}
