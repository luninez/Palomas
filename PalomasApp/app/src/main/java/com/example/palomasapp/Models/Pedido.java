package com.example.palomasapp.Models;

import java.util.Calendar;
import java.util.Objects;

public class Pedido {

    private String id;
    private String estado;
    private String fecha;
    private User usuarioId;

    public Pedido() { }

    public Pedido(String estado, String fecha, User usuarioId) {
        this.estado = estado;
        this.fecha = fecha;
        this.usuarioId = usuarioId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public User getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(User usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) &&
                Objects.equals(estado, pedido.estado) &&
                Objects.equals(fecha, pedido.fecha) &&
                Objects.equals(usuarioId, pedido.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estado, fecha, usuarioId);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", estado='" + estado + '\'' +
                ", fecha=" + fecha +
                ", usuarioId='" + usuarioId.toString() + '\'' +
                '}';
    }
}
