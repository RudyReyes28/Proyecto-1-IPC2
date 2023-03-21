/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador.servletsBodega;

import com.rudyreyes.proyecto.ipc2.modelo.Usuario;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesEnvios;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesIncidencias;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesPedidos;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author rudy-reyes
 */
@WebServlet(name = "ServletOpcionesBodega", urlPatterns = {"/ServletOpcionesBodega"})
public class ServletOpcionesBodega extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "envio":
                        iniciarEnvio(request, response);
                        break;
                    case "incidencias":
                        iniciarIncidencias(request, response);
                        break;

                    case "devoluciones":
                        iniciarDevoluciones(request, response);
                        break;

                    case "productos":

                        break;

                    case "reportes":
                        iniciarReportes(request, response);
                        break;

                    default:
                        response.sendRedirect("vistaUsuarioBodega.jsp");

                }
            } else {
                response.sendRedirect("vistaUsuarioBodega.jsp");
            }
        } catch (Exception e) {
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void iniciarEnvio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        int codigoUsuario = user.getCodigo();
        ArrayList <Integer> tiendas = ConexionesEnvios.obtenerTiendas(codigoUsuario);
        
        sesion.setAttribute("tiendas", tiendas);
        
        response.sendRedirect("moduloBodega/Envio.jsp");

    }

    private void iniciarIncidencias(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        int codigoUsuario = user.getCodigo();
        
        //OBTENER LOS ID DE LOS INCIDENCIAS CON ESTADO "ACTIVA"
        ArrayList<Integer> tiendas = ConexionesEnvios.obtenerTiendas(codigoUsuario);
        
        sesion.setAttribute("tiendas", tiendas);
        
        response.sendRedirect("moduloBodega/solucionarIncidencia.jsp");
        
    }

    private void iniciarDevoluciones(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        int codigoUsuario = user.getCodigo();
        
        
        ArrayList<Integer> tiendas = ConexionesEnvios.obtenerTiendas(codigoUsuario);
        
        sesion.setAttribute("tiendas", tiendas);
        
        response.sendRedirect("moduloBodega/aceptarDevolucion.jsp");
        

    }

    private void iniciarReportes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        int codigoUsuario = user.getCodigo();
        
        
        ArrayList<Integer> tiendas = ConexionesEnvios.obtenerTiendas(codigoUsuario);
        
        sesion.setAttribute("tiendas", tiendas);
        sesion.setAttribute("codigoUsuario", codigoUsuario);
        
        response.sendRedirect("moduloBodega/reportes.jsp");
    }

}
