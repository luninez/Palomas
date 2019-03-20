package com.example.palomasapp.Interfaz;

import com.example.palomasapp.Models.LineaPedido;

public interface OnListLineaPedidoInteractionListener {

    public void onDeleteLineaPedidoClick(String id, String nombre);

    public void onEditLineaPedidoClick(LineaPedido p);

    public void onAddLineaPedidoClick(LineaPedido p);

}
