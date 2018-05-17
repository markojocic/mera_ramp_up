package com.example.markojocic.mera.contactView;



import android.app.Activity;

import android.database.Cursor;

import android.os.Bundle;
import android.provider.ContactsContract;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;



import com.example.markojocic.mera.R;


import java.util.ArrayList;


public class ContactActivity extends Activity {

    public Contacts contacts;
    public ArrayList<Contacts> feedContacts = new ArrayList<>();
    private Boolean readContact = null;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);




        final RecyclerView recyclerViewContact =  findViewById(R.id.recyclerViewContact);
        recyclerViewContact.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewContact.setLayoutManager(layoutManager);


        final AdapterContact mAdapter = new AdapterContact(feedContacts, getApplicationContext());
        recyclerViewContact.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        Log.e("Permission","" + readContact);

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);



            while (phones.moveToNext()) {
                contacts = new Contacts();
                contacts.setContactName(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                contacts.setContactPhone(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                contacts.setContactPhoto(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)));
                feedContacts.add(phones.getPosition(), contacts);
                Log.e("contacts"," "+ contacts);

            }

            Log.e("FeedContacts", "--------" + feedContacts);

            phones.close();



    }

}