package com.example.palomasapp.Interfaz;

import com.example.palomasapp.Models.CategoriaConImagen;

public interface OnListCategoriaConImagenInteractionListener {

    public void onDeleteCategoriaClick(String id, String nombre);

    public void onEditCategoriaClick(CategoriaConImagen c);

    public void onAddCategoriaClick(CategoriaConImagen c);

}
