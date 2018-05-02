package com.iglesias.c.mercuriomovil.Pojo;

/**
 * Created by Ciglesias on 28/03/2018.
 */

public class SitioItem {
    private String nombre, codigo, telefono, direccion;
    private int id;

    public SitioItem(String nombre, String codigo, String telefono, String direccion, int id) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.id = id;
    }

    public SitioItem(String nombre, int id) {
        this.nombre = nombre;
        this.codigo = "No registra";
        this.telefono = "No registra";
        this.direccion = "No registra";
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
