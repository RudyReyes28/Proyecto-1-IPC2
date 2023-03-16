/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.llenarbd;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Producto;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Tienda;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class EscribirTiendas {
    
    public static boolean agregarTiendas(Tienda tienda){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO tienda(codigo,nombre,direccion,tipo_tienda) VALUES (?,?,?,?)");
            ps.setInt(1, tienda.getCodigo());
            ps.setString(2, tienda.getNombre());
            ps.setString(3, tienda.getDireccion());
            ps.setString(4, tienda.getTipoTienda());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public static boolean agregarProductosTienda(int codigoTienda,Producto producto){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO catalogo_tienda(codigo_tienda,codigo_producto,existencias) VALUES (?,?,?)");
            ps.setInt(1, codigoTienda);
            ps.setInt(2, producto.getCodigo());
            ps.setInt(3, producto.getExistencias());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
