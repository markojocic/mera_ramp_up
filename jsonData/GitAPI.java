package com.example.markojocic.mera.jsonData;



import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.Call;
import retrofit2.http.Query;



public interface GitAPI {
    String BASE_URL = "https://api.github.com/";


    @Headers("Content-Type: application/json")
    @GET("search/repositories?q=tetris")
    Call<Feed> getData();




    //Call<Feed> getData();

}
