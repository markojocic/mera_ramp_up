package com.example.markojocic.mera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.example.markojocic.mera.jsonData.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final Button searchButton = findViewById(R.id.searchButton);
        final MultiAutoCompleteTextView inputSearch = findViewById(R.id.inputSearchText);


        //Notification  on five min not included timer
        final Notification5min notification5min1 = new Notification5min(this);

        final JsonData jsonData = new JsonData();









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
                 String getInputSearch = inputSearch.getText().toString();

                Toast.makeText(getApplicationContext(), "http" + getInputSearch, Toast.LENGTH_LONG).show();

                // test for notification when button is click

                notification5min1.setNotification5min("Content Text", "Content title");

                //test for Json data
                jsonData.searchQ = "tetris2";
                jsonData.jsonData();


            }
        });


    }








}
