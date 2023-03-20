/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.util;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Incidencia;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoEnviado;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoIncidencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author rudy-reyes
 */
public class ConexionesIncidencias {

    public static ArrayList<Integer> obtenerIdEnvios(int codigoTienda) {
        ArrayList<Integer> listaEnvios = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idenvios FROM envios WHERE codigo_tienda = ? AND estado = ?");
            ps.setInt(1, codigoTienda);
            ps.setString(2, "RECIBIDO");

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
        int cantidad;
        ProductoEnviado pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_producto, cantidad"
                    + " FROM productos_enviados WHERE idenvio = " + idEnvio + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                codigoProducto = rs.getInt("codigo_producto");
                cantidad = rs.getInt("cantidad");
                pd = new ProductoEnviado(codigoProducto, cantidad);
                listaP.add(pd);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return listaP;
    }

    public static boolean agregarIncidencia(Incidencia incidencia) {
        Conexion con = new Conexion();
        PreparedStatement ps = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO incidencia(idenvio,codigo_tienda, codigo_usuario, fecha_incidencia,estado) VALUES (?,?,?,?,?)");

            ps.setInt(1, incidencia.getIdEnvio());
            ps.setInt(2, incidencia.getCodigoTienda());
            ps.setInt(3, incidencia.getCodigoUsuario());
            ps.setString(4, incidencia.getFechaIncidencia());
            ps.setString(5, incidencia.getEstado());
            ps.executeUpdate();
            ps.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static int obtenerIdIncidencia(int codigoUsuario) {
        int idIncidencia = -1;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idincidencia FROM incidencia WHERE codigo_usuario = " + codigoUsuario + ";");
            rs = ps.executeQuery();

            while (rs.next()) {
                idIncidencia = rs.getInt("idincidencia");
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return idIncidencia;
    }
    
    public static boolean agregarProductosIncidencia(ProductoIncidencia producto) {

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
