package com.example.palomasapp.Blanck;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.ProductoService;
import com.example.palomasapp.Funcionalidades.Util;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.TipoAutenticacion;
import com.example.palomasapp.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends AndroidViewModel {

    public LiveData<User> perfilUsuario;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        perfilUsuario = getProfileData();
    }

    public LiveData<User> getProfileData() {


        return perfilUsuario;
    }

}
