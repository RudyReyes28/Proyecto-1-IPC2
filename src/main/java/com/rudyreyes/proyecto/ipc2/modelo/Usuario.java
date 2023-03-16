/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo;

/**
 *
 * @author rudy-reyes
 */
public class Usuario {
    
    private int codigo;
    private String nombre;
    private String cargo;

    public Usuario(int codigo, String nombre, String cargo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cargo = cargo;
    }

    public Usuario() {

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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
}
