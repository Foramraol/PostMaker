package com.ocean.postermaker.UI.DashboardModule.SettingsModule

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.AdManager.NativeBanner
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.UI.DashboardModule.SettingsModule.BusinessModule.AddNewBusinessActivity
import com.ocean.postermaker.databinding.ActivityAddNewBusinessBinding
import com.ocean.postermaker.databinding.ActivityFeedbackBinding

class FeedbackActivity : BaseActivity() {

    val context: FeedbackActivity = this@FeedbackActivity
    private lateinit var binding: ActivityFeedbackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_feedback)
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
        binding.toolbar.tvTitle.text = "Feedback"
        binding.toolbar.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivBack.setColorFilter(ContextCompat.getColor(context, R.color.white))
    }

    private fun onCLick() {
        binding.toolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.tvSave.setOnClickListener {
            onBackPressed()
//            startActivity(Intent(context, HomeActivity::class.java))
//            finish()
        }
    }
}