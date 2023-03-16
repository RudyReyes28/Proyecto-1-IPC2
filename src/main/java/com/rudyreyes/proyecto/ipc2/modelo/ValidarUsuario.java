/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author rudy-reyes
 */
public class ValidarUsuario {
    
    public Usuario validarUsuario(String username, String contraseña){
        Usuario usuario = null;
        
        if(encontrarAdmin(username, contraseña) != null ){
            return usuario = encontrarAdmin(username, contraseña);
        }else if(encontrarUsuarioTienda(username, contraseña) != null ){
            return usuario = encontrarUsuarioTienda(username, contraseña);
        }else if(encontrarUsuarioBodega(username, contraseña) != null ){
            return usuario = encontrarUsuarioBodega(username, contraseña);
        }else if(encontrarUsuarioSupervisor(username, contraseña) != null ){
            return usuario = encontrarUsuarioSupervisor(username, contraseña);
        }else{
            return usuario;
        }
    }
    
    private Usuario encontrarAdmin(String username, String contraseña){
        Usuario usuario = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_admin, nombre FROM administrador "
                    + "WHERE nombre_usuario = '"+username+"' AND contraseña = '"+contraseña+"';");
            rs = ps.executeQuery();
            
            while(rs.next()){
                usuario = new Usuario(rs.getInt("codigo_admin"), rs.getString("nombre"), "ADMIN");
            }
            
            conexion.close();
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return usuario;
    }
    
    private Usuario encontrarUsuarioTienda(String username, String contraseña){
        Usuario usuario = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_usuario, nombre FROM usuario_tienda "
                    + "WHERE nombre_usuario = '"+username+"' AND contraseña = '"+contraseña+"';");
            rs = ps.executeQuery();
            
            while(rs.next()){
                usuario = new Usuario(rs.getInt("codigo_usuario"), rs.getString("nombre"), "TIENDA");

            }
            
            conexion.close();
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return usuario;
    }
    
    private Usuario encontrarUsuarioBodega(String username, String contraseña){
        Usuario usuario = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_usuario, nombre FROM usuario_bodega "
                    + "WHERE nombre_usuario = '"+username+"' AND contraseña = '"+contraseña+"';");
            rs = ps.executeQuery();
            
            while(rs.next()){
                usuario = new Usuario(rs.getInt("codigo_usuario"), rs.getString("nombre"), "BODEGA");

            }
            
            conexion.close();
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return usuario;
    }
    
    private Usuario encontrarUsuarioSupervisor(String username, String contraseña){
        Usuario usuario = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_supervisor, nombre FROM supervisor_tienda "
                    + "WHERE nombre_usuario = '"+username+"' AND contraseña = '"+contraseña+"';");
            rs = ps.executeQuery();
            
            while(rs.next()){
                usuario = new Usuario(rs.getInt("codigo_supervisor"), rs.getString("nombre"), "SUPERVISOR");

            }
            
            conexion.close();
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        return usuario;
    }
    
}
