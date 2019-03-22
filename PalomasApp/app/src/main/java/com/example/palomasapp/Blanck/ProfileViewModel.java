package com.example.palomasapp.Blanck;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.ProductoService;
import com.example.palomasapp.Funcionalidades.Services.UserService;
import com.example.palomasapp.Funcionalidades.Util;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.TipoAutenticacion;
import com.example.palomasapp.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends AndroidViewModel {

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void editPerfil(User u, String id, final DialogInterface dialog){
        UserService service = ServiceGenerator.createService(UserService.class, Util.getToken(getApplication().getApplicationContext()), TipoAutenticacion.JWT);
        Call<User> call = service.editUser(id, u);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    dialog.dismiss();
                }else{
                    Toast.makeText(getApplication().getApplicationContext(), "Error al editar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
