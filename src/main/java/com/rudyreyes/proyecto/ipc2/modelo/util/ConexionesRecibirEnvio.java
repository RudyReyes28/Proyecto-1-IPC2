/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.util;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoEnviado;
import static com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesPedidos.obtenerNombreProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author rudy-reyes
 */
public class ConexionesRecibirEnvio {
    
    public static ArrayList<Integer> obtenerIdEnvios(int codigoTienda) {
        ArrayList<Integer> listaEnvios = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idenvios FROM envios WHERE codigo_tienda = ? AND estado = ?");
            ps.setInt(1, codigoTienda);
            ps.setString(2, "DESPACHADO");
            
            rs = ps.executeQuery();

            while (rs.next()) {
                listaEnvios.add(rs.getInt("idenvios"));
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return listaEnvios;
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
    
    public static Integer obtenerIdPedido(int idEnvio) {
        Integer idPedido=null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idpedido FROM envios WHERE idenvios = " + idEnvio + ";");
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
    
    public static boolean modificarEstadoPedido(int idPedido,String estado){
       
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
            return false;
        }
        
    }
    
    public static boolean modificarEstadoEnvio(int idEnvio,String estado){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE envios SET estado=? WHERE idenvios =?");
            
            ps.setString(1, estado);
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
    
    public static boolean modificarFechaEnvio(int idEnvio,String fecha){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE envios SET fecha_recibida=? WHERE idenvios =?");
            
            ps.setString(1, fecha);
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
    
    public static boolean modificarCatalogoTienda(int idTienda, int codigoP, int cantidad){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE catalogo_tienda SET existencias = existencias + ? WHERE codigo_tienda =? AND codigo_producto =?");
            
            ps.setInt(1, cantidad);
            ps.setInt(2, idTienda);
            ps.setInt(3, codigoP);
            
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
    
    public static boolean modificarCatalogoBodega( int codigoP, int cantidad){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE catalogo_bodega SET existencias = existencias - ? WHERE codigo_producto =?");
            
            ps.setInt(1, cantidad);
            ps.setInt(2, codigoP);
            
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
