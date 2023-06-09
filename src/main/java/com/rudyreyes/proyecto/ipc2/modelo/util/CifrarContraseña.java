/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.modelo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CifrarContraseña {

    public static String hash(String password) {
        try {
            // Obtiene una instancia del algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Cifra la contraseña
            byte[] encodedHash = digest.digest(password.getBytes());
            // Convierte la salida en hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
