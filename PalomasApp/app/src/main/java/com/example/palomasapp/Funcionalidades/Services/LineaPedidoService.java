package com.example.palomasapp.Funcionalidades.Services;

import com.example.palomasapp.Models.LineaPedido;
import com.example.palomasapp.Models.Pedido;
import com.example.palomasapp.Models.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LineaPedidoService {

    @GET("/linea_pedidos")
    Call<ResponseContainer<LineaPedido>> getLineaPedidos();

    @GET("/linea_pedidos/{pedidoId}")
    Call<ResponseContainer<LineaPedido>> getLineaPedidosPedioId(@Path("pedidoId") String pedidoId);

    @POST("/linea_pedidos")
    Call<LineaPedido> addLineaPedidos(@Body LineaPedido p);

    @PUT("/linea_pedidos/{id}")
    Call<LineaPedido> editLineaPedidos(@Path("id") String id, @Body LineaPedido p);

    @DELETE("/linea_pedidos/{id}")
    Call<LineaPedido> deleteLineaPedidos(@Path("id") String id);

}
