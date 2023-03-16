/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.llenarbd;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author rudy-reyes
 */
public class ConectarAdminTienda {
    
    public static void conectarAdminTiendas(){
        ArrayList<Integer> codigoAdmin = null;
        ArrayList<Integer> codigoUsuario = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        codigoAdmin = obtenerCodigoAdmin();
        codigoUsuario = obtenerCodigoUsuario();
        
        for (int codigoA : codigoAdmin) {
           
                
                for (int codigoU : codigoUsuario) {
                    try {
                        Connection conexion = con.getConnection();
                        ps = conexion.prepareStatement("INSERT INTO admin_tienda(codigo_admin,codigo_usuarioT) VALUES (?,?)");
                        ps.setInt(1, codigoA);
                        ps.setInt(2, codigoU);
                        ps.executeUpdate();
                        ps.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            
        }
    }
    
    private static ArrayList<Integer> obtenerCodigoUsuario(){
        ArrayList<Integer> codigoU = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_usuario from usuario_tienda;");
            rs = ps.executeQuery();
            
            while(rs.next()){
                codigoU.add(rs.getInt("codigo_usuario"));
            }
            
            conexion.close();
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return codigoU;
    }
    
    private static ArrayList<Integer> obtenerCodigoAdmin(){
        ArrayList<Integer> codigoU = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_admin FROM administrador;");
            rs = ps.executeQuery();
            
            while(rs.next()){
                codigoU.add(rs.getInt("codigo_admin"));
            }
            
            conexion.close();
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return codigoU;
    }
    
}
