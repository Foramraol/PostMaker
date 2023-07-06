package com.oceanmtech.dmt.Data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class ImportantDataManager(context: Context) {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("dssdslkhjud", MODE_PRIVATE)
    private val editor: SharedPreferences.Editor

    companion object{
        var loginStartTime = "loginStartTime"
        var HomeTime = "HomeTime"
        var loginStartTimeCounter = "StartTimeCounter"

    }
    init {
        editor = sharedPref.edit()
    }

    fun getAppLable(): String {
        return sharedPref.getString("pref_appLable", "").toString()
    }

    fun setAppLable(response: String) {
        editor.putString("pref_appLable", response).apply()
    }


    fun setAppLanguage(id: String) {
        editor.putString("pref_app_lanuage", id).apply()
    }

    fun getAppLanguage(): String {
        return sharedPref.getString("pref_app_lanuage", "1").toString()
    }

    fun clearData() {
        editor.clear();
        editor.commit();
    }
    fun setFirebaseToken(firebaseToken : String) {
        editor.putString("firebase_token", firebaseToken)
        editor.commit()
        editor.apply()
    }
    fun getFirebaseToken(): String {
        return sharedPref.getString("firebase_token","").toString()
    }


    fun setAction(action: String) {
        editor.putString("pref_action", action).apply()
    }

    fun getAction(): String {
        return sharedPref.getString("pref_action", "").toString()
    }

}