package com.example.markojocic.mera.contactView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contacts {


    @SerializedName("contactName")
    @Expose
    private String contactName;


    @SerializedName("contactPhone")
    @Expose
    private String contactPhone;

    @SerializedName("contactPhoto")
    @Expose
    private String getContactPhoto;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getGetContactPhoto() {
        return getContactPhoto;
    }

    public void setGetContactPhoto(String getContactPhoto) {
        this.getContactPhoto = getContactPhoto;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", getContactPhoto='" + getContactPhoto + '\'' +
                '}';
    }
}
