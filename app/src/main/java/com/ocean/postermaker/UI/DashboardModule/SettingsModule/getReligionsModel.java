package com.ocean.postermaker.UI.DashboardModule.SettingsModule;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class getReligionsModel {

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
