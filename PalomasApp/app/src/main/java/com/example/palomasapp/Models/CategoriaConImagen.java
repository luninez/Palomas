package com.example.palomasapp.Models;

import java.util.Objects;

public class CategoriaConImagen {

    private String id;
    private String nombre;
    private String picture;

    public CategoriaConImagen() { }

    public CategoriaConImagen(String nombre, String picture) {
        this.nombre = nombre;
        this.picture = picture;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaConImagen)) return false;
        CategoriaConImagen that = (CategoriaConImagen) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, picture);
    }

    @Override
    public String toString() {
        return "CategoriaConImagen{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
