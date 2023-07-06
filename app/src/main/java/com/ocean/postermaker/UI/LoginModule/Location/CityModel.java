package com.ocean.postermaker.UI.LoginModule.Location;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CityModel {

    @SerializedName("data")
    public ArrayList<Data> data;
    @SerializedName("message")
    public String message;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status")
    public int status;

    public static class Data {
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public int id;
    }
}
