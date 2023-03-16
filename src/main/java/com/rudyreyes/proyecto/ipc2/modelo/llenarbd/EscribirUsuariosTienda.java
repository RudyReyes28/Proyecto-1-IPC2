/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.llenarbd;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.UsuarioTienda;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class EscribirUsuariosTienda {
    
    public static boolean agregarUsuarioTienda(UsuarioTienda usuario){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO usuario_tienda(codigo_usuario,nombre,codigo_tienda,nombre_usuario,contraseña,correo) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, usuario.getCodigo());
            ps.setString(2, usuario.getNombre());
            ps.setInt(3, usuario.getCodigoTienda());
            ps.setString(4, usuario.getNombreUsuario());
            ps.setString(5, usuario.getContraseña());
            ps.setString(6, usuario.getCorreo());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
