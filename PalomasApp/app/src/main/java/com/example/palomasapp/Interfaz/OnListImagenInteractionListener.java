package com.example.palomasapp.Interfaz;

import com.example.palomasapp.Models.Categoria;
import com.example.palomasapp.Models.Imagen;

public interface OnListImagenInteractionListener {

    public void onDeleteImagenClick(String id, String nombre);

    public void onEditImagenClick(Imagen i);

    public void onAddImagenClick(Imagen i);

    public void onInfoClickImagen(Imagen i);

}
