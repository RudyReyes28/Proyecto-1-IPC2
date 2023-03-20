/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.entidades;

/**
 *
 * @author rudy-reyes
 */
public class Incidencia {
    
    private int idIncidencia;
    private int idEnvio;
    private int codigoTienda;
    private int codigoUsuario;
    private String fechaIncidencia;
    private String solucion;
    private String estado;

    public Incidencia(int idIncidencia, int codigoTienda, int codigoUsuario, String fechaIncidencia, String solucion, String estado) {
        this.idIncidencia = idIncidencia;
        this.codigoTienda = codigoTienda;
        this.codigoUsuario = codigoUsuario;
        this.fechaIncidencia = fechaIncidencia;
        this.solucion = solucion;
        this.estado = estado;
    }

    public Incidencia(int idIncidencia, int idEnvio, int codigoTienda, int codigoUsuario, String fechaIncidencia, String solucion, String estado) {
        this.idIncidencia = idIncidencia;
        this.idEnvio = idEnvio;
        this.codigoTienda = codigoTienda;
        this.codigoUsuario = codigoUsuario;
        this.fechaIncidencia = fechaIncidencia;
        this.solucion = solucion;
        this.estado = estado;
    }

    public Incidencia(int idEnvio, int codigoTienda, int codigoUsuario, String fechaIncidencia, String estado) {
        this.idEnvio = idEnvio;
        this.codigoTienda = codigoTienda;
        this.codigoUsuario = codigoUsuario;
        this.fechaIncidencia = fechaIncidencia;
        this.estado = estado;
    }

    public Incidencia(int idIncidencia, int codigoUsuario, String fechaIncidencia) {
        this.idIncidencia = idIncidencia;
        this.codigoUsuario = codigoUsuario;
        this.fechaIncidencia = fechaIncidencia;
    }

    
    
    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
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

    public String getFechaIncidencia() {
        return fechaIncidencia;
    }

    public void setFechaIncidencia(String fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
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
