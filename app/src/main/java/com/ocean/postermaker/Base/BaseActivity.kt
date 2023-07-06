package com.ocean.postermaker.Base

import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.oceanmtech.dmt.Data.DataManager
import com.oceanmtech.dmt.Network.API.ApiService
import org.koin.android.ext.android.get

open class BaseActivity : AppCompatActivity(){
    private val tag = BaseActivity::class.java.simpleName
    protected val dataManager: DataManager = get()
    protected val apiService: ApiService = get()

    fun screenOrientation() {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}