/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.llenarbd;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.UsuarioBodega;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class EscribirEncargadoBodega {
    
    public static boolean agregarEncargadoBodega(UsuarioBodega encargado){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO usuario_bodega(codigo_usuario,nombre,nombre_usuario,contraseña,correo) VALUES (?,?,?,?,?)");
            ps.setInt(1, encargado.getCodigo());
            ps.setString(2, encargado.getNombre());
            ps.setString(3, encargado.getNombreUsuario());
            ps.setString(4, encargado.getContraseña());
            ps.setString(5, encargado.getCorreo());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean conectarTiendaBodega(int codigoBodega, int codigoTienda){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO conexion_tienda_bodega(codigo_tienda,codigo_bodega) VALUES (?,?)");
            ps.setInt(1, codigoTienda);
            ps.setInt(2, codigoBodega);
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
