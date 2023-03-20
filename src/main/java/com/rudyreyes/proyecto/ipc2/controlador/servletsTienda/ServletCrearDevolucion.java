/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.rudyreyes.proyecto.ipc2.controlador.servletsTienda;

import com.rudyreyes.proyecto.ipc2.modelo.entidades.Devolucion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.Incidencia;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoDevolucion;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoEnviado;
import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoIncidencia;
import com.rudyreyes.proyecto.ipc2.modelo.util.ConexionesDevoluciones;
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
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author rudy-reyes
 */
@WebServlet(name = "ServletCrearDevolucion", urlPatterns = {"/ServletCrearDevolucion"})
public class ServletCrearDevolucion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "crear":
                        insertarDevolucion(request, response);
                        break;

                    case "enviar":
                        enviarDevolucion(request, response);
                        break;

                    case "eliminar":
                        eliminarProducto(request, response);
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

    private void insertarDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        int idEnvio = Integer.parseInt(request.getParameter("idEnvio"));

        //CON ESTO OBTENEMOS EL ENVIO  Y LO AGREGAMOS
        ArrayList<ProductoEnviado> listadoP = ConexionesRecibirEnvio.obtenerProductosEnviados(idEnvio);

        if (listadoP != null) {
            sesion.setAttribute("listado", listadoP);
            sesion.setAttribute("idEnvio", idEnvio);
            response.sendRedirect("moduloTienda/devolucion.jsp");
        } else {
            accionDefault(request, response);
        }
    }

    private void enviarDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<ProductoDevolucion> devoluciones = new ArrayList<>();
        HttpSession sesion = request.getSession();
        //PRIMERO NECESITO CREAR LA INCIDENCIA, Y DESPUES AGREGARLE LOS PRODUCTOS
        int idEnvio = (int) sesion.getAttribute("idEnvio");
        int codigoTienda = (int) sesion.getAttribute("codigoTienda");
        int codigoUsuario = (int) sesion.getAttribute("codigoUsuario");
        LocalDate currentDate = LocalDate.now();
        String fechaIncidencia = currentDate.toString();
        String estado = "ACTIVA";

        //Incidencia incidencia = new Incidencia(idEnvio, codigoTienda, codigoUsuario, fechaIncidencia, estado);
        Devolucion devolucion = new Devolucion(idEnvio, codigoTienda, codigoUsuario, fechaIncidencia, estado);
        boolean realizado = ConexionesDevoluciones.agregarDevolucion(devolucion);

        if (realizado) {
            // Obtener los parametros del formulario
            int idDevolucion = ConexionesDevoluciones.obtenerIdDevolucion(codigoUsuario);
            if (idDevolucion != -1) {
                String[] codigosProductos = request.getParameterValues("codigoProducto");
                String[] cantidadesAfectadas = request.getParameterValues("cantidadAfectada");
                String[] motivos = request.getParameterValues("motivo");
                
                double totalDevolucion = 0;
                // Recorrer los parametros y crear un objeto Incidencia por cada fila de la tabla
                for (int i = 0; i < codigosProductos.length; i++) {
                    int codigoProducto = Integer.parseInt(codigosProductos[i]);
                    int cantidadAfectada = Integer.parseInt(cantidadesAfectadas[i]);
                    String motivo = motivos[i];
                    double costoU = ConexionesPedidos.obtenerCostoUnitario(codigoProducto);
                    double costoT = costoU * cantidadAfectada;
                    totalDevolucion += costoT;
                    ProductoDevolucion ic = new ProductoDevolucion(idDevolucion, codigoProducto, costoU, cantidadAfectada, costoT, motivo);
                    devoluciones.add(ic);
                }

                //AGREGAR LOS PRODUCTOS
                for (ProductoDevolucion producto : devoluciones) {
                    ConexionesDevoluciones.agregarProductosDevolucion(producto);
                }
                
                ConexionesDevoluciones.modificarTotalDevolucion(idDevolucion, totalDevolucion);
            }

            
        } else {
            
        }
        response.sendRedirect("vistaUsuarioTienda.jsp");
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        int idProducto = Integer.parseInt(request.getParameter("codigoP"));

        try {
            ArrayList<ProductoEnviado> listadoP = (ArrayList<ProductoEnviado>) sesion.getAttribute("listado");
            for (ProductoEnviado producto : listadoP) {
                if (producto.getCodigo()==(idProducto)) {
                    listadoP.remove(producto);
                    break; // Detiene el ciclo después de eliminar el producto
                }
            }
            
            sesion.setAttribute("listado", listadoP);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        accionDefault(request, response);
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("moduloTienda/devolucion.jsp");
    }

}
