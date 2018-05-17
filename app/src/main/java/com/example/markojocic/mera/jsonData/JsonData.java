package com.example.markojocic.mera.jsonData;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class JsonData extends ArrayList<Item> {


    final String BASE_URL = "https://api.github.com/";
    final String TAG = "MainActivity";



    public ArrayList<Item> itemsList= new ArrayList();


    public void JsonData() {

    }

    private Callback<Feed> responseCallback;

    public ArrayList<Item> jsonData(String url) {
        String Url = "https://api.github.com/search/repositories?q=" + url;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        GitAPI gitAPI = retrofit.create(GitAPI.class);

        Call<Feed> call = gitAPI.getData(Url);
        Log.e("API interface", "Api added \n");


        call.enqueue(responseCallback);

        return null;
    }

    public void setResponseCallback(Callback<Feed> responseCallback) {
        this.responseCallback = responseCallback;
    }

}
