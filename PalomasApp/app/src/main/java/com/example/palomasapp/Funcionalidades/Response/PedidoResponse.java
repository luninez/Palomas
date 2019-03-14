package com.example.palomasapp.Funcionalidades.Response;

import java.time.LocalDate;

public class PedidoResponse {

    private String estado;
    private LocalDate fecha;
    private String usuarioId;

    public PedidoResponse() { }

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
    public String toString() {
        return "PedidoResponse{" +
                "estado='" + estado + '\'' +
                ", fecha=" + fecha +
                ", usuarioId='" + usuarioId + '\'' +
                '}';
    }
}
