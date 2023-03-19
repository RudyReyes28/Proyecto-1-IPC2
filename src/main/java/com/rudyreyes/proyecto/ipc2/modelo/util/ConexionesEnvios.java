/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.util;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Envio;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoEnviado;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoPedido;
import static com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesPedidos.obtenerNombreProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author rudy-reyes
 */
public class ConexionesEnvios {

    public static ArrayList<Integer> obtenerTiendas(int codigoUsuario) {
        ArrayList<Integer> tiendas = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_tienda from conexion_tienda_bodega WHERE codigo_bodega = " + codigoUsuario + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                tiendas.add(rs.getInt("codigo_tienda"));
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
        int codigoTienda;
        String fecha;
        double costoTotal;
        Pedido pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idpedido, codigo_usuario, fecha_pedido,"
                    + "total_pedido from pedido WHERE codigo_tienda =? AND estado = ?;");

            ps.setInt(1, idTienda);
            ps.setString(2, "SOLICITADO");
            rs = ps.executeQuery();

            while (rs.next()) {
                idPedido = rs.getInt("idpedido");
                codigoTienda = rs.getInt("codigo_usuario");
                fechaD = rs.getDate("fecha_pedido");
                fecha = dateFormat.format(fechaD);
                costoTotal = rs.getDouble("total_pedido");

                pd = new Pedido(idPedido, codigoTienda, fecha, costoTotal);
                pedidos.add(pd);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return pedidos;
    }

    public static boolean agregarEnvio(Envio envio) {
        Conexion con = new Conexion();
        PreparedStatement ps = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO envios(codigo_tienda, codigo_usuario, fecha_salida,estado) VALUES (?,?,?,?)");

            ps.setInt(1, envio.getCodigoTienda());
            ps.setInt(2, envio.getCodigoUsuario());
            ps.setString(3, envio.getFechaSalida());
            ps.setString(4, envio.getEstado());
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static int obtenerIdEnvio(int codigoUsuario) {
        int idEnvio = -1;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idenvios FROM envios WHERE codigo_usuario = " + codigoUsuario + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                idEnvio = rs.getInt("idenvios");
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return idEnvio;
    }

    public static boolean agregarProductosEnvio(ProductoPedido producto, int idEnvio) {

        Conexion con = new Conexion();
        PreparedStatement ps = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO productos_enviados(idenvio,codigo_producto,costo_unitario,cantidad, costo_total) VALUES (?,?,?,?,?)");
            ps.setInt(1, idEnvio);
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

    public static boolean agregarProductosEnvio(ProductoEnviado producto) {

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

    public static ArrayList<ProductoEnviado> obtenerProductosEnviados(int idEnvio) {
        ArrayList<ProductoEnviado> listaP = new ArrayList<>();
        int codigoProducto;
        double costoUnitario;
        int cantidad;
        double costoTotal;
        String nombreProducto;
        ProductoEnviado pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_producto, costo_unitario, cantidad,"
                    + "costo_total FROM productos_enviados WHERE idenvio = " + idEnvio + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                codigoProducto = rs.getInt("codigo_producto");
                costoUnitario = rs.getDouble("costo_unitario");
                cantidad = rs.getInt("cantidad");
                costoTotal = rs.getDouble("costo_total");
                nombreProducto = obtenerNombreProducto(codigoProducto);
                pd = new ProductoEnviado(codigoProducto, costoUnitario, cantidad, costoTotal, nombreProducto);
                listaP.add(pd);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return listaP;
    }

    public static ProductoEnviado encontrarProductoEnvio(int idEnvio, int codigoP) {

        int codigoProducto;
        double costoUnitario;
        int cantidad;
        double costoTotal;
        String nombreProducto;
        ProductoEnviado pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_producto, costo_unitario, cantidad, "
                    + "costo_total FROM productos_enviados WHERE"
                    + " idenvio = ? AND codigo_producto = ?");

            ps.setInt(1, idEnvio);
            ps.setInt(2, codigoP);
            rs = ps.executeQuery();

            while (rs.next()) {
                codigoProducto = rs.getInt("codigo_producto");
                costoUnitario = rs.getDouble("costo_unitario");
                cantidad = rs.getInt("cantidad");
                costoTotal = rs.getDouble("costo_total");
                nombreProducto = obtenerNombreProducto(codigoP);

                pd = new ProductoEnviado(idEnvio, codigoProducto, costoUnitario, cantidad, costoTotal, nombreProducto);

            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return pd;
    }

    public static boolean modificarProductoEnvio(ProductoEnviado productoP) {

        Conexion con = new Conexion();
        PreparedStatement ps = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE productos_enviados SET cantidad=?, costo_total=? WHERE idenvio =? AND codigo_producto =?");

            ps.setInt(1, productoP.getCantidad());
            ps.setDouble(2, productoP.getCostoTotal());
            ps.setInt(3, productoP.getIdEnvio());
            ps.setInt(4, productoP.getCodigo());

            int resultado = ps.executeUpdate();

            conexion.close();

            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean eliminarProductoEnvio(int idEnvio, int idProducto) {
        Conexion con = new Conexion();
        PreparedStatement ps = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("DELETE FROM productos_enviados WHERE idenvio =? AND codigo_producto =?");
            ps.setInt(1, idEnvio);
            ps.setInt(2, idProducto);
            int resultado = ps.executeUpdate();

            conexion.close();

            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean modificarCostoEnvio(int idEnvio,double total){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE envios SET total_envio=? WHERE idenvios =?");
            
            ps.setDouble(1, total);
            ps.setInt(2, idEnvio);
            
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
    
    public static boolean modificarEstadoPedido(int idPedido){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE pedido SET estado=? WHERE idpedido =?");
            
            ps.setString(1, "ENVIADO");
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
}
