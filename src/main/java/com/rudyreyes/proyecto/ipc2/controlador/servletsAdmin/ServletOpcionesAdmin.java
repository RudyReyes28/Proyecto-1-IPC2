/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador.servletsAdmin;

import com.rudyreyes.proyecto.ipc2.modelo.Usuario;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesAdmin;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesPedidos;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ServletOpcionesAdmin", urlPatterns = {"/ServletOpcionesAdmin"})
public class ServletOpcionesAdmin extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "crearT":
                        crearUsuarioTienda(request, response);
                        break;
                    case "crearB":
                        crearUsuarioBodega(request, response);
                        break;

                    case "crearS":
                        crearUsuarioSupervisor(request, response);
                        break;
                        
                    case "reportes":
                        iniciarReportes(request, response);
                        break;
                        
                    default:
                    //accionDefault(request, response); 

                }
            } else {
                //accionDefault(request, response); 
            }
        } catch (Exception e) {
            //accionDefault(request, response);
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

    private void crearUsuarioTienda(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //OBTENER EL LISTADO DE TIENDAS
        HttpSession sesion = request.getSession();
        ArrayList<Integer> tiendas =ConexionesAdmin.obtenerTiendas();
        
        sesion.setAttribute("tiendas", tiendas);
        
        response.sendRedirect("moduloAdmin/crearUsuarioTienda.jsp");
        
    }

    private void crearUsuarioBodega(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession sesion = request.getSession();
        ArrayList<Integer> tiendas =ConexionesAdmin.obtenerTiendas();
        
        sesion.setAttribute("tiendas", tiendas);
        
        response.sendRedirect("moduloAdmin/crearUsuarioBodega.jsp");
        
    }

    private void crearUsuarioSupervisor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.sendRedirect("moduloAdmin/crearUsuarioSupervisor.jsp");
        
    }

    private void iniciarReportes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        
        
        response.sendRedirect("moduloAdmin/reportes.jsp");
    }

}
