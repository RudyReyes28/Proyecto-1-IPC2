/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.util;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author rudy-reyes
 */
public class ConexionesPedidos {

    public static boolean agregarPedido(Pedido pedido) {
        Conexion con = new Conexion();
        PreparedStatement ps = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO pedido(codigo_tienda, codigo_usuario, fecha_pedido,estado) VALUES (?,?,?,?)");

            ps.setInt(1, pedido.getCodigoTienda());
            ps.setInt(2, pedido.getCodigoUsuario());
            ps.setString(3, pedido.getFechaPedido());
            ps.setString(4, pedido.getEstado());
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static int obtenerTienda(int codigoUsuario) {
        int codigoTienda = -1;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_tienda from usuario_tienda where codigo_usuario = " + codigoUsuario + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                codigoTienda = rs.getInt("codigo_tienda");
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return codigoTienda;
    }

    public static int obtenerIdPedido(int codigoUsuario) {
        int idPedido = -1;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idpedido from pedido where codigo_usuario = " + codigoUsuario + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                idPedido = rs.getInt("idpedido");
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return idPedido;
    }

    public static ArrayList<Integer> obtenerIdProductos(int codigoTienda) {
        ArrayList<Integer> codigoP = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_producto from catalogo_tienda where codigo_tienda = " + codigoTienda + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                codigoP.add(rs.getInt("codigo_producto"));
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return codigoP;
    }

    public static double obtenerCostoUnitario(int idProduto) {
        double costoU = -1;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT costo from catalogo_bodega where codigo_producto = " + idProduto + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                costoU = rs.getDouble("costo");
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return costoU;
    }

    public static String obtenerNombreProducto(int idProduto) {
        String nombreP = "";
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT nombre from catalogo_bodega where codigo_producto = " + idProduto + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                nombreP = rs.getString("nombre");
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return nombreP;
    }

    public static ArrayList<ProductoPedido> obtenerProductosPedidos(int idPedido) {
        ArrayList<ProductoPedido> listaP = new ArrayList<>();
        int codigoProducto;
        double costoUnitario;
        int cantidad;
        double costoTotal;
        String nombreProducto;
        ProductoPedido pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_producto, costo_unitario, cantidad,"
                    + "costoTotal from productos_pedidos where idpedido = " + idPedido + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                codigoProducto = rs.getInt("codigo_producto");
                costoUnitario = rs.getDouble("costo_unitario");
                cantidad = rs.getInt("cantidad");
                costoTotal = rs.getDouble("costoTotal");
                nombreProducto = obtenerNombreProducto(codigoProducto);
                pd = new ProductoPedido(codigoProducto, costoUnitario, cantidad, costoTotal, nombreProducto);
                listaP.add(pd);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return listaP;
    }

    
    public static ProductoPedido encontrarProductoPedido(int idPedido, int codigoP){
        
        int codigoProducto;
        double costoUnitario;
        int cantidad;
        double costoTotal;
        String nombreProducto;
        ProductoPedido pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_producto, costo_unitario, cantidad, "
                    + "costoTotal FROM productos_pedidos WHERE"
                    + " idpedido = ? AND codigo_producto = ?");
            
            ps.setInt(1, idPedido);
            ps.setInt(2, codigoP);
            rs = ps.executeQuery();

            while (rs.next()) {
                codigoProducto = rs.getInt("codigo_producto");
                costoUnitario = rs.getDouble("costo_unitario");
                cantidad = rs.getInt("cantidad");
                costoTotal = rs.getDouble("costoTotal");
                nombreProducto = obtenerNombreProducto(codigoP);
                
                pd = new ProductoPedido(idPedido,codigoProducto, costoUnitario, cantidad, costoTotal, nombreProducto);
                
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return pd;
    }
    
    public static boolean modificarProductoPedido(ProductoPedido productoP){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE productos_pedidos SET cantidad=?, costoTotal=? WHERE idpedido =? AND codigo_producto =?");
            
            ps.setInt(1, productoP.getCantidad());
            ps.setDouble(2, productoP.getCostoTotal());
            ps.setInt(3, productoP.getIdPedido());
            ps.setInt(4, productoP.getCodigo());
            
            int resultado = ps.executeUpdate();
            
            conexion.close();
            
            if(resultado>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        
    }
    
    public static boolean eliminarProductoPedido(int idPedido, int idProducto){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("DELETE FROM productos_pedidos WHERE idpedido =? AND codigo_producto =?");
            ps.setInt(1, idPedido);
            ps.setInt(2, idProducto);
            int resultado = ps.executeUpdate();
            
            conexion.close();
            
            if(resultado>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean modificarCostoPedido(int idPedido,double total){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE pedido SET total_pedido=? WHERE idpedido =?");
            
            ps.setDouble(1, total);
            ps.setInt(2, idPedido);
            
            int resultado = ps.executeUpdate();
            
            conexion.close();
            
            if(resultado>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        
    }
    
    public static String obtenerTipoTienda(int idTienda) {
        String nombreT = "";
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT tipo_tienda from tienda where codigo = " + idTienda + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                nombreT = rs.getString("tipo_tienda");
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return nombreT;
    }
}
