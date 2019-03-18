package com.example.palomasapp.Funcionalidades.Services;

import com.example.palomasapp.Funcionalidades.Response.LineaPedidosResponse;
import com.example.palomasapp.Funcionalidades.Response.PedidoResponse;
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

public interface LineaPedidoService {

    @GET("/linea_pedidos")
    Call<ResponseContainer<LineaPedidosResponse>> getLineaPedidos();

    @POST("/linea_pedidos")
    Call<LineaPedidosResponse> addLineaPedidos(@Body LineaPedido p);

    @PUT("/linea_pedidos/{id}")
    Call<LineaPedidosResponse> editLineaPedidos(@Path("id") String id, @Body LineaPedido p);

    @DELETE("/linea_pedidos/{id}")
    Call<LineaPedidosResponse> deleteLineaPedidos(@Path("id") String id);

}
