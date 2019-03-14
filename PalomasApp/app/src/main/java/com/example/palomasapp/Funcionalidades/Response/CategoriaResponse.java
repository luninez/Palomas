package com.example.palomasapp.Funcionalidades.Response;

public class CategoriaResponse {

    private String nombre;

    public CategoriaResponse() { }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CategoriaResponse{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
