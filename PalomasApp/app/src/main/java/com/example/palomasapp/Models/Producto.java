package com.example.palomasapp.Models;

import java.util.List;
import java.util.Objects;

public class Producto {

    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private CategoriaConImagen categoriaId;

    private List<String> imagenes;

    public Producto() { }

    public Producto(String nombre, String descripcion, double precio, CategoriaConImagen categoriaId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoriaId = categoriaId;
    }

    public Producto(String nombre, String descripcion, double precio, CategoriaConImagen categoriaId, List<String> imagenes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoriaId = categoriaId;
        this.imagenes = imagenes;
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

    public CategoriaConImagen getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(CategoriaConImagen categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return Double.compare(producto.precio, precio) == 0 &&
                Objects.equals(id, producto.id) &&
                Objects.equals(nombre, producto.nombre) &&
                Objects.equals(descripcion, producto.descripcion) &&
                Objects.equals(categoriaId, producto.categoriaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, precio, categoriaId);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", categoriaId='" + categoriaId.toString() + '\'' +
                '}';
    }
}
