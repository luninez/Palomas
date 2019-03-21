package com.example.palomasapp.Interfaz;

import com.example.palomasapp.Models.User;

public interface OnListUserIteracionListener {

    public void onDeleteUserClick(String id, String nombre);

    public void onEditUserClick(User u);
}
