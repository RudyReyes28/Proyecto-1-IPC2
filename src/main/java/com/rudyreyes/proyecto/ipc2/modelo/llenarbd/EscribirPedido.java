/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.llenarbd;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class EscribirPedido {
    
    public static boolean agregarPedido(Pedido pedido){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO pedido(idpedido, codigo_tienda,codigo_usuario,fecha_pedido,total_pedido,estado) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, pedido.getIdPedido());
            ps.setInt(2, pedido.getCodigoTienda());
            ps.setInt(3, pedido.getCodigoUsuario());
            ps.setString(4, pedido.getFechaPedido());
            ps.setDouble(5, pedido.getTotalPedido());
            ps.setString(6, pedido.getEstado());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public static boolean agregarProductosPedido(ProductoPedido producto){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO productos_pedidos(idpedido,codigo_producto,costo_unitario,cantidad, costoTotal) VALUES (?,?,?,?,?)");
            ps.setInt(1, producto.getIdPedido());
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
