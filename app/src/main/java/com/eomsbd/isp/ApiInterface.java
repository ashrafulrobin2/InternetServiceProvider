package com.eomsbd.isp;

import com.eomsbd.isp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("user-details/{id}")
    Call<User>getProfile(@Path("id") int id);
}
