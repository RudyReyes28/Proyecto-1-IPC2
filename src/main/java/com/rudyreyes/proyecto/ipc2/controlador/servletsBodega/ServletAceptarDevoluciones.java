/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador.servletsBodega;

import com.rudyreyes.proyecto.ipc2.modelo.entidades.Devolucion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Incidencia;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoDevolucion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoEnviado;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoIncidencia;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesAceptarDevolucion;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesRecibirEnvio;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesSolucionarIncidencia;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author rudy-reyes
 */
@WebServlet(name = "ServletAceptarDevoluciones", urlPatterns = {"/ServletAceptarDevoluciones"})
public class ServletAceptarDevoluciones extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        try {
            if (accion != null) {
                switch (accion) {
                    case "devolucion":
                        insertarDevoluciones(request, response);
                        break;
                    case "mostrar":
                        mostrarDevolucion(request, response);
                        break;

                    case "aceptar":
                        aceptarDevolucion(request, response);
                        break;

                    case "rechazar":
                        rechazarDevolucion(request, response);
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

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect("moduloBodega/aceptarDevolucion.jsp");
    }

    private void insertarDevoluciones(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession sesion = request.getSession();
        int tienda = Integer.parseInt(request.getParameter("tienda"));

        ArrayList<Devolucion> devoluciones = ConexionesAceptarDevolucion.obtenerDevoluciones(tienda);
        sesion.setAttribute("idTienda", tienda);
        sesion.setAttribute("listaDevolucion", devoluciones);

        accionDefault(request, response);
    }

    private void mostrarDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        int idDevolucion = Integer.parseInt(request.getParameter("idDevolucion"));

        //CON ESTO OBTENEMOS EL ENVIO  Y LO AGREGAMOS
        ArrayList<ProductoDevolucion> listadoP = ConexionesAceptarDevolucion.obtenerProductosDevolucion(idDevolucion);

        if (listadoP != null) {
            sesion.setAttribute("listado", listadoP);
            sesion.setAttribute("idDevolucion", idDevolucion);
            request.getRequestDispatcher("/WEB-INF/vistaAceptarDevolucion/listadoProductos.jsp").forward(request, response);
        } else {
            accionDefault(request, response);
        }
    }

    private void aceptarDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        int idDevolucion = (int) sesion.getAttribute("idDevolucion");
        int idTienda = (int) sesion.getAttribute("idTienda");

        realizarOperaciones(idDevolucion, idTienda);
        ConexionesAceptarDevolucion.modificarEstadoDevolucion(idDevolucion, "ACEPTADA");
        //COSA 1: CAMBIAR LOS VALORES DE LOS PRODUCTOS
        //COSA 2: CAMBIAR EL ESTADO DE LA DEVOLUCION
        
        try{
            sesion.removeAttribute("listaDevolucion");
        }catch(Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("vistaUsuarioBodega.jsp");

    }

    private void realizarOperaciones(int idDevolucion, int idTienda) {
        ArrayList<ProductoDevolucion> listadoP = ConexionesAceptarDevolucion.obtenerProductosDevolucion(idDevolucion);

        for (ProductoDevolucion listado : listadoP) {
            ConexionesAceptarDevolucion.modificarCatalogoTienda(idTienda, listado.getCodigo(), listado.getCantidad());
            ConexionesAceptarDevolucion.modificarCatalogoBodega(listado.getCodigo(), listado.getCantidad());
        }
    }

    private void rechazarDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        int idDevolucion = (int) sesion.getAttribute("idDevolucion");
        ConexionesAceptarDevolucion.modificarEstadoDevolucion(idDevolucion, "RECHAZADA");
        
        try{
            sesion.removeAttribute("listaDevolucion");
        }catch(Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("vistaUsuarioBodega.jsp");
    }

}
