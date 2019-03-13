package com.example.palomasapp.Models;

import java.util.Objects;

public class LineaPedido {

    private int cantidad;
    private double precio;
    private String pedidoId;
    private String productoId;

    public LineaPedido() { }

    public LineaPedido(int cantidad, double precio, String pedidoId, String productoId) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.pedidoId = pedidoId;
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineaPedido that = (LineaPedido) o;
        return cantidad == that.cantidad &&
                Double.compare(that.precio, precio) == 0 &&
                Objects.equals(pedidoId, that.pedidoId) &&
                Objects.equals(productoId, that.productoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cantidad, precio, pedidoId, productoId);
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "cantidad=" + cantidad +
                ", precio=" + precio +
                ", pedidoId='" + pedidoId + '\'' +
                ", productoId='" + productoId + '\'' +
                '}';
    }
}
