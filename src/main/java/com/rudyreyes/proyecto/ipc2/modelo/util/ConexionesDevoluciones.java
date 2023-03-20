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

public class ConexionesDevoluciones {
    
    public static boolean agregarDevolucion(Devolucion devolucion) {
        Conexion con = new Conexion();
        PreparedStatement ps = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO devolucion(idenvio,codigo_tienda, codigo_usuario, fecha_devolucion,estado) VALUES (?,?,?,?,?)");

            ps.setInt(1, devolucion.getIdEnvio());
            ps.setInt(2, devolucion.getCodigoTienda());
            ps.setInt(3, devolucion.getCodigoUsuario());
            ps.setString(4, devolucion.getFechaDevolucion());
            ps.setString(5, devolucion.getEstado());
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
    public static int obtenerIdDevolucion(int codigoUsuario) {
        int idDevolucion = -1;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT iddevolucion FROM devolucion WHERE codigo_usuario = " + codigoUsuario + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                idDevolucion = rs.getInt("iddevolucion");
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return idDevolucion;
    }
    
    public static boolean agregarProductosDevolucion(ProductoDevolucion producto) {

        Conexion con = new Conexion();
        PreparedStatement ps = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO productos_devueltos(iddevolucion,codigo_producto, costo_unitario,cantidad, costo_total, motivo) VALUES (?,?,?,?,?,?)");
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
    
    public static boolean modificarTotalDevolucion(int idDevolucion,double total){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE devolucion SET total=? WHERE iddevolucion =?");
            
            ps.setDouble(1, total);
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
