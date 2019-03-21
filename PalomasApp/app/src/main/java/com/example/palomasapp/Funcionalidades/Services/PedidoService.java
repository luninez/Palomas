package com.example.palomasapp.Funcionalidades.Services;

import com.example.palomasapp.Models.Pedido;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.ResponseContainer;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PedidoService {

    @GET("/pedidos")
    Call<ResponseContainer<Pedido>> getPedidos();

    @GET("/pedidos")
    Call<ResponseContainer<Pedido>> getPedidosEstado(@Query("estado") String estado);

    @GET("/pedidos/{usuarioId}")
    Call<ResponseContainer<Pedido>> getPedidosUsuarioEstado(@Path("usuarioId") String usuarioId, @Query("estado") String estado);

    @POST("/pedidos")
    Call<Pedido> addPedidos(@Body Pedido p);

    @PUT("pedidos/{id}")
    Call<Pedido> editPedido(@Path("id") String id, @Body Pedido p);

    @DELETE("/pedidos/{id}")
    Call<Pedido> deletePedido(@Path("id") String id);

}
