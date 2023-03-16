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
public class ConectarSupervisorTiendas {
    
    public static void conectarSupervisorTiendas(){
        ArrayList<Integer> codigoSupervisor = null;
        ArrayList<Integer> codigoUsuario = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        codigoSupervisor = obtenerCodigoSupervisor();
        codigoUsuario = obtenerCodigoUsuario();
        
        for (int codigoS : codigoSupervisor) {
           
                
                for (int codigoU : codigoUsuario) {
                    try {
                        Connection conexion = con.getConnection();
                        ps = conexion.prepareStatement("INSERT INTO supervisar_tiendas(codigo_supervisor,codigo_usuario) VALUES (?,?)");
                        ps.setInt(1, codigoS);
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
            ps = conexion.prepareStatement("SELECT u.codigo_usuario FROM usuario_tienda as u INNER JOIN tienda as t on u.codigo_tienda = t.codigo where t.tipo_tienda = 'supervisada';");
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
    
    private static ArrayList<Integer> obtenerCodigoSupervisor(){
        ArrayList<Integer> codigoU = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_supervisor FROM supervisor_tienda;");
            rs = ps.executeQuery();
            
            while(rs.next()){
                codigoU.add(rs.getInt("codigo_supervisor"));
            }
            
            conexion.close();
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return codigoU;
    }
    
    
 
    
}
