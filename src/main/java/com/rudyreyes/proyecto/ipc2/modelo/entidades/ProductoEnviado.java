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
    private String nombre;

    public ProductoEnviado(int idEnvio, int codigo, double costoU, int cantidad, double costoTotal) {
        this.idEnvio = idEnvio;
        this.codigo = codigo;
        this.costoU = costoU;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
    }

    public ProductoEnviado(int codigo, double costoU, int cantidad, double costoTotal, String nombre) {
        
        this.codigo = codigo;
        this.costoU = costoU;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
        this.nombre = nombre;
    }

    public ProductoEnviado(int idEnvio, int codigo, double costoU, int cantidad, double costoTotal, String nombre) {
        this.idEnvio = idEnvio;
        this.codigo = codigo;
        this.costoU = costoU;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
        this.nombre = nombre;
    }

    public ProductoEnviado(int idEnvio, int codigo, int cantidad, double costoTotal) {
        this.idEnvio = idEnvio;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
    }

    public ProductoEnviado(int codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
