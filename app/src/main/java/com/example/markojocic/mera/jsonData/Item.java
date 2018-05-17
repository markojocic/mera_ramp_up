package com.example.markojocic.mera.jsonData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("owner")
    @Expose
    private Owner owner;

    @SerializedName("size")
    @Expose
    private int size;

    @SerializedName("has_wiki")
    @Expose
    private Boolean has_wiki;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Boolean getHas_wiki() {
        return has_wiki;
    }

    public void setHas_wiki(Boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    @Override
    public String toString() {
        return "Items{" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                ", size=" + size +
                ", has_wiki=" + has_wiki +
                '}';
    }
}
