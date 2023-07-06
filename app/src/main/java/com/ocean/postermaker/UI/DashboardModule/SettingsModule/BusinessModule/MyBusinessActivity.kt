package com.ocean.postermaker.UI.DashboardModule.SettingsModule.BusinessModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.UI.DashboardModule.HomeActivity
import com.ocean.postermaker.UI.LoginModule.Language.AdapterLanguage
import com.ocean.postermaker.UI.LoginModule.Language.SelectLanguageActivity
import com.ocean.postermaker.UI.LoginModule.Location.SelectLocationActivity
import com.ocean.postermaker.databinding.ActivityMyBusinessBinding
import com.ocean.postermaker.databinding.ActivitySelectLanguageBinding

class MyBusinessActivity : BaseActivity() {

    val context: MyBusinessActivity = this@MyBusinessActivity
    private lateinit var binding: ActivityMyBusinessBinding
    var adapterBusiness: AdapterBusiness? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_my_business)
        setTitle()
//        AdsUtils.showGoogleBannerAd(context, binding.adView)
        setListingLang()
        onCLick()
    }

    private fun setListingLang() {
        var list: ArrayList<String> = ArrayList()
        list.add("Gujarati")
        list.add("Hindi")
        list.add("English")
        list.add("French")
        list.add("Spanish")
        list.add("German")
        list.add("Malayalam")
        binding.rvBusinessList.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        adapterBusiness = AdapterBusiness(context, list, object : AdapterBusiness.BtnClickListener {
            override fun onClick(
                list: String,
                position: Int,
                mViewHolder: AdapterBusiness.DataHolder
            ) {
                adapterBusiness!!.setSelected(position)
            }
        })
        binding.rvBusinessList.adapter = adapterBusiness
    }

    private fun setTitle() {
        Log.d("TAG", "setTitle: ")
        binding.toolbar.ivBack.visibility = View.VISIBLE
        binding.toolbar.ivHome.visibility = View.VISIBLE
        binding.toolbar.tvTitle.text = "My Business"
        binding.toolbar.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivBack.setColorFilter(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivHome.setColorFilter(ContextCompat.getColor(context, R.color.white))
    }

    private fun onCLick() {
        binding.toolbar.ivHome.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
            finish()
        }
        binding.ivAdd.setOnClickListener {
            startActivity(Intent(context, AddBusinessActivity::class.java))
            finish()
        }
        binding.toolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}