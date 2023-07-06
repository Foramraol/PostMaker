package com.ocean.postermaker.UI.DashboardModule.SettingsModule.BusinessModule

import android.content.Intent
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
import com.ocean.postermaker.databinding.ActivityAddBusinessBinding
import com.ocean.postermaker.databinding.ActivitySelectProfileBinding

class SelectProfileActivity : BaseActivity() {

    val context: SelectProfileActivity = this@SelectProfileActivity
    private lateinit var binding: ActivitySelectProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_select_profile)
        NativeBanner.loadNativeBannerAds(context, binding.llAdContainerShare)
        binding.llAdContainerShare.visibility = View.VISIBLE;
        AdsUtils.showGoogleBannerAd(context, binding.adView)
        setTitle()
        onCLick()
    }

    private fun setTitle() {
        Log.d("TAG", "setTitle: ")
        binding.toolbar.ivBack.visibility = View.VISIBLE
        binding.toolbar.ivHome.visibility = View.VISIBLE
        binding.toolbar.tvTitle.text = "Select Profile"
        binding.toolbar.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivBack.setColorFilter(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivHome.setColorFilter(ContextCompat.getColor(context, R.color.white))
    }

    private fun onCLick() {
        binding.llBusiness.setOnClickListener {
//            startActivity(
//                Intent(context, AddBusinessActivity::class.java).putExtra(
//                    "From",
//                    "selectProfile"
//                ).putExtra("Category", "Business")
//            )
//            finish()
            var intent: Intent = Intent(context, AddBusinessActivity::class.java).putExtra(
                "From",
                "selectProfile"
            ).putExtra("Category", "Business")
            setResult(RESULT_OK, intent)
            finish()
        }
        binding.llPolitical.setOnClickListener {
//            startActivity(
//                Intent(context, AddBusinessActivity::class.java).putExtra(
//                    "From",
//                    "selectProfile"
//                ).putExtra("Category", "Business")
//            )
//            finish()
            var intent: Intent = Intent(context, AddBusinessActivity::class.java).putExtra(
                "From",
                "selectProfile"
            ).putExtra("Category", "Political")
            setResult(RESULT_OK, intent)
            finish()
        }
        binding.llProfessional.setOnClickListener {
//            startActivity(
//                Intent(context, AddBusinessActivity::class.java).putExtra(
//                    "From",
//                    "selectProfile"
//                ).putExtra("Category", "Business")
//            )
//            finish()
            var intent: Intent = Intent(context, AddBusinessActivity::class.java).putExtra(
                "From",
                "selectProfile"
            ).putExtra("Category", "Professional")
            setResult(RESULT_OK, intent)
            finish()
        }
        binding.llPersonal.setOnClickListener {
//            startActivity(
//                Intent(context, AddBusinessActivity::class.java).putExtra(
//                    "From",
//                    "selectProfile"
//                ).putExtra("Category", "Business")
//            )
//            finish()
            var intent: Intent = Intent(context, AddBusinessActivity::class.java).putExtra(
                "From",
                "selectProfile"
            ).putExtra("Category", "Personal")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}