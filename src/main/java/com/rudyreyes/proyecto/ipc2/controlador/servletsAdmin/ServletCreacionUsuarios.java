/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador.servletsAdmin;

import com.rudyreyes.proyecto.ipc2.modelo.entidades.SupervisorTienda;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.UsuarioBodega;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.UsuarioTienda;
import com.rudyreyes.proyecto.ipc2.modelo.llenarbd.EscribirEncargadoBodega;
import com.rudyreyes.proyecto.ipc2.modelo.llenarbd.EscribirSupervisores;
import com.rudyreyes.proyecto.ipc2.modelo.llenarbd.EscribirUsuariosTienda;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletCreacionUsuarios", urlPatterns = {"/ServletCreacionUsuarios"})
public class ServletCreacionUsuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");

        try {
            if (accion != null) {
                switch (accion) {
                    case "uTienda":
                        crearUsuarioT(request, response);
                        break;
                    case "uBodega":
                        crearUsuarioB(request, response);
                        break;

                    case "uSupervisor":
                        crearUsuarioS(request, response);
                        break;
                        
                    default:
                    accionDefault(request, response); 

                }
            } else {
                accionDefault(request, response); 
            }
        } catch (Exception e) {
            accionDefault(request, response);
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

    private void crearUsuarioB(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        int codigoUsuario = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre") ;
        String [] tiendas = request.getParameterValues("tienda");
        String nombreUsuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contrasenia");
        String correo  = request.getParameter("correo");
        
        UsuarioBodega usuario = new UsuarioBodega(codigoUsuario, nombre, nombreUsuario, contraseña, correo);
        EscribirEncargadoBodega.agregarEncargadoBodega(usuario);
        
        for(String tienda: tiendas){
            EscribirEncargadoBodega.conectarTiendaBodega(codigoUsuario, Integer.parseInt(tienda));
        }
        
        response.sendRedirect("vistaAdmin.jsp");
        
        
    }

    private void crearUsuarioT(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        int codigoUsuario = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre") ;
        int codigoTienda = Integer.parseInt(request.getParameter("tienda"));
        String nombreUsuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contrasenia");
        String correo  = request.getParameter("correo");
        
        UsuarioTienda usuario = new UsuarioTienda(codigoUsuario, nombre, codigoTienda, nombreUsuario, contraseña, correo);
        
        EscribirUsuariosTienda.agregarUsuarioTienda(usuario);
        
        response.sendRedirect("vistaAdmin.jsp");
    }

    private void crearUsuarioS(HttpServletRequest request, HttpServletResponse response) throws IOException {
       int codigoUsuario = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre") ;
        String nombreUsuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contrasenia");
        String correo  = request.getParameter("correo");
        
        SupervisorTienda supervisor = new SupervisorTienda(codigoUsuario, nombre, nombreUsuario, contraseña, correo);
        
        EscribirSupervisores.agregarSupervisores(supervisor);
        accionDefault(request, response);
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("vistaAdmin.jsp");
    }

}
