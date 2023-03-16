/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.entidades;

public class Tienda {
    
    private int codigo;
    private String nombre;
    private String direccion;
    private String tipoTienda;

    public Tienda(int codigo, String nombre, String direccion, String tipoTienda) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoTienda = tipoTienda;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoTienda() {
        return tipoTienda;
    }

    public void setTipoTienda(String tipoTienda) {
        this.tipoTienda = tipoTienda;
    }
    
    
    
    
}
