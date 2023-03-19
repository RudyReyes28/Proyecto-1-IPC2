/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador.servletsBodega;

import com.rudyreyes.proyecto.ipc2.modelo.Usuario;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Envio;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoEnviado;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoPedido;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesEnvios;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesPedidos;
import jakarta.enterprise.inject.spi.Producer;
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
@WebServlet(name = "ServletControladorEnvios", urlPatterns = {"/ServletControladorEnvios"})
public class ServletControladorEnvios extends HttpServlet {

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
                    case "verPedidos":
                        insertarPedidos(request, response);
                        break;
                    case "mostrar":
                        mostrarPedido(request, response);
                        break;

                    case "insertar":
                        insertarPedido(request, response);
                        break;

                    case "modificar":
                        modificarProducto(request, response);
                        break;

                    case "editar":
                        editarProducto(request, response);
                        break;

                    case "eliminar":
                        eliminarProducto(request, response);
                        break;

                    case "enviar":
                        enviarPedido(request, response);
                        break;

                    default:
                    accionDefaultEnvio(request, response); 

                }
            } else {
                accionDefaultEnvio(request, response); 
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

    private void insertarPedidos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int tienda = Integer.parseInt(request.getParameter("tienda"));

        ArrayList<Pedido> pedidos = ConexionesEnvios.obtenerPedidos(tienda);
        sesion.setAttribute("idTienda", tienda);
        sesion.setAttribute("ListaPedidos", pedidos);

        accionDefault(request, response);
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("moduloBodega/Envio.jsp");
    }

    private void accionDefaultEnvio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int idEnvio = (int) sesion.getAttribute("idEnvio");
        ArrayList<ProductoEnviado> listadoProductos = ConexionesEnvios.obtenerProductosEnviados(idEnvio);
        sesion.setAttribute("listado", listadoProductos);
        response.sendRedirect("moduloBodega/envioProductos.jsp");
    }

    private void mostrarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));

        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        int codigoUsuario = user.getCodigo();
        int codigoTienda = (int) sesion.getAttribute("idTienda");
        LocalDate currentDate = LocalDate.now();
        String fecha = currentDate.toString();
        String estado = "DESPACHADO";

        Envio envio = new Envio(codigoTienda, codigoUsuario, fecha, estado);
        boolean realizado = ConexionesEnvios.agregarEnvio(envio);

        if (realizado) {
            int idEnvio = ConexionesEnvios.obtenerIdEnvio(codigoUsuario);
            //obtener todos los productos del pedido

            ArrayList<ProductoPedido> listadoP = ConexionesPedidos.obtenerProductosPedidos(idPedido);
            //agregar los pedidos al envio
            for (ProductoPedido productos : listadoP) {
                ConexionesEnvios.agregarProductosEnvio(productos, idEnvio);
            }

            //PEDIR DE NUEVO LOS PRODUCTOS DEL ENVIO
            ArrayList<ProductoEnviado> listadoProductos = ConexionesEnvios.obtenerProductosEnviados(idEnvio);

            //PEDIMOS EL CATALOGO DE PRODUCTOS DE LA TIENDA
            ArrayList<Integer> catalogoProductos = ConexionesPedidos.obtenerIdProductos(codigoTienda);

            if (listadoProductos != null) {
                sesion.setAttribute("idEnvio", idEnvio);
                sesion.setAttribute("listado", listadoProductos);
                sesion.setAttribute("catalogo", catalogoProductos);
                sesion.setAttribute("idPedido", idPedido);
                
                response.sendRedirect("moduloBodega/envioProductos.jsp");
            } else {
                response.sendRedirect("moduloBodega/envio.jsp");
            }
        }

    }

    private void insertarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();

        int idEnvio = (int) sesion.getAttribute("idEnvio");
        int codigoProducto = Integer.parseInt(request.getParameter("idProducto"));
        double costoUnitario = ConexionesPedidos.obtenerCostoUnitario(codigoProducto);
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double costoTotal = costoUnitario * cantidad;

        //ProductoPedido pd = new ProductoPedido(idPedido, codigoProducto, costoUnitario, cantidad, costoTotal);
        ProductoEnviado pd = new ProductoEnviado(idEnvio, codigoProducto, costoUnitario, cantidad, costoTotal);
        boolean realizado = ConexionesEnvios.agregarProductosEnvio(pd);

        if (realizado) {
            accionDefaultEnvio(request, response);
        } else {
            accionDefaultEnvio(request, response);
        }

    }

    private void modificarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEnvio = Integer.parseInt(request.getParameter("idEnvio"));
        int idProducto = Integer.parseInt(request.getParameter("codigoP"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double costoTotal = Double.parseDouble(request.getParameter("costoU")) * cantidad;
        ProductoEnviado pd = new ProductoEnviado(idEnvio, idProducto, cantidad, costoTotal);

        boolean realizado = ConexionesEnvios.modificarProductoEnvio(pd);

        accionDefaultEnvio(request, response);

    }

    private void editarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEnvio = Integer.parseInt(request.getParameter("idEnvio"));
        int codigoProducto = Integer.parseInt(request.getParameter("codigoP"));

        ProductoEnviado productoP = ConexionesEnvios.encontrarProductoEnvio(idEnvio, codigoProducto);

        
        
        request.setAttribute("productoP", productoP);

        request.getRequestDispatcher("/WEB-INF/vistaEnvio/editarProducto.jsp").forward(request, response);

    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEnvio = Integer.parseInt(request.getParameter("idEnvio"));
        int idProducto = Integer.parseInt(request.getParameter("codigoP"));

        boolean realizado = ConexionesEnvios.eliminarProductoEnvio(idEnvio, idProducto);

        accionDefaultEnvio(request, response);
    }

    private void enviarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession sesion = request.getSession();
        int idEnvio = Integer.parseInt(String.valueOf(sesion.getAttribute("idEnvio")));
        int idPedido = Integer.parseInt(String.valueOf(sesion.getAttribute("idPedido")));
        //NECESITO VER EL TOTAL PARA AGREGARLO EN PEDIDOS
        ArrayList<ProductoEnviado> listadoP = ConexionesEnvios.obtenerProductosEnviados(idEnvio);

        double totalEnvio = 0;
        for (ProductoEnviado productos : listadoP) {
            totalEnvio += productos.getCostoTotal();
        }

        ConexionesEnvios.modificarCostoEnvio(idEnvio, totalEnvio);
        ConexionesEnvios.modificarEstadoPedido(idPedido);
        /*try{
            sesion.removeAttribute("idPedido");
            sesion.removeAttribute("listadoP");---->
            sesion.removeAttribute("catalogo");
        }catch(Exception e){
            e.printStackTrace();
        }*/
        response.sendRedirect("vistaUsuarioBodega.jsp");
    }

}
