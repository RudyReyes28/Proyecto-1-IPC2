/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.util;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Envio;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Incidencia;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Producto;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoIncidencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ObtenerReportes {

    /*
    REPORTES TIENDAS
    
    SELECT p.codigo_producto FROM catalogo_tienda as p INNER JOIN tienda as t ON p.codigo_tienda = t.codigo 
WHERE p.existencias < ? AND t.codigo = ? ORDER BY p.existencias ASC ;
    
    SELECT p.idpedido FROM pedido as p INNER JOIN usuario_tienda as u ON p.codigo_usuario = u.codigo_usuario
WHERE p.fecha_pedido BETWEEN ? AND ? AND u.codigo_usuario =? AND p.estado= ?;
    
    
    SELECT COUNT(*) as cantidad_incidencias  FROM incidencia  WHERE idenvio = ? AND estado = 'ACTIVA';
    SELECT COUNT(*) as cantidad_devolucion  FROM devolucion  WHERE idenvio = ? AND estado = 'ACTIVA';
    
    
    
    
    REPORTES DE BODEGAS
    Reporte de envíos generados a una tienda en específico en un intervalo de tiempo.
    
    SELECT idenvios, total_envio FROM envios WHERE codigo_usuario = ? AND codigo_tienda = ? AND fecha_salida BETWEEN ? AND ?
    
    
Reporte de incidencias solucionadas de una tienda en específico en un intervalo de tiempo.
    
    SELECT idincidencia, solucion FROM incidencia WHERE estado = ? AND codigo_tienda = ? AND fecha_incidencia BETWEEN ? AND ?

    
Reporte de devoluciones de una tienda en específico en un intervalo de tiempo por estado.
    SELECT iddevolucion, total FROM devolucion WHERE estado = ? AND codigo_tienda = ? AND fecha_incidencia BETWEEN ? AND ?

    
    
    REPORTES DE ADMINISTRADOR
    Reporte de las 5 tiendas con más pedidos en un intervalo de tiempo por estado
    SELECT p.codigo_tienda, COUNT(*) as cantidad_pedidos FROM pedido p WHERE p.fecha_pedido BETWEEN ? AND ? AND p.estado = ?
GROUP BY p.codigo_tienda ORDER BY cantidad_pedidos DESC LIMIT 5;
    
Reporte de los 5 usuarios con más envíos generados en un intervalo de tiempo
    SELECT codigo_usuario, COUNT(*) AS total_envios FROM envios WHERE fecha_salida BETWEEN ? AND ? 
GROUP BY codigo_usuario 
ORDER BY total_envios DESC 
LIMIT 5;
    
Reporte de los 5 usuarios con más pedidos generados en un intervalo de tiempo

SELECT codigo_usuario, COUNT(*) AS total_pedidos FROM pedido WHERE fecha_pedido BETWEEN ? AND ? GROUP BY codigo_usuario 
ORDER BY total_pedidos DESC 
LIMIT 5;
    
     */
    public static ArrayList<Producto> obtenerProductosMenores(int existencia, int codigoT) {
        ArrayList<Producto> listaP = new ArrayList<>();
        int codigoProducto;
        int cantidad;

        Producto pd = null;

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT p.codigo_producto, p.existencias FROM catalogo_tienda as p "
                    + "INNER JOIN tienda as t ON p.codigo_tienda = t.codigo "
                    + "WHERE p.existencias < ? AND t.codigo = ? ORDER BY p.existencias ASC");

            ps.setInt(1, existencia);
            ps.setInt(2, codigoT);

            rs = ps.executeQuery();

            while (rs.next()) {
                codigoProducto = rs.getInt("codigo_producto");
                cantidad = rs.getInt("existencias");
                pd = new Producto(codigoProducto, cantidad);
                listaP.add(pd);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return listaP;
    }

    public static ArrayList<Pedido> obtenerIdPedidosEnUnTiempo(String fecha1, String fecha2, int codigo, String estado) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Conexion con = new Conexion();
        Pedido pedido = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT p.idpedido, p.total_pedido FROM pedido as p INNER JOIN usuario_tienda as u "
                    + "ON p.codigo_usuario = u.codigo_usuario "
                    + "WHERE p.fecha_pedido BETWEEN ? AND ? AND u.codigo_usuario =? AND p.estado= ?");

            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            ps.setInt(3, codigo);
            ps.setString(4, estado);

            rs = ps.executeQuery();

            while (rs.next()) {
                int idPedido = rs.getInt("idpedido");
                double total = rs.getDouble("total_pedido");
                pedido = new Pedido(idPedido, total);
                pedidos.add(pedido);
            }

            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return pedidos;
    }

    public static ArrayList<Envio> obtenerIdEnviosRecibidos(int tienda) {
        ArrayList<Envio> envios = new ArrayList<>();
        Envio envio = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT e.idenvios FROM envios as e WHERE e.codigo_tienda = ? AND e.estado = ?");
            ps.setInt(1, tienda);
            ps.setString(2, "RECIBIDO");
            rs = ps.executeQuery();

            while (rs.next()) {
                envio = new Envio(rs.getInt("idenvios"));
                envios.add(envio);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return envios;
    }

    public static void obtenerCantIncidencia(Envio envio) {

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT COUNT(*) as cantidad_incidencias  FROM incidencia  WHERE idenvio = ? AND estado = ?;");
            ps.setInt(1, envio.getIdEnvio());
            ps.setString(2, "ACTIVA");
            rs = ps.executeQuery();

            while (rs.next()) {
                int cant = rs.getInt("cantidad_incidencias");
                envio.setIncidencias(cant);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }
    }

    public static void obtenerCantDevolucion(Envio envio) {

        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT COUNT(*) as cantidad_devolucion  FROM devolucion  WHERE idenvio = ? AND estado = ?");
            ps.setInt(1, envio.getIdEnvio());
            ps.setString(2, "ACTIVA");
            rs = ps.executeQuery();

            while (rs.next()) {
                int cant = rs.getInt("cantidad_devolucion");
                envio.setDevoluciones(cant);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

    }

    public static ArrayList<Envio> obtenerEnviosEnUnTiempo(String fecha1, String fecha2, int codigoU, int codigoT) {
        ArrayList<Envio> envios = new ArrayList<>();
        Conexion con = new Conexion();
        Envio envio = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idenvios, total_envio FROM envios WHERE codigo_usuario = ? "
                    + "AND codigo_tienda = ? AND fecha_salida BETWEEN ? AND ?");

            ps.setInt(1, codigoU);
            ps.setInt(2, codigoT);
            ps.setString(3, fecha1);
            ps.setString(4, fecha2);

            rs = ps.executeQuery();

            while (rs.next()) {
                int idEnvio = rs.getInt("idenvios");
                double total = rs.getDouble("total_envio");
                envio = new Envio(idEnvio, total);
                envios.add(envio);
            }

            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return envios;
    }

    public static ArrayList<Incidencia> obtenerIncidenciasTiempo(String fecha1, String fecha2, int codigoT) {
        ArrayList<Incidencia> incidencias = new ArrayList<>();
        Incidencia incidencia = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT idincidencia, solucion FROM incidencia WHERE estado = ? AND codigo_tienda = ? "
                    + "AND fecha_incidencia BETWEEN ? AND ?");

            ps.setString(1, "SOLUCIONADA");
            ps.setInt(2, codigoT);
            ps.setString(3, fecha1);
            ps.setString(4, fecha2);

            rs = ps.executeQuery();

            while (rs.next()) {
                int idIncidencia = rs.getInt("idincidencia");
                String solucion = rs.getString("solucion");
                incidencia = new Incidencia(idIncidencia, solucion);
                incidencias.add(incidencia);
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return incidencias;
    }

    public static ArrayList<Pedido> obtenerCantTiendasP(String fecha1, String fecha2, String estado) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Conexion con = new Conexion();
        Pedido pedido = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT p.codigo_tienda, COUNT(*) as cantidad_pedidos FROM pedido as p "
                    + "WHERE p.fecha_pedido BETWEEN ? AND ? AND p.estado = ? "
                    + "GROUP BY p.codigo_tienda ORDER BY cantidad_pedidos DESC LIMIT 5");

            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            ps.setString(3, estado);

            rs = ps.executeQuery();

            while (rs.next()) {
                int idTienda = rs.getInt("codigo_tienda");
                int cantidad = rs.getInt("cantidad_pedidos");
                pedido = new Pedido(idTienda, cantidad);
                pedidos.add(pedido);
            }

            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return pedidos;
    }
}
