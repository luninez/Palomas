package com.example.palomasapp.Models;

import java.util.Objects;

public class Categoria {

    private String nombre;
    private String picture;

    public Categoria() { }

    public Categoria(String nombre, String picture) {
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
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(nombre, categoria.nombre) &&
                Objects.equals(picture, categoria.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, picture);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nombre='" + nombre + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
