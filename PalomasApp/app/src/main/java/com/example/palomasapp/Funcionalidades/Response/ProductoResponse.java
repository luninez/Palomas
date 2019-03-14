package com.example.palomasapp.Funcionalidades.Response;

import com.example.palomasapp.Models.Categoria;
import com.example.palomasapp.Models.Producto;

import java.util.List;
import java.util.Objects;

public class ProductoResponse {

    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean favorito;
    private Categoria categoriaId;

    public ProductoResponse() { }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public String toString() {
        return "ProductoResponse{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", favorito=" + favorito +
                ", categoriaId=" + categoriaId +
                '}';
    }
}
