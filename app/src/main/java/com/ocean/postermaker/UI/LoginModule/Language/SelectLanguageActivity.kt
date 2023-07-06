package com.ocean.postermaker.UI.LoginModule.Language

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.UI.LoginModule.Location.SelectLocationActivity
import com.ocean.postermaker.databinding.ActivitySelectLanguageBinding
import com.oceanmtech.dmt.Utils.Utils
import com.oceanmtech.dmt.Utils.dismissLoader
import com.oceanmtech.dmt.Utils.displayToast
import com.oceanmtech.dmt.Utils.showLoader
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.ArrayList

class SelectLanguageActivity : BaseActivity() {

    val context: SelectLanguageActivity = this@SelectLanguageActivity
    private lateinit var binding: ActivitySelectLanguageBinding
    var adapterLanguage: AdapterLanguage? = null
    private val viewModel by viewModel<LanguageViewModel>()
    private val viewUpdateLanguageModel by viewModel<UpdateLanguageViewModel>()
    var type: String = ""
    var selectedLanguageId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_select_language)
        var bundel: Bundle? = intent.extras
        if (bundel != null) {
            type = bundel.getString("Type").toString()
            setTitle(type)
            Log.d("TAG", "onCreate: " + bundel.getString("Type"))
        } else {
            setTitle("")
        }
        callApi()
        AdsUtils.showGoogleBannerAd(context, binding.adView)
        onCLick()
    }

    fun callApi() {
        showLoader(context)
        viewModel.callLangListApi()
        viewModel.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    setListingLang(it.data)
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

    private fun setListingLang(data: java.util.ArrayList<LanguageModel.Data>) {
        var list: ArrayList<LanguageModel.Data> = data
        binding.rvLanguage.layoutManager =
            GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        adapterLanguage = AdapterLanguage(context, list, object : AdapterLanguage.BtnClickListener {
            override fun onClick(
                list: LanguageModel.Data,
                position: Int,
                mViewHolder: AdapterLanguage.DataHolder
            ) {
                adapterLanguage!!.setSelected(position)
                selectedLanguageId= list.id.toString()
                if (type.equals("MyProfile")){
                    var intent = Intent()
                    intent.putExtra("selectedLanguage", list.name);
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        })
        binding.rvLanguage.adapter = adapterLanguage
    }

    private fun setTitle(type: String) {
        Log.d("TAG", "setTitle: " + type)
        if (type != "") {
            binding.toolBar.ivClose.visibility = View.VISIBLE
            binding.tvNext.visibility = View.GONE
        }
        binding.toolBar.tvTitle.text = getString(R.string.select_lang)
        binding.toolBar.tvSubTitle.visibility = View.GONE
    }

    fun callApiUpdate(selectedLanguageId: String) {
        viewUpdateLanguageModel.callLangUpdateApi(selectedLanguageId)
        viewUpdateLanguageModel.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    if(it.status==200){
                        startActivity(Intent(context, SelectLocationActivity::class.java))
                        finish()
                    }else{

                    }
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

    private fun onCLick() {
        binding.tvNext.setOnClickListener {
            if(!selectedLanguageId.equals("")) {
                callApiUpdate(selectedLanguageId)
            }else{
                Toast.makeText(context,"Please select Language.",Toast.LENGTH_SHORT).show()
            }
        }
        binding.toolBar.ivClose.setOnClickListener {
            onBackPressed()
        }
    }
}