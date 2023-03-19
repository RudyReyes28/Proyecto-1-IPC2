/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.entidades;

/**
 *
 * @author rudy-reyes
 */
public class Envio {
    
    private int idEnvio;
    private int codigoTienda;
    private int codigoUsuario;
    private String fechaSalida;
    private String fechaRecibida;
    private double totalEnvio;
    private String estado;

    public Envio(int idEnvio, int codigoTienda, int codigoUsuario, String fechaSalida, String fechaRecibida, double totalEnvio, String estado) {
        this.idEnvio = idEnvio;
        this.codigoTienda = codigoTienda;
        this.codigoUsuario = codigoUsuario;
        this.fechaSalida = fechaSalida;
        this.fechaRecibida = fechaRecibida;
        this.totalEnvio = totalEnvio;
        this.estado = estado;
    }

    public Envio(int idEnvio, int codigoTienda, int codigoUsuario, String fechaSalida, double totalEnvio, String estado) {
        this.idEnvio = idEnvio;
        this.codigoTienda = codigoTienda;
        this.codigoUsuario = codigoUsuario;
        this.fechaSalida = fechaSalida;
        this.totalEnvio = totalEnvio;
        this.estado = estado;
    }

    public Envio(int codigoTienda, int codigoUsuario, String fechaSalida, String estado) {
        this.codigoTienda = codigoTienda;
        this.codigoUsuario = codigoUsuario;
        this.fechaSalida = fechaSalida;
        this.estado = estado;
    }

    
    
    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
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

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaRecibida() {
        return fechaRecibida;
    }

    public void setFechaRecibida(String fechaRecibida) {
        this.fechaRecibida = fechaRecibida;
    }

    public double getTotalEnvio() {
        return totalEnvio;
    }

    public void setTotalEnvio(double totalEnvio) {
        this.totalEnvio = totalEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
