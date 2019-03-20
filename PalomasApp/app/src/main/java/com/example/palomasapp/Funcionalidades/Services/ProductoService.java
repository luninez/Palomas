package com.example.palomasapp.Funcionalidades.Services;

import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.ResponseContainer;
import com.example.palomasapp.Models.User;

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
    Call<ResponseContainer<Producto>> getProductos();

    @GET("/productos/{id}")
    Call<Producto> getOneProducto(@Query("nombre") String nombre);

    @POST("/productos")
    Call<Producto> addProducto(@Body Producto p);

    @PUT("productos/{id}")
    Call<Producto> editProducto(@Path("id") String id, @Body Producto p);

    @DELETE("/productos/{id}")
    Call<Producto> deleteProducto(@Path("id") String id);

    @POST("productos/fav/{id}")
    Call<User> addToFav(@Path("id") String id);

    @DELETE("productos/fav/{id_property}")
        // @HTTP(method = "DELETE", path = "/properties/fav/{id_property}", hasBody = true)
    Call<User> deleteFav(@Path("id_property") String id_property);

}
