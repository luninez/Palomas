package com.example.palomasapp.Models;

import java.util.Objects;

public class LineaPedido {

    private String id;
    private int cantidad;
    private double precio;
    private String pedidoId;
    private Producto productoId;

    public LineaPedido() { }

    public LineaPedido(int cantidad, double precio, String pedidoId, Producto productoId) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.pedidoId = pedidoId;
        this.productoId = productoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Producto getProductoId() {
        return productoId;
    }

    public void setProductoId(Producto productoId) {
        this.productoId = productoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineaPedido)) return false;
        LineaPedido that = (LineaPedido) o;
        return cantidad == that.cantidad &&
                Double.compare(that.precio, precio) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(pedidoId, that.pedidoId) &&
                Objects.equals(productoId, that.productoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad, precio, pedidoId, productoId);
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", pedidoId='" + pedidoId + '\'' +
                ", productoId='" + productoId.toString() + '\'' +
                '}';
    }
}
