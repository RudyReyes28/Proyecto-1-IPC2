/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador;

import com.rudyreyes.proyecto.ipc2.modelo.LecturaJSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author rudy-reyes
 */
@WebServlet(name = "ServletDescargarArchivo", urlPatterns = {"/ServletDescargarArchivo"})
@MultipartConfig
public class ServletDescargarArchivo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("archivo");
        String inputFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        guardarArchivo(filePart, inputFileName);
        
        response.sendRedirect("index.jsp");
        
    }

    private void guardarArchivo(Part filePart, String nombreArchivo) {
        File ruta = new File("/tmp");
        File file = new File(ruta, nombreArchivo);

        try ( InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            LecturaJSON.leerArchivo(nombreArchivo);
            System.out.println("Archivo " + nombreArchivo + " guardado");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
