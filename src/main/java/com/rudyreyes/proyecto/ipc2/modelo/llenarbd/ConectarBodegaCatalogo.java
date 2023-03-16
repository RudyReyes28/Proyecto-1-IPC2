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
public class ConectarBodegaCatalogo {
    
    
    //NECESITO EXTRAER TODOS LOS ID DE LOS PRODUCTOS DE LA TABLA CATALOGO
    // EXTRAER TODOS LOS ID DE USUARIO DE LA TABLA USUARIO BODEGA
    //REVISAR SI AUN NO ESTÁN CONECTADOS
    
    public static void conectarBodegaCatalogo(){
        ArrayList<Integer> codigoP = null;
        ArrayList<Integer> codigoU = null;
        ArrayList<Integer> codigoUB = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        codigoP = obtenerCodigoProducto();
        codigoU = obtenerCodigoUsuario();
        codigoUB = obtenerCodigoUnion();
        
        for (int codigoUsuario : codigoU) {
            if (!revisarConexion(codigoUsuario, codigoUB)) {
                
                for (int codigoProducto : codigoP) {
                    try {
                        Connection conexion = con.getConnection();
                        ps = conexion.prepareStatement("INSERT INTO union_ub(codigo_usuario,codigo_producto) VALUES (?,?)");
                        ps.setInt(1, codigoUsuario);
                        ps.setInt(2, codigoProducto);
                        ps.executeUpdate();
                        ps.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
    }
    
    private  static ArrayList<Integer> obtenerCodigoProducto(){
        ArrayList<Integer> codigoP = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_producto FROM catalogo_bodega;");
            rs = ps.executeQuery();
            
            while(rs.next()){
                codigoP.add(rs.getInt("codigo_producto"));
            }
            
            conexion.close();
            
        } catch (Exception e) {
            System.err.println(e);
            
        }
        
        return codigoP;
    }
    
    private static ArrayList<Integer> obtenerCodigoUsuario(){
        ArrayList<Integer> codigoU = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_usuario FROM usuario_bodega;");
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
    
    private static ArrayList<Integer> obtenerCodigoUnion(){
        ArrayList<Integer> codigoU = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo_usuario FROM union_ub;");
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
    
    private static boolean revisarConexion(int codigoUsuario, ArrayList<Integer> codigoUB){
        
        if(codigoUB!=null){
            for (int codigo : codigoUB) {
                if (codigoUsuario == codigo) {
                    return true;
                }
            }
        }
        return false;
    }
}
