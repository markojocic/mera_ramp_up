package com.example.markojocic.mera;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import com.example.markojocic.mera.contactView.ContactActivity;
import com.example.markojocic.mera.jsonData.AdapterJson;
import com.example.markojocic.mera.jsonData.Feed;
import com.example.markojocic.mera.jsonData.Item;

import com.example.markojocic.mera.jsonData.JsonData;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    private AdapterJson adapter;
    private int urlPer_page = 10;
    private int urlPage = 1;
    private  Boolean loadingData = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.e("msg", "on create");



        final Button searchButton = findViewById(R.id.searchButton);
        final MultiAutoCompleteTextView inputSearch = findViewById(R.id.inputSearchText);
        final Button contactButton = findViewById(R.id.contactButton);

        //Notification  on five min not included timer
        final Notification5min notification5min1 = new Notification5min(this);

        final RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);



        // use a linear layout manager
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);



        final JsonData jsonData = new JsonData();
        jsonData.setResponseCallback(responseCallback);

        adapter = new AdapterJson();
        recyclerView.setAdapter(adapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                if(!recyclerView.canScrollVertically(1) && !loadingData){

                    //create new call and update notify data change

                    // git give only first 1000 items, 10 pages and 100 items per page
                  urlPer_page = urlPer_page +10;

                  if (urlPer_page > 100 && urlPage <= 10){

                      urlPage = urlPage + 1;
                      urlPer_page = 10;
                  }


                    String getInputSearch = inputSearch.getText().toString();
                    jsonData.jsonData(getInputSearch + "&page=" + urlPage +"&per_page=" + urlPer_page);
                    loadingData = true;



                    Log.e("Scroll","***********");
                }


            }
        });



        // Timer for internet connection check
        Timer internetConnectionTimer = new Timer();

        final Toast internetConnectionToast =  Toast.makeText(getApplicationContext(), "No Internet Connection" , Toast.LENGTH_LONG);

        final ConnectionDetector cd = new ConnectionDetector(this);

        internetConnectionTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                // if state for checking connect, if no connection show ICT msg else do nothing...

                if(!cd.isConnected()){
                    internetConnectionToast.show();
                }

            }
        },0, 5000);


        // Timer for Notification
        Timer notificationTimer = new Timer();

        notificationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                notification5min1.setNotification5min("Content Text", "Content title");
            }
        }, 60000, 3000000);



        //Search button handler
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputSearch.getText().length() > 0) {

                    Log.e("Button click ", "get text from input\n");
                    String getInputSearch = inputSearch.getText().toString();
                    jsonData.jsonData(getInputSearch + "&page=1&per_page=10");
                    urlPage = 1;
                    urlPer_page = 10;
                }else{
                    Toast.makeText(MainActivity.this, "Please insert more then one character!", Toast.LENGTH_SHORT).show();
                }



            }
        });

        // Open New activity Contacts Button handler
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Asking for permission to start activity
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);


               // Intent mIntent = new Intent(MainActivity.this, ContactActivity.class);
                //startActivity(mIntent);

            }
        });



    }

    @Override
    protected void onStart(){
        super.onStart();
        responseCallback.setAdapter(adapter);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted

                    //Toast.makeText(MainActivity.this, "Permission granted to read your Contacts", Toast.LENGTH_SHORT).show();
                    Intent mIntent = new Intent(MainActivity.this, ContactActivity.class);
                    startActivity(mIntent);



                } else {

                    // permission denied,

                    Toast.makeText(MainActivity.this, "Permission denied to read your Contacts", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
    }

    // Response CallBack

    private ResponseCallBack responseCallback = new ResponseCallBack();
//STATIC DELETED
    private  class ResponseCallBack implements Callback<Feed>{

        private AdapterJson adapter;

        void setAdapter(AdapterJson adapter){

            this.adapter = adapter;

        }

        @Override
        public void onResponse(Call<Feed> call, Response<Feed> response) {
            Log.e("RESPONSE", "On Response ----------->");


           // ArrayList<Item> items = response.body().getItems();
            Log.e("Items size", ""+response.body().getItems().size());

            if (adapter != null){

                adapter.setAdapterJson(response.body().getItems());
                adapter.notifyDataSetChanged();
                loadingData = false;

            }


        }

        @Override
        public void onFailure(Call<Feed> call, Throwable t) {

            Log.e("FAIL", "ON Fail ------>" + t.getMessage());

            //loadingData = true;
        }

    }


    @Override
    protected void onStop(){
        super.onStop();
        responseCallback.setAdapter(null);
    }

}
