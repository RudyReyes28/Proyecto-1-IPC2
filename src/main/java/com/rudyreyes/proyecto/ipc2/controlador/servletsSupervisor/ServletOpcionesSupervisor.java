/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador.servletsSupervisor;

import com.rudyreyes.proyecto.ipc2.modelo.Usuario;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesAceptarPedido;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesEnvios;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ServletOpcionesSupervisor", urlPatterns = {"/ServletOpcionesSupervisor"})
public class ServletOpcionesSupervisor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "editar":
                        //editarPedido(request, response);
                        break;
                    case "pedidos":
                        verPedidos(request, response);
                        break;

                    case "reportes":

                        break;

                    default:
                        response.sendRedirect("vistaUsuarioSupervisor.jsp");

                }
            } else {
                response.sendRedirect("vistaUsuarioSupervisor.jsp");
            }
        } catch (Exception e) {
        }

    }

    private void verPedidos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        int codigoUsuario = user.getCodigo();
        
        
        ArrayList<Integer> tiendas = ConexionesAceptarPedido.obtenerTiendas();
        
        sesion.setAttribute("tiendas", tiendas);
        
        response.sendRedirect("moduloSupervisor/aceptarPedido.jsp");
        
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

}
