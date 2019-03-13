package com.example.palomasapp.Interfaz;

import com.example.palomasapp.Funcionalidades.Response.ProductoResponse;
import com.example.palomasapp.Models.Producto;

public interface OnListProductoInteractionListener {

    public void onDeleteProductoClick(String id, String nombre);

    public void onEditProductoClick(Producto p);

    public void onAddProductoClick(Producto p);

    public void onInfoClickProducto(Producto p);

    public void onFavoriteClickProducto(ProductoResponse p);

}
