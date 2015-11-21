/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author antonio
 */
public class ModelVentaTV {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public void actualizaTotal()
    {
        this.total = this.precio*this.Cantidad;
    }

    
    
    private int id;
    private String nombre;
    private int precio;
    private int total;
    private int idReceta;
    
    public int getCantidad() {
        return Cantidad;
    }
    public ModelVentaTV(){
        
    }

    public ModelVentaTV(int id, String nombre, int precio, int Cantidad, int total,int idReceta) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.total = total;
        this.Cantidad = Cantidad;
        this.idReceta=idReceta;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }
    private int Cantidad;

    /**
     * @return the idReceta
     */
    public int getIdReceta() {
        return idReceta;
    }

    /**
     * @param idReceta the idReceta to set
     */
    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }
        
}
