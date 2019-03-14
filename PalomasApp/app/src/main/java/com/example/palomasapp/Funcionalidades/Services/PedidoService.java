package com.example.palomasapp.Funcionalidades.Services;

import com.example.palomasapp.Funcionalidades.Response.PedidoResponse;
import com.example.palomasapp.Funcionalidades.Response.ProductoResponse;
import com.example.palomasapp.Models.Pedido;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PedidoService {

    @GET("/pedidos")
    Call<ResponseContainer<PedidoResponse>> getPeidodos();

    @POST("/pedidos")
    Call<PedidoResponse> addPedidos(@Body Pedido p);

    @PUT("pedidos/{id}")
    Call<PedidoResponse> editOne(@Path("id") String id, @Body Pedido p);

    @DELETE("/pedidos/{id}")
    Call<PedidoResponse> deletePedido(@Path("id") String id);

}
