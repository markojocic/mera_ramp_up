package com.example.markojocic.mera;

import android.util.Log;
import android.widget.Toast;

import com.example.markojocic.mera.jsonData.Feed;
import com.example.markojocic.mera.jsonData.GitAPI;
import com.example.markojocic.mera.jsonData.Items;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class JsonData {


    final String BASE_URL = "https://api.github.com/";
    final String TAG = "MainActivity";
    String searchQ;

    public JsonData() {

    }

    public  void jsonData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitAPI gitAPI = retrofit.create(GitAPI.class);
        Call<Feed> call = gitAPI.getData();

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.e(TAG, "On Response ----------->" +  response.body().toString());

                ArrayList<Items> itemsList = response.body().getItems();
                for( int i = 0; i<itemsList.size(); i++){
                    Log.d(TAG, "ON RESPONSE \n" +
                            "Name " + itemsList.get(i).getName() + "\n"
                            + "----------\n" +
                            "Size " + itemsList.get(i).getSize() + "\n"
                            + "-----------\n"+
                            "Has_Wiki " + itemsList.get(i).getHas_wiki() + "\n"
                            + "-----------\n"+
                            "Owner  " + itemsList.get(i).getOwner().getLogin() + "\n"

                            + "------------------------------------\n");
                }

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {

                Log.e(TAG, "ON Fail ------>" + t.getMessage());


            }
        });

    }
}
