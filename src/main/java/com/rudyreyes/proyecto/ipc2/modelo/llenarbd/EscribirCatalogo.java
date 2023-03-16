/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.llenarbd;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class EscribirCatalogo {
    
    public static boolean agregarCatalogo(Producto producto){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO catalogo_bodega(codigo_producto,nombre,costo,precio,existencias) values (?,?,?,?,?)");
            ps.setInt(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getCosto());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getExistencias());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
