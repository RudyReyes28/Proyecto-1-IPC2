/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador.servletsSupervisor;

import com.rudyreyes.proyecto.ipc2.modelo.entidades.Devolucion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoDevolucion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoPedido;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesAceptarDevolucion;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesAceptarPedido;
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

@WebServlet(name = "ServletAceptarPedidos", urlPatterns = {"/ServletAceptarPedidos"})
public class ServletAceptarPedidos extends HttpServlet {

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
            if (accion != null) {
                switch (accion) {
                    case "pedido":
                        insertarPedido(request, response);
                        break;
                    case "mostrar":
                        mostrarPedido(request, response);
                        break;

                    case "aceptar":
                        aceptarPedido(request, response);
                        break;
                        
                    case "rechazar":
                        rechazarPedido(request, response);
                        break;

                    default:
                    accionDefault(request, response); 

                }
            } else {
                accionDefault(request, response); 
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

    private void insertarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        int tienda = Integer.parseInt(request.getParameter("tienda"));

        ArrayList<Pedido> pedidos = ConexionesAceptarPedido.obtenerPedidos(tienda);
        sesion.setAttribute("idTienda", tienda);
        sesion.setAttribute("listaPedido", pedidos);

        accionDefault(request, response);
    
    }

    private void aceptarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession sesion = request.getSession();
        int idPedido = (int) sesion.getAttribute("idPedido");
        int idTienda = (int) sesion.getAttribute("idTienda");

        
        ConexionesAceptarPedido.modificarEstadoPedido(idPedido, "SOLICITADO");
        //COSA 1: CAMBIAR LOS VALORES DE LOS PRODUCTOS
        //COSA 2: CAMBIAR EL ESTADO DE LA DEVOLUCION
        try{
            sesion.removeAttribute("listaPedido");
        }catch(Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("vistaUsuarioSupervisor.jsp");

    }

    private void rechazarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession sesion = request.getSession();
        int idPedido = (int) sesion.getAttribute("idPedido");
        int idTienda = (int) sesion.getAttribute("idTienda");

        
        ConexionesAceptarPedido.modificarEstadoPedido(idPedido, "RECHAZADO");
        try{
            sesion.removeAttribute("listaPedido");
        }catch(Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("vistaUsuarioSupervisor.jsp");

    }

    private void mostrarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        HttpSession sesion = request.getSession();
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));

        //CON ESTO OBTENEMOS EL ENVIO  Y LO AGREGAMOS
        ArrayList<ProductoPedido> listadoP = ConexionesPedidos.obtenerProductosPedidos(idPedido);

        if (listadoP != null) {
            sesion.setAttribute("listado", listadoP);
            sesion.setAttribute("idPedido", idPedido);
            request.getRequestDispatcher("/WEB-INF/vistaAceptarPedido/listadoProductos.jsp").forward(request, response);
        } else {
            accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("moduloSupervisor/aceptarPedido.jsp");
    }
    


}
