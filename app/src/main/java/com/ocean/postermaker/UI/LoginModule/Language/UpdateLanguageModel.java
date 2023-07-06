package com.ocean.postermaker.UI.LoginModule.Language;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UpdateLanguageModel {

    @SerializedName("data")
    public ArrayList<String> data;
    @SerializedName("message")
    public String message;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status")
    public int status;
}
