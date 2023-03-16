/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.llenarbd;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class EscribirAdmin {
    
    public static boolean agregarAdmin(Administrador admin){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO administrador(codigo_admin,nombre,nombre_usuario,contraseña) VALUES (?,?,?,?)");
            ps.setInt(1, admin.getCodigo());
            ps.setString(2, admin.getNombre());
            ps.setString(3, admin.getNombreUsuario());
            ps.setString(4, admin.getContraseña());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
