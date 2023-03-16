/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.entidades;

/**
 *
 * @author rudy-reyes
 */
public class ProductoEnviado {
    private int idEnvio;
    private int codigo;
    private double costoU;
    private int cantidad;
    private double costoTotal;

    public ProductoEnviado(int idEnvio, int codigo, double costoU, int cantidad, double costoTotal) {
        this.idEnvio = idEnvio;
        this.codigo = codigo;
        this.costoU = costoU;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getCostoU() {
        return costoU;
    }

    public void setCostoU(double costoU) {
        this.costoU = costoU;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
    
    
}
