package com.ocean.postermaker.UI.DashboardModule.SettingsModule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.ActivitySelectReligionBinding
import com.oceanmtech.dmt.Utils.Utils
import com.oceanmtech.dmt.Utils.dismissLoader
import com.oceanmtech.dmt.Utils.displayToast
import com.oceanmtech.dmt.Utils.showLoader
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.ArrayList

class SelectReligionActivity : BaseActivity() {

    val context: SelectReligionActivity = this@SelectReligionActivity
    private lateinit var binding: ActivitySelectReligionBinding
    var adapterReligion: AdapterReligion? = null
    private val viewModel by viewModel<getReligionsViewModel>()
    var type: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_select_religion)
        var bundel: Bundle? = intent.extras
        if (bundel != null) {
            type = bundel.getString("Type").toString()
            var location: String = bundel.getString("Location").toString()
            Log.d("TAG", "onCreate: $type::$location")
            if (type != null) {
                if (location == "Religion") {
                    callApi()
                    binding.toolBar.ivClose.visibility = View.VISIBLE
                }
            }
            Log.d("TAG", "onCreate: " + bundel.getString("Type"))
//        } else {
//            setListingCountry()
        }
        AdsUtils.showGoogleBannerAd(context, binding.adView)
        onCLick()
    }

    fun callApi() {
        showLoader(context)
        viewModel.callgetPRofileViewModel()
        viewModel.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    setListingReligion(it.data)
                } else {

                }
            } else {
                displayToast(
                    this,
                    "Response is null."
                )
            }
        })
    }

    private fun setTitle(title: String, hint: String) {
        binding.toolBar.tvTitle.text = title
        binding.searchbox.edtSearch.hint = hint
        binding.toolBar.tvSubTitle.visibility = View.GONE
    }

    private fun onCLick() {
        binding.toolBar.ivClose.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setListingReligion(data: java.util.ArrayList<getReligionsModel.Data>) {
        setTitle(getString(R.string.select_religion), getString(R.string.search_your_religion))
        var list: ArrayList<getReligionsModel.Data> = data
//        list.add("Hindu")
//        list.add("Muslim/Islam")
//        list.add("Christians")
//        list.add("Sikhs")
//        list.add("Zoroastrianism")
//        list.add("Judaism")
        binding.rvReligion.visibility = View.VISIBLE
        binding.rvReligion.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapterReligion = AdapterReligion(context, list, object : AdapterReligion.BtnClickListener {
            override fun onClick(
                list: String, position: Int, mViewHolder: AdapterReligion.DataHolder
            ) {
                adapterReligion!!.setSelected(position)
                if (type.equals("MyProfile")){
                    var intent = Intent()
                    intent.putExtra("selectedReligion", list);
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        })
        binding.rvReligion.adapter = adapterReligion
    }
}