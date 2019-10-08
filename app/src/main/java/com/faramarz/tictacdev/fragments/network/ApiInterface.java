package com.faramarz.tictacdev.fragments.network;

import com.faramarz.tictacdev.fragments.model.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("5d98dded340000bf1cf48ad1")
    Call<List<Car>> getCars();


}
