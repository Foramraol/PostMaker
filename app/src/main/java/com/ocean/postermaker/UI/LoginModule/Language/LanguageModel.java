package com.ocean.postermaker.UI.LoginModule.Language;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LanguageModel {

    @SerializedName("data")
    public ArrayList<Data> data;
    @SerializedName("message")
    public String message;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status")
    public int status;

    public static class Data {
        @SerializedName("total_post_video")
        public int total_post_video;
        @SerializedName("total_story_video")
        public int total_story_video;
        @SerializedName("total_post")
        public int total_post;
        @SerializedName("total_story")
        public int total_story;
        @SerializedName("image")
        public String image;
        @SerializedName("short_code")
        public String short_code;
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public int id;
    }
}
