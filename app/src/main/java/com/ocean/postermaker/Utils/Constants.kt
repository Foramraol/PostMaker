package com.oceanmtech.dmt.Utils

import android.content.Context
import android.content.Intent
import com.ocean.postermaker.UI.LoginModule.LoginActivity
import java.io.File

object Constants {

    var FOLDER_PATH: File? = null
    var BUSINESS_LOGO_PATH: String? = null
    var BUSINESS_Profile_PATH: String? = null
    var fontfile = ArrayList<String>()

    fun fontLoad() {
        fontfile.add("logo_font1.ttf")
        fontfile.add("logo_font2.otf")
        fontfile.add("logo_font2.TTF")
        fontfile.add("logo_font3.ttf")
        fontfile.add("logo_font4.TTF")
        fontfile.add("logo_font5.ttf")
        fontfile.add("logo_font6.ttf")
        fontfile.add("logo_font7.ttf")
        fontfile.add("logo_font8.ttf")
        fontfile.add("logo_font9.ttf")
        fontfile.add("logo_font10.ttf")
        fontfile.add("logo_font11.ttf")
        fontfile.add("logo_font12.otf")
    }

    public fun logout(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        intent.putExtra("logout", "logout")
        context.startActivity(intent)
    }
}