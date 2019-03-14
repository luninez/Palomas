package com.example.palomasapp.Funcionalidades.Services;

import android.media.Image;

import com.example.palomasapp.Funcionalidades.Response.CategoriaResponse;
import com.example.palomasapp.Funcionalidades.Response.ImageResponse;
import com.example.palomasapp.Models.Categoria;
import com.example.palomasapp.Models.Imagen;
import com.example.palomasapp.Models.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoriaService {

    @GET("/categorias")
    Call<ResponseContainer<CategoriaResponse>> getCategorias();

    @POST("/categorias")
    Call<CategoriaResponse> addCategoria(@Body Categoria c);

    @PUT("categorias/{id}")
    Call<CategoriaResponse> editCategoria(@Path("id") String id, @Body Categoria c);

    @DELETE("/categorias/{id}")
    Call<CategoriaResponse> deteleCategoria(@Path("id") String id);

}
