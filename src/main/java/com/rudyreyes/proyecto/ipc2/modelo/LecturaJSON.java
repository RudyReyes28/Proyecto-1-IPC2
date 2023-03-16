
package com.rudyreyes.proyecto.ipc2.modelo;


import com.rudyreyes.proyecto.ipc2.modelo.entidades.*;
import com.rudyreyes.proyecto.ipc2.modelo.llenarbd.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author rudy-reyes
 */
public class LecturaJSON {

    
    public static void leerArchivo(String nombreArchivo){
        JSONParser jsonParser = new JSONParser();
        
        try(FileReader reader = new FileReader("/tmp/"+nombreArchivo)){
            Object obj = jsonParser.parse(reader);
            
            leerCatalogo((JSONObject) obj);
            leerTiendas((JSONObject) obj);
            leerAdmins((JSONObject) obj);
            leerUsuariosTienda((JSONObject) obj);
            leerSupervisores((JSONObject) obj);
            leerEncargadosBodega((JSONObject) obj);
            leerPedido((JSONObject) obj);
            leerEnvio((JSONObject) obj);
            leerIncidencias((JSONObject) obj);
            leerDevoluciones((JSONObject) obj);
            
            realizarConexiones();
            
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }
    }

    private static void leerCatalogo(JSONObject jsonObject) {
        JSONArray productos = (JSONArray) jsonObject.get("productos");
        
        for(Object producto: productos){
            JSONObject p = (JSONObject) producto;
            int codigo = Integer.parseInt(String.valueOf(p.get("codigo")));
            String nombre = String.valueOf(p.get("nombre"));
            double costo = Double.parseDouble(String.valueOf(p.get("costo")));
            double precio = Double.parseDouble(String.valueOf(p.get("precio")));
            int existencias = Integer.parseInt(String.valueOf(p.get("existencias")));
            
            Producto producto1 = new Producto(codigo, nombre, costo, precio, existencias);
            boolean realizado = EscribirCatalogo.agregarCatalogo(producto1);
            
            if(realizado){
                System.out.println("Hecho");
            }else{
                System.out.println("Error");
            }
        }
    }

    private static void leerTiendas(JSONObject jsonObject) {
        JSONArray tiendas = (JSONArray) jsonObject.get("tiendas");
        
        for(Object tienda: tiendas){
            JSONObject t = (JSONObject) tienda;
            
            int codigo = Integer.parseInt(String.valueOf(t.get("codigo")));
            String nombre = String.valueOf(t.get("nombre"));
            String direccion = String.valueOf(t.get("direccion"));
            String tipoTienda = String.valueOf(t.get("tipo"));
            
            Tienda tienda1 = new Tienda(codigo, nombre, direccion, tipoTienda);
            boolean realizado = EscribirTiendas.agregarTiendas(tienda1);
            
            if (realizado) {

                JSONArray productos = (JSONArray) t.get("productos");
                for (Object producto : productos) {
                    JSONObject p = (JSONObject) producto;
                    int codigoP = Integer.parseInt(String.valueOf(p.get("codigo")));
                    int existenciasP = Integer.parseInt(String.valueOf(p.get("existencias")));
                    
                    Producto producto1 = new Producto(codigoP, existenciasP);
                    boolean realizadoP = EscribirTiendas.agregarProductosTienda(codigo, producto1);
                    
                    if(realizadoP){
                        //System.out.println("Hecho");
                    }else{
                        System.out.println("Error");
                    }
                    
                }
            }else{
                System.out.println("Error");
            }
            
            System.out.println("");
        }

    }

    private static void leerAdmins(JSONObject jsonObject) {
        JSONArray admins = (JSONArray) jsonObject.get("admins");
        
        for(Object admin: admins){
            JSONObject ad = (JSONObject) admin;
            
            int codigo = Integer.parseInt(String.valueOf(ad.get("codigo")));
            String nombre = String.valueOf(ad.get("nombre"));
            String usuario = String.valueOf(ad.get("username"));
            String contraseña = String.valueOf(ad.get("password"));
            
            Administrador admin1 = new Administrador(codigo, nombre, usuario, contraseña);
            boolean realizado = EscribirAdmin.agregarAdmin(admin1);
            
            if (realizado) {
                System.out.println("Hecho");
            } else {
                System.out.println("Error");
            }
        }
    }

    private static void leerUsuariosTienda(JSONObject jsonObject) {
        JSONArray usuariosT = (JSONArray) jsonObject.get("usuariostienda");
        
        for(Object user: usuariosT){
            JSONObject u = (JSONObject) user;
            
            int codigo = Integer.parseInt(String.valueOf(u.get("codigo")));
            String nombre = String.valueOf(u.get("nombre"));
            int codigoTienda = Integer.parseInt(String.valueOf(u.get("tienda")));
            String usuario = String.valueOf(u.get("username"));
            String contraseña = String.valueOf(u.get("password"));
            String correo = String.valueOf(u.get("email"));
            
            UsuarioTienda usuario1 = new UsuarioTienda(codigo, nombre, codigoTienda, usuario, contraseña, correo);
            
            boolean realizado = EscribirUsuariosTienda.agregarUsuarioTienda(usuario1);
            
            if (realizado) {
                System.out.println("Hecho");
            } else {
                System.out.println("Error");
            }
        }
    }

    private static void leerSupervisores(JSONObject jsonObject) {
        JSONArray supervisores = (JSONArray) jsonObject.get("supervisores");
        
        for(Object user: supervisores){
            JSONObject u = (JSONObject) user;
            
            int codigo = Integer.parseInt(String.valueOf(u.get("codigo")));
            String nombre = String.valueOf(u.get("nombre"));
            String usuario = String.valueOf(u.get("username"));
            String contraseña = String.valueOf(u.get("password"));
            String correo = String.valueOf(u.get("email"));
            
            SupervisorTienda usuario1 = new SupervisorTienda(codigo, nombre, usuario, contraseña, correo);
            
            boolean realizado = EscribirSupervisores.agregarSupervisores(usuario1);
            
            if (realizado) {
                System.out.println("Hecho");
            } else {
                System.out.println("Error");
            }
        }
    }

    private static void leerEncargadosBodega(JSONObject jsonObject) {
        JSONArray bodegas = (JSONArray) jsonObject.get("encargadosBodega");
        
        for(Object user: bodegas){
            JSONObject u = (JSONObject) user;
            
            int codigo = Integer.parseInt(String.valueOf(u.get("codigo")));
            String nombre = String.valueOf(u.get("nombre"));
            String usuario = String.valueOf(u.get("username"));
            String contraseña = String.valueOf(u.get("password"));
            String correo = String.valueOf(u.get("email"));
            
            UsuarioBodega usuario1 = new UsuarioBodega(codigo, nombre, usuario, contraseña, correo);
            
            boolean realizado = EscribirEncargadoBodega.agregarEncargadoBodega(usuario1);
            
            if (realizado) {
                JSONArray tiendas = (JSONArray) u.get("tiendas");
                for (Object tienda : tiendas) {

                    int idTienda = Integer.parseInt(String.valueOf(tienda));
                    boolean realizadoC = EscribirEncargadoBodega.conectarTiendaBodega(codigo, idTienda);

                    if (!realizadoC) {
                        System.out.println("Error");
                    }

                }
            } else {
                System.out.println("Error");
            }
            
            
        }
    }

    private static void leerPedido(JSONObject jsonObject) {
        JSONArray pedidos = (JSONArray) jsonObject.get("pedidos");
        
        for(Object pedido: pedidos){
            JSONObject pe = (JSONObject) pedido;
            
            int codigo = Integer.parseInt(String.valueOf(pe.get("id")));
            int codigoTienda = Integer.parseInt(String.valueOf(pe.get("tienda")));
            int codigoUsuario = Integer.parseInt(String.valueOf(pe.get("usuario")));
            String fecha = String.valueOf(pe.get("fecha"));
            double total = Double.parseDouble(String.valueOf(pe.get("total")));
            String estado = String.valueOf(pe.get("estado"));
            
            Pedido pedidoU = new Pedido(codigo, codigoTienda, codigoUsuario, fecha, total, estado);
            
            boolean realizado = EscribirPedido.agregarPedido(pedidoU);
            
            if (realizado) {
                
                JSONArray productos = (JSONArray) pe.get("productos");
                for (Object producto : productos) {
                    JSONObject p = (JSONObject) producto;
                    
                    int codigoP = Integer.parseInt(String.valueOf(p.get("codigo")));
                    double costo = Double.parseDouble(String.valueOf(p.get("costoU")));
                    int cantidad = Integer.parseInt(String.valueOf(p.get("cantidad")));
                    double costoTotal = Double.parseDouble(String.valueOf(p.get("costoTotal")));
                    
            
                    ProductoPedido productosP = new ProductoPedido(codigo, codigoP, costo, cantidad, costoTotal);
                    boolean realizarP = EscribirPedido.agregarProductosPedido(productosP);
                    
                    if(!realizarP){
                        System.out.println("Error");
                    }
                }
            }else{
                System.out.println("Error");
            }
            
        }
    }

    private static void leerEnvio(JSONObject jsonObject) {
        JSONArray envios = (JSONArray) jsonObject.get("envios");
        
        for(Object envio: envios){
            JSONObject en = (JSONObject) envio;
            
            int codigo = Integer.parseInt(String.valueOf(en.get("id")));
            int codigoTienda = Integer.parseInt(String.valueOf(en.get("tienda")));
            int codigoUsuario = Integer.parseInt(String.valueOf(en.get("usuario")));
            String fechaSalida = String.valueOf(en.get("fechaSalida"));
            String fechaRecibido = String.valueOf(en.get("fechaRecibido"));
            double total = Double.parseDouble(String.valueOf(en.get("total")));
            String estado = String.valueOf(en.get("estado"));
            
            if(fechaRecibido.equals("")){
                fechaRecibido = null;
            }
            Envio envio1 = new Envio(codigo, codigoTienda, codigoUsuario, fechaSalida, fechaRecibido, total, estado);
            boolean realizado = EscribirEnvio.agregarEnvio(envio1);
            
            if (realizado) {
                JSONArray productos = (JSONArray) en.get("productos");

                for (Object producto : productos) {
                    JSONObject p = (JSONObject) producto;
                    
                    int codigoP = Integer.parseInt(String.valueOf(p.get("codigo")));
                    double costo = Double.parseDouble(String.valueOf(p.get("costoU")));
                    int cantidad = Integer.parseInt(String.valueOf(p.get("cantidad")));
                    double costoTotal = Double.parseDouble(String.valueOf(p.get("costoTotal")));
                    
            
                    ProductoEnviado productosP = new ProductoEnviado(codigo, codigoP, costo, cantidad, costoTotal);
                    boolean realizarP = EscribirEnvio.agregarProductosEnvio(productosP);
                    
                    if(!realizarP){
                        System.out.println("Error");
                    }
                }
            }else{
                System.out.println("Error");
            }
        }
            
    }

    private static void leerIncidencias(JSONObject jsonObject){
        JSONArray incidencias = (JSONArray) jsonObject.get("incidencias");
        
        for(Object incidencia: incidencias){
            JSONObject in = (JSONObject) incidencia;
            
            int codigo = Integer.parseInt(String.valueOf(in.get("id")));
            int codigoTienda = Integer.parseInt(String.valueOf(in.get("tienda")));
            int codigoUsuario = Integer.parseInt(String.valueOf(in.get("usuario")));
            String fechaIncidencia = String.valueOf(in.get("fecha"));
            String solucion = String.valueOf(in.get("solucion"));
            String estado = String.valueOf(in.get("estado"));
            
            Incidencia incidencia1 = new Incidencia(codigoTienda, codigoTienda, codigoUsuario, fechaIncidencia, solucion, estado);
            boolean realizado = EscribirIncidencia.agregarIncidencia(incidencia1);
            
            if(realizado){
            
                JSONArray productos = (JSONArray) in.get("productos");
                for (Object producto : productos) {
                    JSONObject p = (JSONObject) producto;
                    
                    int codigoP = Integer.parseInt(String.valueOf(p.get("codigo")));
                    int cantidad = Integer.parseInt(String.valueOf(p.get("cantidad")));
                    String motivo = String.valueOf(p.get("motivo"));
                    
                    ProductoIncidencia productoIncidencia = new ProductoIncidencia(codigo, codigoP, cantidad, motivo);
                    boolean realizarP = EscribirIncidencia.agregarProductoIncidencia(productoIncidencia);
                    if(!realizarP){
                        System.out.println("Error");
                    }
                }
            }else{
                System.out.println("Error");
            }
            
        }
    }

    private static void leerDevoluciones(JSONObject jsonObject) {
        JSONArray devoluciones = (JSONArray) jsonObject.get("devoluciones");
        
        for(Object devolucion: devoluciones){
            JSONObject dev = (JSONObject) devolucion;
            
            int codigo = Integer.parseInt(String.valueOf(dev.get("id")));
            int codigoTienda = Integer.parseInt(String.valueOf(dev.get("tienda")));
            int codigoUsuario = Integer.parseInt(String.valueOf(dev.get("usuario")));
            String fechaDevolucion = String.valueOf(dev.get("fecha"));
            double total = Double.parseDouble(String.valueOf(dev.get("total")));
            String estado = String.valueOf(dev.get("estado"));
            
            Devolucion devolucion1 = new Devolucion(codigo, codigoTienda, codigoUsuario, fechaDevolucion, total, estado);
            boolean realizado = EscribirDevolucion.agregarDevolucion(devolucion1);
            
            if(realizado){
            
                JSONArray productos = (JSONArray) dev.get("productos");
                for (Object producto : productos) {
                    JSONObject p = (JSONObject) producto;
                    
                    
                    int codigoP = Integer.parseInt(String.valueOf(p.get("codigo")));
                    double costo = Double.parseDouble(String.valueOf(p.get("costo")));
                    int cantidad = Integer.parseInt(String.valueOf(p.get("cantidad")));
                    double costoTotal = Double.parseDouble(String.valueOf(p.get("costoTotal")));
                    String motivo = String.valueOf(p.get("motivo"));
                    
                    ProductoDevolucion productoDev = new ProductoDevolucion(codigo, codigoP, costo, cantidad, costoTotal, motivo);
                    boolean realizarP = EscribirDevolucion.agregarProductoDevolucion(productoDev);
                    
                    if(!realizarP){
                        System.out.println("Error");
                    }
                }
            }else{
                System.out.println("Error");
            }
            
        }
        
    }

    private static void realizarConexiones() {
        ConectarBodegaCatalogo.conectarBodegaCatalogo();
        
        ConectarSupervisorTiendas.conectarSupervisorTiendas();
        
        ConectarAdminTienda.conectarAdminTiendas();
        
        ConectarAdminBodega.conectarAdminBodegas();
        
        ConectarAdminSupervisor.conectarAdminSupervisor();
    }
    
}
