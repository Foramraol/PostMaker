package com.ocean.postermaker.UI.DashboardModule.SettingsModule;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProfileModel {

    @SerializedName("data")
    public Data data;
    @SerializedName("message")
    public String message;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status")
    public int status;

    public static class Data {
        @SerializedName("user_plans")
        public ArrayList<User_plans> user_plans;
        @SerializedName("total_points")
        public int total_points;
        @SerializedName("sister")
        public ArrayList<String> sister;
        @SerializedName("brother")
        public ArrayList<String> brother;
        @SerializedName("children")
        public ArrayList<String> children;
        @SerializedName("city_name")
        public String city_name;
        @SerializedName("state_name")
        public String state_name;
        @SerializedName("country_name")
        public String country_name;
        @SerializedName("religion_name")
        public String religion_name;
        @SerializedName("user_language_short_code")
        public String user_language_short_code;
        @SerializedName("user_language_name")
        public String user_language_name;
        @SerializedName("referral_code")
        public String referral_code;
        @SerializedName("spouse_name")
        public String spouse_name;
        @SerializedName("mother_name")
        public String mother_name;
        @SerializedName("father_name")
        public String father_name;
        @SerializedName("join_date")
        public String join_date;
        @SerializedName("logo")
        public String logo;
        @SerializedName("anniversary_date")
        public String anniversary_date;
        @SerializedName("date_of_birth")
        public String date_of_birth;
        @SerializedName("gender")
        public String gender;
        @SerializedName("city_id")
        public int city_id;
        @SerializedName("state_id")
        public int state_id;
        @SerializedName("country_id")
        public int country_id;
        @SerializedName("religion_id")
        public int religion_id;
        @SerializedName("user_language_id")
        public int user_language_id;
        @SerializedName("email")
        public String email;
        @SerializedName("mobile_no")
        public String mobile_no;
        @SerializedName("name")
        public String name;
        @SerializedName("is_tour_completed")
        public int is_tour_completed;
        @SerializedName("is_paid")
        public String is_paid;
        @SerializedName("member_name")
        public String member_name;
        @SerializedName("member_id")
        public String member_id;
        @SerializedName("type")
        public String type;
        @SerializedName("id")
        public int id;
    }

    public static class User_plans {
        @SerializedName("plan")
        public Plan plan;
        @SerializedName("plan_name")
        public String plan_name;
        @SerializedName("business_name")
        public String business_name;
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("status")
        public String status;
        @SerializedName("payment_status")
        public String payment_status;
        @SerializedName("payment_response")
        public String payment_response;
        @SerializedName("transaction_id")
        public String transaction_id;
        @SerializedName("plan_data")
        public String plan_data;
        @SerializedName("psa")
        public String psa;
        @SerializedName("pta")
        public String pta;
        @SerializedName("epfa")
        public String epfa;
        @SerializedName("mba")
        public int mba;
        @SerializedName("pcwid")
        public int pcwid;
        @SerializedName("amount")
        public int amount;
        @SerializedName("duration_type")
        public String duration_type;
        @SerializedName("duration")
        public int duration;
        @SerializedName("end_date")
        public String end_date;
        @SerializedName("start_date")
        public String start_date;
        @SerializedName("business_id")
        public int business_id;
        @SerializedName("is_business_plan")
        public int is_business_plan;
        @SerializedName("plan_id")
        public int plan_id;
        @SerializedName("user_id")
        public int user_id;
        @SerializedName("id")
        public int id;
    }

    public static class Plan {
        @SerializedName("plan_description")
        public String plan_description;
        @SerializedName("plan_duration")
        public String plan_duration;
        @SerializedName("plan_name")
        public String plan_name;
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("status")
        public String status;
        @SerializedName("psa")
        public String psa;
        @SerializedName("pta")
        public String pta;
        @SerializedName("epfa")
        public String epfa;
        @SerializedName("mba")
        public String mba;
        @SerializedName("pcwid")
        public String pcwid;
        @SerializedName("amount")
        public String amount;
        @SerializedName("duration_type")
        public String duration_type;
        @SerializedName("duration")
        public int duration;
        @SerializedName("is_business_plan")
        public int is_business_plan;
        @SerializedName("is_default")
        public int is_default;
        @SerializedName("id")
        public int id;
    }
}
