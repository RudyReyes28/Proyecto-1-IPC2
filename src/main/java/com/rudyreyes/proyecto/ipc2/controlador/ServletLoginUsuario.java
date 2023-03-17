/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador;

import com.rudyreyes.proyecto.ipc2.modelo.Usuario;
import com.rudyreyes.proyecto.ipc2.modelo.ValidarUsuario;
import com.rudyreyes.proyecto.ipc2.modelo.util.CifrarContraseña;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author rudy-reyes
 */
@WebServlet(name = "ServletLoginUsuario", urlPatterns = {"/ServletLoginUsuario"})
public class ServletLoginUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");
        
        try {
            if(accion!=null){
                switch(accion){
                    case "verificar":
                        verificar(request, response);
                        break;
                    case "cerrar":
                        cerrarSesion(request, response);
                    default:
                        response.sendRedirect("index.jsp");
                        
                }
            }else{
                response.sendRedirect("index.jsp");
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

    private void verificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion;
        ValidarUsuario validacion = new ValidarUsuario();
        Usuario usuario;
        
        String contraseñaCifrada = CifrarContraseña.hash(request.getParameter("contraseña"));
        System.out.println(contraseñaCifrada);
        usuario = validacion.validarUsuario(request.getParameter("usuario"), contraseñaCifrada );
        
        if(usuario != null && usuario.getCargo().equals("ADMIN")){
            sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            //this.getServletConfig().getServletContext().getRequestDispatcher("vistaAdmin.jsp").forward(request, response);
            response.sendRedirect("vistaAdmin.jsp");
            
        }else if(usuario != null && usuario.getCargo().equals("TIENDA")){
            sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            //this.getServletConfig().getServletContext().getRequestDispatcher("vistaUsuarioTienda.jsp").forward(request, response);
            response.sendRedirect("vistaUsuarioTienda.jsp");
            
        }else if(usuario != null && usuario.getCargo().equals("BODEGA")){
            sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            //this.getServletConfig().getServletContext().getRequestDispatcher("vistaUsuarioBodega.jsp").forward(request, response);
            response.sendRedirect("vistaUsuarioBodega.jsp");
        }else if(usuario != null && usuario.getCargo().equals("SUPERVISOR")){
            sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            //this.getServletConfig().getServletContext().getRequestDispatcher("vistaUsuarioSupervisor.jsp").forward(request, response);
            response.sendRedirect("vistaUsuarioSupervisor.jsp");
            
        }else{
            request.setAttribute("error", "No se pudo iniciar sesion");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", null);
        sesion.invalidate();
        response.sendRedirect("index.jsp");
    }


}
