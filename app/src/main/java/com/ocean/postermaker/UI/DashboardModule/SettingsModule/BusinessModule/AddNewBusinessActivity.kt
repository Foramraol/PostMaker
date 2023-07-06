package com.ocean.postermaker.UI.DashboardModule.SettingsModule.BusinessModule

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.UI.DashboardModule.HomeActivity
import com.ocean.postermaker.UI.DashboardModule.SettingsModule.FeedbackActivity
import com.ocean.postermaker.databinding.ActivityAddNewBusinessBinding
import com.ocean.postermaker.databinding.ActivitySelectCatBinding

class AddNewBusinessActivity : BaseActivity() {

    val context: AddNewBusinessActivity = this@AddNewBusinessActivity
    private lateinit var binding: ActivityAddNewBusinessBinding
    var cat: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_add_new_business)
        var bundel: Bundle? = intent.extras
        if (bundel != null) {
            cat = bundel.getString("cat").toString()
            Log.d("TAG", "onCreate: ::$cat")
        }
        setTitle()
        AdsUtils.showGoogleBannerAd(context, binding.adView)
        onCLick()
    }

    @SuppressLint("SetTextI18n")
    private fun setTitle() {
        Log.d("TAG", "setTitle: ")
        binding.toolbar.ivBack.visibility = View.VISIBLE
        binding.toolbar.ivHome.visibility = View.GONE
        binding.toolbar.tvTitle.text = "Add New Business"
        binding.toolbar.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivBack.setColorFilter(ContextCompat.getColor(context, R.color.white))
    }

    private fun onCLick() {
        binding.toolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.tvSave.setOnClickListener {
            startActivity(Intent(context, PostStorySelectActivity::class.java))
        }
    }
}