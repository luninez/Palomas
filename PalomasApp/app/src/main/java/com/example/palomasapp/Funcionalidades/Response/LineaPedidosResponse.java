package com.example.palomasapp.Funcionalidades.Response;

import com.example.palomasapp.Models.Pedido;
import com.example.palomasapp.Models.Producto;

public class LineaPedidosResponse {

    private String id;
    private int cantidad;
    private double precio;
    //private String pedidoId;
    private Producto productoId;

    public LineaPedidosResponse() { }

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

    /*public String getPedidoId() {
        return pedidoId;
    }*/

    /*public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }*/

    public Producto getProductoId() {
        return productoId;
    }

    public void setProductoId(Producto productoId) {
        this.productoId = productoId;
    }

    @Override
    public String toString() {
        return "LineaPedidosResponse{" +
                "id='" + id + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                //", pedidoId=" + pedidoId +
                ", productoId=" + productoId +
                '}';
    }
}
