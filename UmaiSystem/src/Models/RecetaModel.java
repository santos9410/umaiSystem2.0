/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Models.ModelRecetaTV;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author antonio
 */
public class RecetaModel {

    public RecetaModel(int idRec, String nombreRec, String tipoRec, int tamañoPorcion, int numeroPorcion, int tiempoPreparacion, String dificultad, String procedimiento, ObservableList<ModelRecetaTV> data) {
        this.idRec = idRec;
        this.nombreRec = nombreRec;
        this.tipoRec = tipoRec;
        this.tamañoPorcion = tamañoPorcion;
        this.numeroPorcion = numeroPorcion;
        this.tiempoPreparacion = tiempoPreparacion;
        this.dificultad = dificultad;
        this.procedimiento = procedimiento;
        this.data = data;
    }
    
    private int idRec;
    private String nombreRec;
    private String tipoRec;
    private int tamañoPorcion;
    private int numeroPorcion;
    private int tiempoPreparacion;
    private String dificultad;
    private String procedimiento;
    private ObservableList<ModelRecetaTV> data;

    public ObservableList<ModelRecetaTV> getData() {
        return data;
    }

    public void setData(ObservableList<ModelRecetaTV> data) {
        this.data = data;
    }
    
    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public String getNombreRec() {
        return nombreRec;
    }

    public void setNombreRec(String nombreRec) {
        this.nombreRec = nombreRec;
    }

    public String getTipoRec() {
        return tipoRec;
    }

    public void setTipoRec(String tipoRec) {
        this.tipoRec = tipoRec;
    }

    public int getTamañoPorcion() {
        return tamañoPorcion;
    }

    public void setTamañoPorcion(int tamañoPorcion) {
        this.tamañoPorcion = tamañoPorcion;
    }

    public int getNumeroPorcion() {
        return numeroPorcion;
    }

    public void setNumeroPorcion(int numeroPorcion) {
        this.numeroPorcion = numeroPorcion;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }
    
  
    
    
}
