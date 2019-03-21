package com.example.palomasapp.Funcionalidades.Services;

import com.example.palomasapp.Models.ResponseContainer;
import com.example.palomasapp.Models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    @GET("/users")
    Call<User> getUser();

    @GET("/users/{id}")
    Call<User> getOneUser(@Path("id") String id);

}