package com.example.palomasapp.Funcionalidades.Services;

import com.example.palomasapp.Models.Pedido;
import com.example.palomasapp.Models.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PedidoService {

    @GET("/pedidos")
    Call<ResponseContainer<Pedido>> getPeidodos();

    @POST("/pedidos")
    Call<Pedido> addPedidos(@Body Pedido p);

    @PUT("pedidos/{id}")
    Call<Pedido> editOne(@Path("id") String id, @Body Pedido p);

    @DELETE("/pedidos/{id}")
    Call<Pedido> deletePedido(@Path("id") String id);

}
