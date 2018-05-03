package com.example.markojocic.mera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button searchButton = findViewById(R.id.searchButton);
        final MultiAutoCompleteTextView inputSearch = findViewById(R.id.inputSearchText);


        // timer for internet connection
        Timer internetConnectionTimer = new Timer();

        final Toast internetConnectionToast =  Toast.makeText(getApplicationContext(), "Not Connected" , Toast.LENGTH_LONG);

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








        //button handler
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String getInputSearch = inputSearch.getText().toString();

                Toast.makeText(getApplicationContext(), "http" + getInputSearch, Toast.LENGTH_LONG).show();
            }
        });


    }








}
