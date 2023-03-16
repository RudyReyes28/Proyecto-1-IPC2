/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.entidades;

public class Producto {
    private int codigo;
    private String nombre;
    private double costo;
    private double precio;
    private int existencias;

    public Producto(int codigo, String nombre, double costo, double precio, int existencias) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.precio = precio;
        this.existencias = existencias;
    }

    public Producto(int codigo, int existencias) {
        this.codigo = codigo;
        this.existencias = existencias;
    }

    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
    
    
}
