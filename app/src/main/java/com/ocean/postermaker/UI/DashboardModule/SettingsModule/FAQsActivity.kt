package com.ocean.postermaker.UI.DashboardModule.SettingsModule

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.ActivityFaqsBinding
import com.oceanmtech.dmt.Utils.Utils
import com.oceanmtech.dmt.Utils.dismissLoader
import com.oceanmtech.dmt.Utils.displayToast
import com.oceanmtech.dmt.Utils.showLoader
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.ArrayList

class FAQsActivity : BaseActivity() {

    val context: FAQsActivity = this@FAQsActivity
    private lateinit var binding: ActivityFaqsBinding
    var adapterFaqs: AdapterFAQs? = null
    private val viewModel by viewModel<faqViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_faqs)
        setTitle()
        AdsUtils.showGoogleBannerAd(context,binding.adView)
        callApi()
        onCLick()
    }

    fun callApi() {
        showLoader(context)
        viewModel.callFaqViewModel()
        viewModel.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    setFAQs(it.data)
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

    private fun setFAQs(data: java.util.ArrayList<faqModel.Data>) {
        var list: ArrayList<faqModel.Data> = data
//        list.add("Hindu")
//        list.add("Muslim/Islam")
//        list.add("Christians")
//        list.add("Sikhs")
//        list.add("Zoroastrianism")
//        list.add("Judaism")
        binding.rvFAQs.visibility = View.VISIBLE
        binding.rvFAQs.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapterFaqs = AdapterFAQs(context, list, object : AdapterFAQs.BtnClickListener {
            override fun onClick(
                list: faqModel.Data, position: Int, mViewHolder: AdapterFAQs.DataHolder
            ) {

            }
        })
        binding.rvFAQs.adapter = adapterFaqs
    }

    @SuppressLint("SetTextI18n")
    private fun setTitle() {
        Log.d("TAG", "setTitle: ")
        binding.toolbar.ivBack.visibility = View.VISIBLE
        binding.toolbar.ivHome.visibility = View.VISIBLE
        binding.toolbar.tvTitle.text = getString(R.string.faq)
        binding.toolbar.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivBack.setColorFilter(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivHome.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_website))
        binding.toolbar.ivHome.setColorFilter(ContextCompat.getColor(context, R.color.white))
    }

    private fun onCLick() {
        binding.toolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}