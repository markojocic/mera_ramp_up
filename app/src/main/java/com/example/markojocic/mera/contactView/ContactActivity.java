package com.example.markojocic.mera.contactView;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.support.v4.app.ActivityCompat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.example.markojocic.mera.R;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;


public class ContactActivity extends Activity {
    public ArrayList<String> contactName = new ArrayList<>();
    public ArrayList<String> contactPhone = new ArrayList<>();
    public ArrayList<String> contactPhoto = new ArrayList<>();

    //public ArrayList<Contacts> contacts = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);



        final RecyclerView recyclerViewContact =  findViewById(R.id.recyclerViewContact);
        recyclerViewContact.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewContact.setLayoutManager(layoutManager);


        final AdapterContact mAdapter = new AdapterContact(contactName, contactPhone, contactPhoto, getApplicationContext());
        recyclerViewContact.setAdapter(mAdapter);






        ActivityCompat.requestPermissions(ContactActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);






    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted


                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);

                        contactName.clear();

                    while (phones.moveToNext())
                    {
                            /// bug when last name is empty not showing items in rv, prob geting null object
                        if(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)) != null){

                            contactName.add(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));

                        }else{
                            contactName.add("nema");
                        }

                        //contactName.add(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                        contactPhone.add(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                        contactPhoto.add(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)));

                    }

                    Log.e("","--------" + contactName);

                    phones.close();





                } else {

                    // permission denied,
                    Toast.makeText(ContactActivity.this, "Permission denied to read your Contacts", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
    }

}