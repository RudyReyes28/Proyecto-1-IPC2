/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.entidades;

import com.rudyreyes.proyecto.ipc2.modelo.util.CifrarContraseña;

/**
 *
 * @author rudy-reyes
 */
public class SupervisorTienda {
    private int codigo;
    private String nombre;
    private String nombreUsuario;
    private String contraseña;
    private String correo;

    public SupervisorTienda(int codigo, String nombre, String nombreUsuario, String contraseña, String correo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = CifrarContraseña.hash(contraseña);
        this.correo = correo;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = CifrarContraseña.hash(contraseña);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}
