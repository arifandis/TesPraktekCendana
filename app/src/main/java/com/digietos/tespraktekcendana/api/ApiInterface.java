package com.digietos.tespraktekcendana.api;

import com.digietos.tespraktekcendana.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/users")
    Call<List<User>> getUsers();
}
