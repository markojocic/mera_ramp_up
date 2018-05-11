package com.example.markojocic.mera.contactView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedContacts {

    @SerializedName("contacts")
    @Expose
    ArrayList<Contacts>  contacts;

    public ArrayList<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "FeedContacts{" +
                "contacts=" + contacts +
                '}';
    }
}
