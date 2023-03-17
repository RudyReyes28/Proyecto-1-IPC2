/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador;

import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoPedido;
import com.rudyreyes.proyecto.ipc2.modelo.llenarbd.EscribirPedido;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesPedidos;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;


/**
 *
 * @author rudy-reyes
 */
@WebServlet(name = "ServletControladorPedido", urlPatterns = {"/ServletControladorPedido"})
public class ServletControladorPedido extends HttpServlet {

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
                    case "insertar":
                        insertarProducto(request, response);
                        break;
                    case "modificar":

                        break;

                    case "editar":

                        break;

                    case "eliminar":
                        break;

                    default:
                        

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

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        
        int idPedido = (int) sesion.getAttribute("idPedido");
        int codigoProducto = Integer.parseInt(request.getParameter("idProducto"));
        double costoUnitario = ConexionesPedidos.obtenerCostoUnitario(codigoProducto);
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double costoTotal = costoUnitario*cantidad;
        
        ProductoPedido pd = new ProductoPedido(idPedido, codigoProducto, costoUnitario, cantidad, costoTotal);
        
        boolean realizado = EscribirPedido.agregarProductosPedido(pd);
        
        if(realizado){
            //sesion.setAttribute("mostrarLista", productoM);
            accionDefault(request, response);
        }else{
            accionDefault(request, response);
        }
    }
    
    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        ArrayList<ProductoPedido> listadoP = ConexionesPedidos.obtenerProductosPedidos((int) sesion.getAttribute("idPedido"));
        
        sesion.setAttribute("listadoP", listadoP);
        response.sendRedirect("moduloTienda/pedido.jsp");
    }

}
