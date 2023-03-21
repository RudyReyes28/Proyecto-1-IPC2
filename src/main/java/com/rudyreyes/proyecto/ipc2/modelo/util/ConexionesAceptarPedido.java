/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.util;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Devolucion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ConexionesAceptarPedido {
    
    public static ArrayList<Integer> obtenerTiendas() {
        ArrayList<Integer> tiendas = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo from tienda WHERE tipo_tienda = ?");
            ps.setString(1, "SUPERVISADA");
            
            rs = ps.executeQuery();

            while (rs.next()) {
                tiendas.add(rs.getInt("codigo"));
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return tiendas;
    }
    
    public static ArrayList<Pedido> obtenerPedidos(int idTienda) {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        java.sql.Date fechaD = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int idPedido;
        int codigoUsuario;
        String fecha;
        double total;
        Pedido pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idpedido, codigo_usuario, fecha_pedido, total_pedido"
                    + " from pedido WHERE codigo_tienda =? AND estado = ?");

            ps.setInt(1, idTienda);
            ps.setString(2, "PENDIENTE");
            rs = ps.executeQuery();

            while (rs.next()) {
                idPedido = rs.getInt("idpedido");
                codigoUsuario = rs.getInt("codigo_usuario");
                fechaD = rs.getDate("fecha_pedido");
                fecha = dateFormat.format(fechaD);
                total = rs.getDouble("total_pedido");

                pd = new Pedido(idPedido, codigoUsuario, fecha, total);
                pedidos.add(pd);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return pedidos;
    }
    
    public static boolean modificarEstadoPedido(int idPedido, String estado){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE pedido SET estado=? WHERE idpedido =?");
            
            ps.setString(1, estado);
            ps.setInt(2, idPedido);
            
            int resultado = ps.executeUpdate();
            
            conexion.close();
            
            if(resultado>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

}
