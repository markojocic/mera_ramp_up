package com.example.markojocic.mera;


import android.content.Intent;
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
import com.example.markojocic.mera.jsonData.JsonData;

import java.util.Timer;
import java.util.TimerTask;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart(){
        super.onStart();
        Log.e("msg", "on start");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final Button searchButton = findViewById(R.id.searchButton);
        final MultiAutoCompleteTextView inputSearch = findViewById(R.id.inputSearchText);
        final Button contactButton = findViewById(R.id.contactButton);

        //Notification  on five min not included timer
        final Notification5min notification5min1 = new Notification5min(this);

        final RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);



        final JsonData jsonData = new JsonData();
        jsonData.jsonData("https://api.github.com/search/repositories?q=tetris");
        final AdapterJson mAdapter = new AdapterJson();
        mAdapter.SetAdapterJson(jsonData.itemsList);
        recyclerView.setAdapter(mAdapter);
      //  https://api.github.com/search/repositories?q=tetris
        //



        // Timer for internet connection check
        Timer internetConnectionTimer = new Timer();

        final Toast internetConnectionToast =  Toast.makeText(getApplicationContext(), "No Internet Connection" , Toast.LENGTH_LONG);

        final ConnectionDetector cd = new ConnectionDetector(this);

        internetConnectionTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                // if state for checking connectiviti, if no connection show ICT msg else do nothing...

                if(!cd.isConnected()){
                    internetConnectionToast.show();
                }

            }
        },0, 5000);







        //Search button handler
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Button click ", "get text from input\n");
                 String getInputSearch = inputSearch.getText().toString();

                //Toast.makeText(getApplicationContext(), "http" + getInputSearch, Toast.LENGTH_LONG).show();

                // test for notification when button is click

               // notification5min1.setNotification5min("Content Text", "Content title");





                jsonData.jsonData(getInputSearch);


                mAdapter.SetAdapterJson(jsonData.itemsList);


                recyclerView.setAdapter(mAdapter);






            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(mIntent);

            }
        });



    }








}
