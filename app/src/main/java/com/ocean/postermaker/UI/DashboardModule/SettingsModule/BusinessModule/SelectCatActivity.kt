package com.ocean.postermaker.UI.DashboardModule.SettingsModule.BusinessModule

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.UI.DashboardModule.HomeActivity
import com.ocean.postermaker.databinding.ActivitySelectCatBinding

class SelectCatActivity : BaseActivity() {

    val context: SelectCatActivity = this@SelectCatActivity
    private lateinit var binding: ActivitySelectCatBinding
    var adapterSelectCat: AdapterSelectCat? = null
    var cat: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_select_cat)
        var bundel: Bundle? = intent.extras
        if (bundel != null) {
            cat = bundel.getString("cat").toString()
            Log.d("TAG", "onCreate: ::$cat")
        }
        setTitle()
        AdsUtils.showGoogleBannerAd(context, binding.adView)
        setListingCat()
        onCLick()
    }

    private fun setListingCat() {
        var list: ArrayList<String> = ArrayList()
        list.add("Gujarati")
        list.add("Hindi")
        list.add("English")
        list.add("French")
        list.add("Spanish")
        list.add("German")
        list.add("Malayalam")
        list.add("Gujarati")
        list.add("Hindi")
        list.add("English")
        list.add("French")
        list.add("Spanish")
        list.add("German")
        list.add("Malayalam")
        list.add(9,"ad")
        val spanCount = 3
        val layoutManager = GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, false)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position >= 9 && ((position - 9) % 10 == 0)) spanCount else 1
            }
        }
        binding.rvCategories.layoutManager = layoutManager
        adapterSelectCat =
            AdapterSelectCat(context, list, object : AdapterSelectCat.BtnClickListener {
                override fun onClick(
                    list: String,
                    position: Int,
                    mViewHolder: AdapterSelectCat.DataHolder
                ) {
                    adapterSelectCat!!.setSelected(position)
                    startActivity(Intent(context, AddNewBusinessActivity::class.java).putExtra("cat",cat))
                }
            })
        binding.rvCategories.adapter = adapterSelectCat
    }

    @SuppressLint("SetTextI18n")
    private fun setTitle() {
        Log.d("TAG", "setTitle: ")
        binding.toolbar.ivBack.visibility = View.VISIBLE
        binding.toolbar.ivHome.visibility = View.VISIBLE
        binding.toolbar.tvTitle.text = "Select $cat Category"
        binding.searchBox.edtSearch.hint = "Search your $cat category..."
        binding.toolbar.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivBack.setColorFilter(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivHome.setColorFilter(ContextCompat.getColor(context, R.color.white))
    }

    private fun onCLick() {
        binding.toolbar.ivHome.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
            finish()
        }
        binding.toolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}