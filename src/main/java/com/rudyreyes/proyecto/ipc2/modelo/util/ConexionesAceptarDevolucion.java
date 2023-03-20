/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.util;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Devolucion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Incidencia;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoDevolucion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoIncidencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ConexionesAceptarDevolucion {
    
    public static ArrayList<Devolucion> obtenerDevoluciones(int idTienda) {
        ArrayList<Devolucion> devoluciones = new ArrayList<>();

        java.sql.Date fechaD = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int idDevolucion;
        int codigoUsuario;
        String fecha;
        Devolucion pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT iddevolucion, codigo_usuario, fecha_devolucion"
                    + " from devolucion WHERE codigo_tienda =? AND estado = ?");

            ps.setInt(1, idTienda);
            ps.setString(2, "ACTIVA");
            rs = ps.executeQuery();

            while (rs.next()) {
                idDevolucion = rs.getInt("iddevolucion");
                codigoUsuario = rs.getInt("codigo_usuario");
                fechaD = rs.getDate("fecha_devolucion");
                fecha = dateFormat.format(fechaD);

                pd = new Devolucion(idDevolucion, codigoUsuario, fecha);
                devoluciones.add(pd);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return devoluciones;
    }
    
    public static ArrayList<ProductoDevolucion> obtenerProductosDevolucion(int idDevolucion) {
        ArrayList<ProductoDevolucion> listaP = new ArrayList<>();
        int codigoProducto;
        int cantidad;
        String motivo;
        
        ProductoDevolucion pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_producto, cantidad,"
                    + " motivo FROM productos_devueltos WHERE iddevolucion = " + idDevolucion + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                codigoProducto = rs.getInt("codigo_producto");
                cantidad = rs.getInt("cantidad");
                motivo = rs.getString("motivo");
                pd = new ProductoDevolucion(codigoProducto, cantidad, motivo);
                listaP.add(pd);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }
        
        return listaP;
    }
    
    public static boolean modificarCatalogoTienda(int idTienda, int codigoP, int cantidad){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE catalogo_tienda SET existencias = existencias - ? WHERE codigo_tienda =? AND codigo_producto =?");
            
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
            e.printStackTrace();
            return false;
        }
        
    }
    
    public static boolean modificarCatalogoBodega( int codigoP, int cantidad){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE catalogo_bodega SET existencias = existencias + ? WHERE codigo_producto =?");
            
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
    
    public static boolean modificarEstadoDevolucion(int idDevolucion, String estado){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE devolucion SET estado=? WHERE iddevolucion =?");
            
            ps.setString(1, estado);
            ps.setInt(2, idDevolucion);
            
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
