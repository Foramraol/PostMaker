package com.ocean.postermaker.UI.DashboardModule.SettingsModule.BusinessModule

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.AdManager.NativeBanner
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.ActivityFinalViewBinding
import com.ocean.postermaker.databinding.ActivityPostStorySelectBinding

class FinalViewActivity : BaseActivity() {

    val context: FinalViewActivity = this@FinalViewActivity
    private lateinit var binding: ActivityFinalViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_final_view)
        setTitle()
        NativeBanner.loadNativeBannerAds(context, binding.llAdContainerShare)
        binding.llAdContainerShare.visibility = View.VISIBLE;
        onCLick()
    }

    @SuppressLint("SetTextI18n")
    private fun setTitle() {
        Log.d("TAG", "setTitle: ")
        binding.toolbar.ivBack.visibility = View.VISIBLE
        binding.toolbar.ivHome.visibility = View.GONE
        binding.toolbar.tvTitle.text ="Poster Maker"
        binding.toolbar.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivBack.setColorFilter(ContextCompat.getColor(context, R.color.white))
    }

    private fun onCLick() {
        binding.toolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}