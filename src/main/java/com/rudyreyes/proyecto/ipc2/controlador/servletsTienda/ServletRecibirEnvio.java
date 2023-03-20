package com.rudyreyes.proyecto.ipc2.controlador.servletsTienda;

import com.rudyreyes.proyecto.ipc2.modelo.entidades.ProductoEnviado;
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
@WebServlet(name = "ServletRecibirEnvio", urlPatterns = {"/ServletRecibirEnvio"})
public class ServletRecibirEnvio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "verEnvio":
                        insertarEnvio(request, response);
                        break;

                    case "aceptar":
                        aceptarEnvio(request, response);
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

    private void insertarEnvio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        int idEnvio = Integer.parseInt(request.getParameter("idEnvio"));

        //CON ESTO OBTENEMOS EL ENVIO  Y LO AGREGAMOS
        ArrayList<ProductoEnviado> listadoP = ConexionesRecibirEnvio.obtenerProductosEnviados(idEnvio);

        if (listadoP != null) {
            sesion.setAttribute("listado", listadoP);
            sesion.setAttribute("idEnvio", idEnvio);
            response.sendRedirect("moduloTienda/recibirEnvio.jsp");
        } else {
            accionDefault(request, response);
        }

    }

    private void aceptarEnvio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        int idEnvio = (int) sesion.getAttribute("idEnvio");
        int idTienda = (int) sesion.getAttribute("codigoTienda");
        Integer idPedido = ConexionesRecibirEnvio.obtenerIdPedido(idEnvio);
        LocalDate currentDate = LocalDate.now();
        String fechaEntrega = currentDate.toString();
        //NECESITO CAMBIAR EL ESTADO DEL ENVIO, DEL PEDIDO, AGREGAR LOS VALORES DE LOS CATALOGOS

        if (idPedido != null) {
            //CAMBIAMOS EL ESTADO DEL ENVIO Y PEDIDO
            //NECESITAMOS MODIFICAR LA FECHA
            ConexionesRecibirEnvio.modificarFechaEnvio(idEnvio, fechaEntrega);
            ConexionesRecibirEnvio.modificarEstadoEnvio(idEnvio, "RECIBIDO");
            ConexionesRecibirEnvio.modificarEstadoPedido(idPedido, "COMPLETADO");
            realizarOperaciones(idEnvio, idTienda);
        } else {
            //SOLO CAMBIAMOS EL ESTADO DEL ENVIO
            ConexionesRecibirEnvio.modificarFechaEnvio(idEnvio, fechaEntrega);
            ConexionesRecibirEnvio.modificarEstadoEnvio(idEnvio, "RECIBIDO");
            realizarOperaciones(idEnvio, idTienda);
        }

        response.sendRedirect("vistaUsuarioTienda.jsp");

    }

    private void realizarOperaciones(int idEnvio, int idTienda) {
        ArrayList<ProductoEnviado> listadoP = ConexionesRecibirEnvio.obtenerProductosEnviados(idEnvio);

        for (ProductoEnviado listado : listadoP) {
            ConexionesRecibirEnvio.modificarCatalogoTienda(idTienda, listado.getCodigo(), listado.getCantidad());
            ConexionesRecibirEnvio.modificarCatalogoBodega(listado.getCodigo(), listado.getCantidad());
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("moduloTienda/recibirEnvio.jsp");
    }

}
