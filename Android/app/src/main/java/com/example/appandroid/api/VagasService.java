package com.example.appandroid.api;

import com.example.appandroid.model.Interesse;
import com.example.appandroid.model.Vaga;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VagasService {
    @GET("vagas")
    Call<List<Vaga>> getVagas();

    @POST("vagas/interesse")
    Call<Void> registrarInteresse(@Body Interesse interesse);
}