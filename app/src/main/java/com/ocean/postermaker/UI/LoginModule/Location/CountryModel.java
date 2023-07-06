package com.ocean.postermaker.UI.LoginModule.Location;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CountryModel {

    @SerializedName("data")
    public ArrayList<Data> data;
    @SerializedName("message")
    public String message;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status")
    public int status;

    public static class Data {
        @SerializedName("flag")
        public String flag;
        @SerializedName("code")
        public int code;
        @SerializedName("sortname")
        public String sortname;
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public int id;
    }
}
