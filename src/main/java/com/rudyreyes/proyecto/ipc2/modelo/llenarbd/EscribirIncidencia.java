/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.llenarbd;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Incidencia;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoIncidencia;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class EscribirIncidencia {
    
    public static boolean agregarIncidencia(Incidencia incidencia){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO incidencia(idincidencia, codigo_tienda,codigo_usuario,fecha_incidencia,solucion,estado) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, incidencia.getIdIncidencia());
            ps.setInt(2, incidencia.getCodigoTienda());
            ps.setInt(3, incidencia.getCodigoUsuario());
            ps.setString(4, incidencia.getFechaIncidencia());
            ps.setString(5, incidencia.getSolucion());
            ps.setString(6, incidencia.getEstado());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean agregarProductoIncidencia(ProductoIncidencia producto){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO productos_incidencias(idincidencia,codigo_producto,cantidad_afectada, motivo_incidencia) VALUES (?,?,?,?)");
            ps.setInt(1, producto.getIdIncidencia());
            ps.setInt(2, producto.getCodigo());
            ps.setInt(3, producto.getCantidad());
            ps.setString(4, producto.getMotivo());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
