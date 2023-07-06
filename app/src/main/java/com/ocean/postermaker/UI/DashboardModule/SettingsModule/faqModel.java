package com.ocean.postermaker.UI.DashboardModule.SettingsModule;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class faqModel {

    @SerializedName("data")
    public ArrayList<Data> data;
    @SerializedName("message")
    public String message;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status")
    public int status;

    public static class Data {
        @SerializedName("video_url")
        public String video_url;
        @SerializedName("audio_url")
        public String audio_url;
        @SerializedName("image_url")
        public String image_url;
        @SerializedName("description")
        public String description;
        @SerializedName("title")
        public String title;
        @SerializedName("id")
        public int id;
    }
}
