package com.example.palomasapp.Interfaz;

import com.example.palomasapp.Models.Categoria;
import com.example.palomasapp.Models.Producto;

import java.util.List;

public interface OnListCategoriaInteractionListener {

    public void onDeleteCategoriaClick(String id, String nombre);

    public void onEditCategoriaClick(Categoria c);

    public void onAddCategoriaClick(Categoria c);

    public void onInfoClickCategoria(Categoria c);

    public void onGoToProductoClick(List<Producto> productos);

}
