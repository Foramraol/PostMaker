package com.ocean.postermaker.UI.LoginModule.Interest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.UI.DashboardModule.HomeActivity
import com.ocean.postermaker.databinding.ActivitySelectInterestBinding

class SelectInterestActivity : BaseActivity() {

    val context: SelectInterestActivity = this@SelectInterestActivity
    private lateinit var binding: ActivitySelectInterestBinding
    var adapterInterest: AdapterInterest? = null
    var selectedPositionsList: ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_select_interest)
        setTitle()
        AdsUtils.showGoogleBannerAd(context, binding.adView)
        setListingInterest()
        onCLick()
    }

    private fun setListingInterest() {
        var list: ArrayList<String> = ArrayList()
        list.add("Good Morning")
        list.add("Good Night")
        list.add("Birthday")
        list.add("Anniversary")
        list.add("Thank You")
        list.add("Engagement")
        list.add("Congratulation")
        binding.rvInterest.layoutManager =
            GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        adapterInterest = AdapterInterest(context, list, object : AdapterInterest.BtnClickListener {
            override fun onClick(
                list: String,
                position: Int,
                mViewHolder: AdapterInterest.DataHolder
            ) {
                if ((selectedPositionsList).contains(position)) {
                    selectedPositionsList.remove(position)
                    mViewHolder.binding.ivSelected.visibility = View.GONE
                    mViewHolder.binding.rlImageView.background = null
                } else {
                    if (selectedPositionsList.size < 5) {
                        selectedPositionsList.add(position)
                        mViewHolder.binding.ivSelected.visibility = View.VISIBLE
                        mViewHolder.binding.rlImageView.background =
                            ContextCompat.getDrawable(context, R.drawable.rect_stroke_20dp)
                    } else {
                        Toast.makeText(
                            context,
                            "You have reached to maximum selection of 5 items.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
        binding.rvInterest.adapter = adapterInterest
    }

    private fun setTitle() {
        binding.toolBar.tvTitle.text = getString(R.string.select_interest)
        binding.toolBar.tvSubTitle.visibility = View.VISIBLE
    }

    private fun onCLick() {
        binding.tvNext.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
            finish()
        }
    }
}