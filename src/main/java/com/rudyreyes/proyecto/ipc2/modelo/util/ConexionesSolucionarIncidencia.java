/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.util;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Incidencia;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoEnviado;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoIncidencia;
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
public class ConexionesSolucionarIncidencia {
    
    public static ArrayList<Integer> obtenerIdIncidencia(int codigoTienda) {
        ArrayList<Integer> idIncidencia = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idincidencia FROM incidencia WHERE codigo_tienda = ? AND estado =?");
            
            ps.setInt(1, codigoTienda);
            ps.setString(2, "ACTIVA");
            
            rs = ps.executeQuery();

            while (rs.next()) {
                idIncidencia.add(rs.getInt("idincidencia"));
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return idIncidencia;
    }
    
    
    public static ArrayList<Incidencia> obtenerIncidencias(int idTienda) {
        ArrayList<Incidencia> incidencias = new ArrayList<>();

        java.sql.Date fechaD = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int idIncidencia;
        int codigoUsuario;
        String fecha;
        Incidencia pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idincidencia, codigo_usuario, fecha_incidencia"
                    + " from incidencia WHERE codigo_tienda =? AND estado = ?");

            ps.setInt(1, idTienda);
            ps.setString(2, "ACTIVA");
            rs = ps.executeQuery();

            while (rs.next()) {
                idIncidencia = rs.getInt("idincidencia");
                codigoUsuario = rs.getInt("codigo_usuario");
                fechaD = rs.getDate("fecha_incidencia");
                fecha = dateFormat.format(fechaD);

                pd = new Incidencia(idIncidencia, codigoUsuario, fecha);
                incidencias.add(pd);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return incidencias;
    }
    
    public static ArrayList<ProductoIncidencia> obtenerProductosIncidencias(int idIncidencia) {
        ArrayList<ProductoIncidencia> listaP = new ArrayList<>();
        int codigoProducto;
        int cantidad;
        String motivo;
        
        ProductoIncidencia pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_producto, cantidad_afectada,"
                    + "motivo_incidencia FROM productos_incidencias WHERE idincidencia = " + idIncidencia + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                codigoProducto = rs.getInt("codigo_producto");
                cantidad = rs.getInt("cantidad_afectada");
                motivo = rs.getString("motivo_incidencia");
                pd = new ProductoIncidencia(codigoProducto, cantidad, motivo);
                listaP.add(pd);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }
        
        return listaP;
    }
    
    public static boolean agregarSolucion(String solucion, int idIncidencia) {
        Conexion con = new Conexion();
        PreparedStatement ps = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE incidencia SET solucion=? WHERE idincidencia=?");

            ps.setString(1, solucion);
            ps.setInt(2, idIncidencia);
            
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
    
    public static boolean modificarEstadoIncidencia(int idIncidencia,String estado){
       
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("UPDATE incidencia SET estado=? WHERE idincidencia =?");
            
            ps.setString(1, estado);
            ps.setInt(2, idIncidencia);
            
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
