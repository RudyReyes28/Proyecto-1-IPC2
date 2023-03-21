/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.util;

import com.rudyreyes.proyecto.ipc2.modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author rudy-reyes
 */
public class ConexionesAdmin {
    
    public static ArrayList<Integer> obtenerTiendas() {
        ArrayList<Integer> tiendas = new ArrayList<>();
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("SELECT codigo from tienda;");
            
            
            rs = ps.executeQuery();

            while (rs.next()) {
                tiendas.add(rs.getInt("codigo"));
            }

            conexion.close();

        } catch (Exception e) {
            System.err.println(e);

        }

        return tiendas;
    }
    
    
}
