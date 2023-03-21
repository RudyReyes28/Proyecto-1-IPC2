/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador.servletsAdmin;

import com.rudyreyes.proyecto.ipc2.modelo.entidades.Pedido;
import com.rudyreyes.proyecto.ipc2.modelo.util.ObtenerReportes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ServletAdminReportes", urlPatterns = {"/ServletAdminReportes"})
public class ServletAdminReportes extends HttpServlet {

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

                    case "verReporte1":
                        generarReporte1(request, response);
                        break;

                    case "verReporte2":
                        generarReporte2(request, response);
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

    private void verReporte1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/vistaReportesA/reporte1.jsp").forward(request, response);
    }

    private void verReporte2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/vistaReportesA/reporte2.jsp").forward(request, response);
    }

    private void generarReporte1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String fecha1 = request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        String estado = request.getParameter("estado");

        ArrayList<Pedido> pedidos = ObtenerReportes.obtenerCantTiendasP(fecha1, fecha2, estado);

        sesion.setAttribute("listado", pedidos);

        request.getRequestDispatcher("/WEB-INF/vistaReportesA/reporte1.jsp").forward(request, response);

    }

    private void generarReporte2(HttpServletRequest request, HttpServletResponse response) {

    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        try {
            sesion.removeAttribute("listado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("moduloAdmin/reportes.jsp");
    }

}
