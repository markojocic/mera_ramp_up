package com.example.markojocic.mera.jsonData;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Feed {

    @SerializedName("items")
    @Expose

    private ArrayList<Items> items;

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "items=" + items +
                '}';
    }
}
