package com.example.markojocic.mera.jsonData;




import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Url;


public interface GitAPI {

@GET

Call<Feed> getData(@Url String url);

}
