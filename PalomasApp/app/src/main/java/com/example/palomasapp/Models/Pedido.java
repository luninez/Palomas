package com.example.palomasapp.Models;

import java.time.LocalDate;
import java.util.Objects;

public class Pedido {

    private String estado;
    private LocalDate fecha;
    private String usuarioId;

    public Pedido() { }

    public Pedido(String estado, LocalDate fecha, String usuarioId) {
        this.estado = estado;
        this.fecha = fecha;
        this.usuarioId = usuarioId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(estado, pedido.estado) &&
                Objects.equals(fecha, pedido.fecha) &&
                Objects.equals(usuarioId, pedido.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estado, fecha, usuarioId);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "estado='" + estado + '\'' +
                ", fecha=" + fecha +
                ", usuarioId='" + usuarioId + '\'' +
                '}';
    }
}
