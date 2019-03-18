package com.example.palomasapp.Funcionalidades.Response;

public class CategoriaResponse {

    private String id;
    private String nombre;
    private String picture;

    public CategoriaResponse() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "CategoriaResponse{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
