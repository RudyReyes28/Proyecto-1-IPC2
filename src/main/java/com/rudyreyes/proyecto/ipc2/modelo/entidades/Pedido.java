/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.entidades;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Pedido {
    private int idPedido;
    private int codigoTienda;
    private int codigoUsuario;
    //private Date fechaPedido;
    private String fechaPedido;
    private double totalPedido;
    private String estado;

    public Pedido(int idPedido, int codigoTienda, int codigoUsuario, String fechaPedido, double totalPedido, String estado) {
        this.idPedido = idPedido;
        this.codigoTienda = codigoTienda;
        this.codigoUsuario = codigoUsuario;
        /*SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        try {
            this.fechaPedido = (Date) formato.parse(fechaPedido);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }*/
        this.fechaPedido = fechaPedido;
        this.totalPedido = totalPedido;
        this.estado = estado;
    }

    public Pedido(int codigoTienda, int codigoUsuario, String fechaPedido, String estado) {
        this.codigoTienda = codigoTienda;
        this.codigoUsuario = codigoUsuario;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
    }

    public Pedido(int idPedido, int codigoUsuario, String fechaPedido, double totalPedido) {
        this.idPedido = idPedido;
        this.codigoUsuario = codigoUsuario;
        this.fechaPedido = fechaPedido;
        this.totalPedido = totalPedido;
    }
    
    
    

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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

    public String getFechaPedido() {
        return fechaPedido;
    }

 

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
