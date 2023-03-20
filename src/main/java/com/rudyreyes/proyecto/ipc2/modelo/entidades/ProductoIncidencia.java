/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.entidades;

/**
 *
 * @author rudy-reyes
 */
public class ProductoIncidencia {
    private int idIncidencia;
    private int codigo;
    private int cantidad;
    private String motivo;

    public ProductoIncidencia(int idIncidencia, int codigo, int cantidad, String motivo) {
        this.idIncidencia = idIncidencia;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    public ProductoIncidencia(int codigo, int cantidad, String motivo) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    
    
    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    
}
