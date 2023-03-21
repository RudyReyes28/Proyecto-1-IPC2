/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador;

import com.rudyreyes.proyecto.ipc2.modelo.Usuario;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesIncidencias;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesPedidos;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesRecibirEnvio;
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
@WebServlet(name = "ServletOpcionesTienda", urlPatterns = {"/ServletOpcionesTienda"})
public class ServletOpcionesTienda extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "pedido":
                        iniciarPedido(request, response);
                        break;
                    case "incidencias":
                        iniciarIncidencia(request, response);
                        break;

                    case "devoluciones":
                        iniciarDevolucion(request, response);
                        break;

                    case "envios":
                        iniciarEnvios(request, response);
                        break;

                    case "productos":

                        break;

                    case "reportes":

                        break;

                    default:
                        response.sendRedirect("vistaUsuarioTienda.jsp");

                }
            } else {
                response.sendRedirect("vistaUsuarioTienda.jsp");
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

    private void iniciarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //NECESITO INICIAR UNA CONEXION PARA AGREGAR PEDIDO y generar el ID;
        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        int codigoUsuario = user.getCodigo();
        int codigoTienda = ConexionesPedidos.obtenerTienda(codigoUsuario);
        LocalDate currentDate = LocalDate.now();
        String fecha = currentDate.toString();
        
        String estado = "SOLICITADO";
        
        String tipo = ConexionesPedidos.obtenerTipoTienda(codigoTienda);
        
        if(tipo.equalsIgnoreCase("SUPERVISADA")){
            estado = "PENDIENTE";
        }

        Pedido pedido = new Pedido(codigoTienda, codigoUsuario, fecha, estado);

        if (codigoTienda != -1) {
            boolean realizado = ConexionesPedidos.agregarPedido(pedido);

            if (realizado) {
                int idPedido = ConexionesPedidos.obtenerIdPedido(codigoUsuario);
                ArrayList<Integer> catalogoProductos = ConexionesPedidos.obtenerIdProductos(codigoTienda);

                if (catalogoProductos != null && idPedido != -1) {
                    sesion.setAttribute("idPedido", idPedido);
                    sesion.setAttribute("catalogo", catalogoProductos);
                    response.sendRedirect("moduloTienda/pedido.jsp");
                } else {
                    response.sendRedirect("vistaUsuarioTienda.jsp");
                }
            }
        }

    }

    private void iniciarEnvios(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        int codigoUsuario = user.getCodigo();
        int codigoTienda = ConexionesPedidos.obtenerTienda(codigoUsuario);

        //ONTENER EL ID DE LOS ENVIOS CON CODIGO DE TIENDA
        ArrayList<Integer> listaEnvios = ConexionesRecibirEnvio.obtenerIdEnvios(codigoTienda);

        if (listaEnvios != null) {
            sesion.setAttribute("codigoUsuario", codigoUsuario);
            sesion.setAttribute("codigoTienda", codigoTienda);
            sesion.setAttribute("listadoEnvio", listaEnvios);
            response.sendRedirect("moduloTienda/recibirEnvio.jsp");
        } else {
            response.sendRedirect("vistaUsuarioTienda.jsp");
        }
    }

    private void iniciarIncidencia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        int codigoUsuario = user.getCodigo();
        int codigoTienda = ConexionesPedidos.obtenerTienda(codigoUsuario);
        
        //OBTENER LOS ID DE LOS ENVIOS CON ESTADO "RECIBIDO"
        ArrayList<Integer> listaEnvios = ConexionesIncidencias.obtenerIdEnvios(codigoTienda);
        
        if(listaEnvios != null){
            sesion.setAttribute("codigoUsuario", codigoUsuario);
            sesion.setAttribute("codigoTienda", codigoTienda);
            sesion.setAttribute("listadoEnvio", listaEnvios);
            response.sendRedirect("moduloTienda/incidencia.jsp");
        }else {
            response.sendRedirect("vistaUsuarioTienda.jsp");
        }
    }

    private void iniciarDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession sesion = request.getSession();
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        int codigoUsuario = user.getCodigo();
        int codigoTienda = ConexionesPedidos.obtenerTienda(codigoUsuario);
        
        //OBTENER LOS ID DE LOS ENVIOS CON ESTADO "RECIBIDO"
        ArrayList<Integer> listaEnvios = ConexionesIncidencias.obtenerIdEnvios(codigoTienda);
        
        if(listaEnvios != null){
            sesion.setAttribute("codigoUsuario", codigoUsuario);
            sesion.setAttribute("codigoTienda", codigoTienda);
            sesion.setAttribute("listadoEnvio", listaEnvios);
            response.sendRedirect("moduloTienda/devolucion.jsp");
        }else {
            response.sendRedirect("vistaUsuarioTienda.jsp");
        }
    }

}
