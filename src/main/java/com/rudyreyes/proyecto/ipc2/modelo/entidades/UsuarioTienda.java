
package com.rudyreyes.proyecto.ipc2.modelo.entidades;

import com.rudyreyes.proyecto.ipc2.modelo.util.CifrarContraseña;

/**
 *
 * @author rudy-reyes
 */
public class UsuarioTienda {
    
    private int codigo;
    private String nombre;
    private int codigoTienda;
    private String nombreUsuario;
    private String contraseña;
    private String correo;

    public UsuarioTienda(int codigo, String nombre, int codigoTienda, String nombreUsuario, String contraseña, String correo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codigoTienda = codigoTienda;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = CifrarContraseña.hash(contraseña);
        this.correo = correo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigoTienda() {
        return codigoTienda;
    }

    public void setCodigoTienda(int codigoTienda) {
        this.codigoTienda = codigoTienda;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = CifrarContraseña.hash(contraseña);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    
    
}
