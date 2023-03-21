/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador.servletsBodega;

import com.rudyreyes.proyecto.ipc2.modelo.entidades.Incidencia;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoEnviado;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoIncidencia;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesEnvios;
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
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "ServletSolucionarIncidencias", urlPatterns = {"/ServletSolucionarIncidencias"})
public class ServletSolucionarIncidencias extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");

        try {
            if (accion != null) {
                switch (accion) {
                    case "incidencias":
                        insertarIncidencias(request, response);
                        break;
                    case "mostrar":
                        mostrarIncidencia(request, response);
                        break;

                    case "enviar":
                        enviarIncidencia(request, response);
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

    private void insertarIncidencias(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession sesion = request.getSession();
        int tienda = Integer.parseInt(request.getParameter("tienda"));

        ArrayList<Incidencia> incidencias = ConexionesSolucionarIncidencia.obtenerIncidencias(tienda);
        sesion.setAttribute("idTienda", tienda);
        sesion.setAttribute("listaIncidencia", incidencias);

        accionDefault(request, response);
    }

    private void mostrarIncidencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int idIncidencia = Integer.parseInt(request.getParameter("idIncidencia"));

        //CON ESTO OBTENEMOS EL ENVIO  Y LO AGREGAMOS
        ArrayList<ProductoIncidencia> listadoP = ConexionesSolucionarIncidencia.obtenerProductosIncidencias(idIncidencia);

        if (listadoP != null) {
            sesion.setAttribute("listado", listadoP);
            sesion.setAttribute("idIncidencia", idIncidencia);
            request.getRequestDispatcher("/WEB-INF/vistaSolucionIncidencia/listadoProductos.jsp").forward(request, response);
        } else {
            accionDefault(request, response);
        }
    }

    private void enviarIncidencia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        int idIncidencia = (int) sesion.getAttribute("idIncidencia");
        String solucion = request.getParameter("solucion");
        
        ConexionesSolucionarIncidencia.agregarSolucion(solucion, idIncidencia);
        ConexionesSolucionarIncidencia.modificarEstadoIncidencia(idIncidencia, "SOLUCIONADA");
        
        try{
            
            sesion.removeAttribute("listaIncidencia");
        }catch(Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("vistaUsuarioBodega.jsp");

    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect("moduloBodega/solucionarIncidencia.jsp");
    }

}
