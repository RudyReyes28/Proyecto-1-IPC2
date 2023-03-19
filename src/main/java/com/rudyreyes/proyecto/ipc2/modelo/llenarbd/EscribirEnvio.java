/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.llenarbd;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Envio;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoEnviado;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class EscribirEnvio {
    
    public static boolean agregarEnvio(Envio envio){
        
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO envios(idenvios, idpedido, codigo_tienda,codigo_usuario,fecha_salida,fecha_recibida,total_envio,estado) VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, envio.getIdEnvio());
            ps.setInt(2, envio.getIdPedido());
            ps.setInt(3, envio.getCodigoTienda());
            ps.setInt(4, envio.getCodigoUsuario());
            ps.setString(5, envio.getFechaSalida());
            ps.setString(6, envio.getFechaRecibida());
            ps.setDouble(7, envio.getTotalEnvio());
            ps.setString(8, envio.getEstado());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean agregarProductosEnvio(ProductoEnviado producto){
        
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO productos_enviados(idenvio,codigo_producto,costo_unitario,cantidad, costo_total) VALUES (?,?,?,?,?)");
            ps.setInt(1, producto.getIdEnvio());
            ps.setInt(2, producto.getCodigo());
            ps.setDouble(3, producto.getCostoU());
            ps.setInt(4, producto.getCantidad());
            ps.setDouble(5, producto.getCostoTotal());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
