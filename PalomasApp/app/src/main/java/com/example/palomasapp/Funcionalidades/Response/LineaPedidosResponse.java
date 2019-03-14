package com.example.palomasapp.Funcionalidades.Response;

public class LineaPedidosResponse {

    private int cantidad;
    private double precio;
    private String pedidoId;
    private String productoId;

    public LineaPedidosResponse() { }

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
    public String toString() {
        return "LineaPedidosResponse{" +
                "cantidad=" + cantidad +
                ", precio=" + precio +
                ", pedidoId='" + pedidoId + '\'' +
                ", productoId='" + productoId + '\'' +
                '}';
    }
}
