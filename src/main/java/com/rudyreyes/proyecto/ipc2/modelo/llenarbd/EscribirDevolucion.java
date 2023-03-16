/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.llenarbd;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Devolucion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoDevolucion;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class EscribirDevolucion {
    
    public static boolean agregarDevolucion(Devolucion devolucion){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO devolucion(iddevolucion, codigo_tienda,codigo_usuario,fecha_devolucion,total,estado) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, devolucion.getIdDevolucion());
            ps.setInt(2, devolucion.getCodigoTienda());
            ps.setInt(3, devolucion.getCodigoUsuario());
            ps.setString(4, devolucion.getFechaDevolucion());
            ps.setDouble(5, devolucion.getTotalDevolucion());
            ps.setString(6, devolucion.getEstado());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public static boolean agregarProductoDevolucion(ProductoDevolucion producto){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO productos_devueltos(iddevolucion,codigo_producto,costo_unitario,cantidad,costo_total, motivo) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, producto.getIdDevolucion());
            ps.setInt(2, producto.getCodigo());
            ps.setDouble(3, producto.getCostoU());
            ps.setInt(4, producto.getCantidad());
            ps.setDouble(5, producto.getCostoTotal());
            ps.setString(6, producto.getMotivo());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
