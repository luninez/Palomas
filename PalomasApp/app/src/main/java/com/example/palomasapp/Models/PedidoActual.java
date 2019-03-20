package com.example.palomasapp.Models;

import java.util.List;
import java.util.Objects;

public class PedidoActual {

    private Pedido pedido;
    private List<LineaPedido> lineaPedidos;
    private static PedidoActual instance;


    public static PedidoActual Instance(){
        if(instance == null)
            instance = new PedidoActual();
        return  instance;
    }


    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<LineaPedido> getLineaPedidos() {
        return lineaPedidos;
    }

    public void setLineaPedidos(List<LineaPedido> lineaPedidos) {
        this.lineaPedidos = lineaPedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoActual)) return false;
        PedidoActual that = (PedidoActual) o;
        return Objects.equals(pedido, that.pedido) &&
                Objects.equals(lineaPedidos, that.lineaPedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, lineaPedidos);
    }

    @Override
    public String toString() {
        return "PedidoActual{" +
                "pedido=" + pedido +
                ", lineaPedidos=" + lineaPedidos +
                '}';
    }
}
