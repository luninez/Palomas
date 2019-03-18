package com.example.palomasapp.Dialog;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.palomasapp.Funcionalidades.Response.ProductoResponse;
import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.ProductoService;
import com.example.palomasapp.Funcionalidades.Util;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.TipoAutenticacion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoEditViewModel extends AndroidViewModel {

    public ProductoEditViewModel(@NonNull Application application) { super(application); }

    public void editProducto(Producto p, String id, final DialogInterface dialog) {
        ProductoService service = ServiceGenerator.createService(ProductoService.class, Util.getToken(getApplication().getApplicationContext()), TipoAutenticacion.JWT);
        Call<ProductoResponse> call = service.editProducto(id, p);

        call.enqueue(new Callback<ProductoResponse>() {
            @Override
            public void onResponse(Call<ProductoResponse> call, Response<ProductoResponse> response) {
                if(response.isSuccessful()) {
                    dialog.dismiss();
                } else {
                    Toast.makeText(getApplication().getApplicationContext(), "Error al editar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductoResponse> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
