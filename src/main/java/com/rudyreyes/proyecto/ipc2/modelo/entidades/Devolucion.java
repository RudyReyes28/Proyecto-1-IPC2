/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.entidades;

/**
 *
 * @author rudy-reyes
 */
public class Devolucion {
    private int idDevolucion;
    private int idEnvio;
    private int codigoTienda;
    private int codigoUsuario;
    private String fechaDevolucion;
    private double totalDevolucion;
    private String estado;

    public Devolucion(int idDevolucion, int codigoTienda, int codigoUsuario, String fechaDevolucion, double totalDevolucion, String estado) {
        this.idDevolucion = idDevolucion;
        this.codigoTienda = codigoTienda;
        this.codigoUsuario = codigoUsuario;
        this.fechaDevolucion = fechaDevolucion;
        this.totalDevolucion = totalDevolucion;
        this.estado = estado;
    }

    public Devolucion(int idDevolucion, int idEnvio, int codigoTienda, int codigoUsuario, String fechaDevolucion, double totalDevolucion, String estado) {
        this.idDevolucion = idDevolucion;
        this.idEnvio = idEnvio;
        this.codigoTienda = codigoTienda;
        this.codigoUsuario = codigoUsuario;
        this.fechaDevolucion = fechaDevolucion;
        this.totalDevolucion = totalDevolucion;
        this.estado = estado;
    }
    
    

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public int getCodigoTienda() {
        return codigoTienda;
    }

    public void setCodigoTienda(int codigoTienda) {
        this.codigoTienda = codigoTienda;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public double getTotalDevolucion() {
        return totalDevolucion;
    }

    public void setTotalDevolucion(double totalDevolucion) {
        this.totalDevolucion = totalDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }
    
    
}
