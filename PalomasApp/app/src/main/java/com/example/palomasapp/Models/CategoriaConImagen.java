package com.example.palomasapp.Models;

import java.util.Objects;

public class CategoriaConImagen {

    private String nombre;
    private String picture;

    public CategoriaConImagen() { }

    public CategoriaConImagen(String nombre, String picture) {
        this.nombre = nombre;
        this.picture = picture;
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
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaConImagen that = (CategoriaConImagen) o;
        return Objects.equals(nombre, that.nombre) &&
                Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, picture);
    }

    @Override
    public String toString() {
        return "CategoriaConImagen{" +
                "nombre='" + nombre + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
