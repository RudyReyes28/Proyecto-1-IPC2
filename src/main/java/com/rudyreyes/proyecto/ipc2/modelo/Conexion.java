/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author rudy-reyes
 */
public class Conexion {
    
    public static final String URL = "jdbc:mysql://localhost:3306/tienda_conveniencia?autoReconnect=true&useSSL=false";
    public static final String usuario = "root";
    public static final String contraseña = "rudyreyes123";
    
    public Connection getConnection(){
        Connection conexion = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, usuario, contraseña);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return conexion;
    }
}
