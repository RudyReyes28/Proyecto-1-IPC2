/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.llenarbd;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.SupervisorTienda;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rudy-reyes
 */
public class EscribirSupervisores {
    
    public static boolean agregarSupervisores(SupervisorTienda supervisor){
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("INSERT INTO supervisor_tienda(codigo_supervisor,nombre,nombre_usuario,contraseña,correo) VALUES (?,?,?,?,?)");
            ps.setInt(1, supervisor.getCodigo());
            ps.setString(2, supervisor.getNombre());
            ps.setString(3, supervisor.getNombreUsuario());
            ps.setString(4, supervisor.getContraseña());
            ps.setString(5, supervisor.getCorreo());
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
