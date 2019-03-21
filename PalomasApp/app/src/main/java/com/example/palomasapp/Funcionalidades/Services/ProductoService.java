package com.example.palomasapp.Funcionalidades.Services;

import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.ResponseContainer;
import com.example.palomasapp.Models.User;

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

public interface ProductoService {

    @GET("/productos")
    Call<ResponseContainer<Producto>> getProductos();

    @GET("/productos")
    Call<ResponseContainer<Producto>> getBuscarProductos(@Query("nombre") String nombre);

    @GET("/productos/{categoriaId}")
    Call<ResponseContainer<Producto>> getFiltrarCategoria(@Path("categoriaId") String categoriaId);

    @GET("/productos/{id}")
    Call<Producto> getOneProducto(@Path("nombre") String nombre);

    @POST("/productos")
    Call<Producto> addProducto(@Body Producto p);

    @PUT("productos/{id}")
    Call<Producto> editProducto(@Path("id") String id, @Body Producto p);

    @DELETE("/productos/{id}")
    Call<Producto> deleteProducto(@Path("id") String id);

}
