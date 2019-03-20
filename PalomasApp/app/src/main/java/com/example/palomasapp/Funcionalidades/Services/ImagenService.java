package com.example.palomasapp.Funcionalidades.Services;

import android.media.Image;

import com.example.palomasapp.Funcionalidades.Response.ImageResponse;
import com.example.palomasapp.Models.Imagen;
import com.example.palomasapp.Models.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ImagenService {

    @GET("/imagenes")
    Call<ResponseContainer<ImageResponse>> getImagen();

    @POST("/imagenes")
    Call<ImageResponse> addImagen(@Body Imagen i);

    @PUT("imagenes/{id}")
    Call<ImageResponse> editImagen(@Path("id") String id, @Body Image i);

    @DELETE("/imagenes/{id}")
    Call<ImageResponse> deleteImagen(@Path("id") String id);

}
