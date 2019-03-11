package com.example.palomasapp.Funcionalidades.Services;


import com.example.palomasapp.Funcionalidades.Responde.AuthAndRegisterResponse;
import com.example.palomasapp.Models.Register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthAndRegisterService {

    @POST("/auth")
    Call<AuthAndRegisterResponse> login(@Header("Authorization") String authorization);

    @POST("/users")
    Call<AuthAndRegisterResponse> register(@Body Register registro);
}
