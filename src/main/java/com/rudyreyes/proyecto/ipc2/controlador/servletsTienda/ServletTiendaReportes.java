/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador.servletsTienda;

import com.rudyreyes.proyecto.ipc2.modelo.entidades.Envio;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Producto;
import com.rudyreyes.proyecto.ipc2.modelo.util.ObtenerReportes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author rudy-reyes
 */
@WebServlet(name = "ServletTiendaReportes", urlPatterns = {"/ServletTiendaReportes"})
public class ServletTiendaReportes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "reporte1":
                        verReporte1(request, response);
                        break;

                    case "reporte2":
                        verReporte2(request, response);
                        break;

                    case "reporte3":
                        verReporte3(request, response);
                        break;

                    case "verReporte1":
                        generarReporte1(request, response);
                        break;

                    case "verReporte2":
                        generarReporte2(request, response);
                        break;

                    case "verReporte3":
                        generarReporte3(request, response);
                        break;

                    case "regresar":
                        accionDefault(request, response);
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

    private void verReporte1(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/vistaReportesT/reporte1.jsp").forward(request, response);

    }

    private void verReporte2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/vistaReportesT/reporte2.jsp").forward(request, response);
    }

    private void verReporte3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/vistaReportesT/reporte3.jsp").forward(request, response);
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        try {
            sesion.removeAttribute("listado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("moduloTienda/reportes.jsp");
    }

    private void generarReporte1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int existencias = Integer.parseInt(request.getParameter("existencias"));
        int codigUsuario = (int) sesion.getAttribute("codigoUsuario");
        int codigoTienda = (int) sesion.getAttribute("codigoTienda");

        ArrayList<Producto> productos = ObtenerReportes.obtenerProductosMenores(existencias, codigoTienda);

        sesion.setAttribute("listado", productos);

        request.getRequestDispatcher("/WEB-INF/vistaReportesT/reporte1.jsp").forward(request, response);

    }

    private void generarReporte2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        HttpSession sesion = request.getSession();
        String fecha1 = request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        String estado = request.getParameter("estado");

        int codigUsuario = (int) sesion.getAttribute("codigoUsuario");

        ArrayList<Pedido> pedidos = ObtenerReportes.obtenerIdPedidosEnUnTiempo(fecha1, fecha2, codigUsuario, estado);

        sesion.setAttribute("listado", pedidos);

        request.getRequestDispatcher("/WEB-INF/vistaReportesT/reporte2.jsp").forward(request, response);

    }

    private void generarReporte3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String fecha1 = request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        int codigoTienda = (int) sesion.getAttribute("codigoTienda");
        
        ArrayList<Envio> envios = ObtenerReportes.obtenerIdEnviosRecibidos(codigoTienda);
        
        
        for(Envio e: envios){
            ObtenerReportes.obtenerCantIncidencia(e);
            ObtenerReportes.obtenerCantDevolucion(e);
        }
        
        sesion.setAttribute("listado", envios);

        request.getRequestDispatcher("/WEB-INF/vistaReportesT/reporte3.jsp").forward(request, response);
    }
}
