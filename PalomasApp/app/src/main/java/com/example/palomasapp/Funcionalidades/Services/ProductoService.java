package com.example.palomasapp.Funcionalidades.Services;

import com.example.palomasapp.Funcionalidades.Response.ProductoResponse;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.ResponseContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductoService {

    @GET("/productos")
    Call<ResponseContainer<ProductoResponse>> getProductos();

    @GET("/productos/{id}")
    Call<Producto> getOneProducto(@Query("nombre") String nombre);

    @POST("/productos")
    Call<Producto> addProducto(@Body Producto p);

    @PUT("productos/{id}")
    Call<ProductoResponse> editProducto(@Path("id") String id, @Body Producto p);

    @DELETE("/productos/{id}")
    Call<ProductoResponse> deleteProducto(@Path("id") String id);

}
