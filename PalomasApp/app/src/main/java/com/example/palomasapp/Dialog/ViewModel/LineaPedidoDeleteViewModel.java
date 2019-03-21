package com.example.palomasapp.Dialog.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.LineaPedidoService;
import com.example.palomasapp.Funcionalidades.Util;
import com.example.palomasapp.Models.LineaPedido;
import com.example.palomasapp.Models.TipoAutenticacion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LineaPedidoDeleteViewModel extends AndroidViewModel {

    public LineaPedidoDeleteViewModel(@NonNull Application application) {super(application);}

    public void deleteLienaPedido(String nombre, String id, final DialogInterface dialog) {
        LineaPedidoService service = ServiceGenerator.createService(LineaPedidoService.class, Util.getToken(getApplication().getApplicationContext()), TipoAutenticacion.JWT);
        Call<LineaPedido> call = service.deleteLineaPedidos(id);

        call.enqueue(new Callback<LineaPedido>() {
            @Override
            public void onResponse(Call<LineaPedido> call, Response<LineaPedido> response) {
                if(response.isSuccessful()) {
                    dialog.dismiss();
                } else {
                    Toast.makeText(getApplication().getApplicationContext(), "Error al borrar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LineaPedido> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
