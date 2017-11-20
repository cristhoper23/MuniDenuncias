package com.cristhoper.munidenuncias.models;

/**
 * Created by Cris on 15/11/2017.
 */

//Los nombres de la clase deben ser los mismos que los campos que le están llegando
public class Denuncia {
    private int id;
    private String username;
    private String titulo;
    private String descripcion;
    private String imagen;
    private String ubicacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Denuncia{" +
                "id=" + id +
                ", username=" + username +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }
}
