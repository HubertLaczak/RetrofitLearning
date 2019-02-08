package com.example.retrofitlib;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts") //reszta URL
    Call<List<Post>> getPosts();

    @GET("/typicode/demo/posts") //reszta URL
    Call<List<Demo>> getDemos();


}
