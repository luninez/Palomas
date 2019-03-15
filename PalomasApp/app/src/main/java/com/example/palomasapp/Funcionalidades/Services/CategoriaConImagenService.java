package com.example.palomasapp.Funcionalidades.Services;

import com.example.palomasapp.Funcionalidades.Response.CategoriaConImagenResponse;
import com.example.palomasapp.Models.CategoriaConImagen;
import com.example.palomasapp.Models.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoriaConImagenService {

    @GET("/categoriaConImagenes")
    Call<ResponseContainer<CategoriaConImagenResponse>> getCategorias();

    @POST("/categoriaConImagenes")
    Call<CategoriaConImagenResponse> addCategoria(@Body CategoriaConImagen c);

    @PUT("categoriaConImagenes/{id}")
    Call<CategoriaConImagenResponse> editCategoria(@Path("id") String id, @Body CategoriaConImagen c);

    @DELETE("/categoriaConImagenes/{id}")
    Call<CategoriaConImagenResponse> deteleCategoria(@Path("id") String id);

}
