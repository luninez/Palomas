package com.example.palomasapp.Interfaz;

import com.example.palomasapp.Models.Categoria;
import com.example.palomasapp.Models.Pedido;

public interface OnListPedidoInteractionListener {

    public void onDeletePedidoClick(String id, String nombre);

    public void onEditPedidoClick(Pedido p);

    public void onAddPedidoClick(Pedido p);

    public void onInfoClickPedido(Pedido p);

}
