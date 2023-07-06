package com.ocean.postermaker.UI.DashboardModule.SettingsModule.BusinessModule

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.ActivityPostStorySelectBinding

class PostStorySelectActivity : BaseActivity() {

    val context: PostStorySelectActivity = this@PostStorySelectActivity
    private lateinit var binding: ActivityPostStorySelectBinding
    var cat: String = ""
    var adapterPost: AdapterPost? = null
    var adapterStory: AdapterStory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_post_story_select)
        var bundel: Bundle? = intent.extras
        if (bundel != null) {
            cat = bundel.getString("cat").toString()
            Log.d("TAG", "onCreate: ::$cat")
        }
        setTitle()
        setPostListing()
        setStoryListing()
        AdsUtils.showGoogleBannerAd(context, binding.adView)
        onCLick()
    }

    @SuppressLint("SetTextI18n")
    private fun setTitle() {
        Log.d("TAG", "setTitle: ")
        binding.toolbar.ivBack.visibility = View.VISIBLE
        binding.toolbar.tvNext.visibility = View.VISIBLE
        binding.toolbar.ivHome.visibility = View.GONE
        binding.toolbar.tvTitle.text = "Choose Frame"
        binding.toolbar.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivBack.setColorFilter(ContextCompat.getColor(context, R.color.white))
    }

    private fun onCLick() {
        binding.toolbar.ivBack.setOnClickListener {
            if (binding.toolbar.tvNext.text == "Next") {
                onBackPressed()
            } else if (binding.toolbar.tvNext.text == "Save") {
                binding.toolbar.tvNext.text = "Next"
                binding.rvPost.visibility = View.VISIBLE
                binding.rvStory.visibility = View.GONE
                binding.tvPost.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.tvStory.setTextColor(ContextCompat.getColor(context, R.color.gray))
            }
        }
        binding.toolbar.tvNext.setOnClickListener {
            if (binding.toolbar.tvNext.text == "Next") {
                binding.toolbar.tvNext.text = "Save"
                binding.rvPost.visibility = View.GONE
                binding.rvStory.visibility = View.VISIBLE
                binding.tvPost.setTextColor(ContextCompat.getColor(context, R.color.gray))
                binding.tvStory.setTextColor(ContextCompat.getColor(context, R.color.black))
            } else if (binding.toolbar.tvNext.text == "Save") {
                startActivity(Intent(context, FinalViewActivity::class.java))
            }
        }
    }

    private fun setPostListing() {
        var list: ArrayList<String> = ArrayList()
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        binding.rvPost.layoutManager =
            GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        adapterPost = AdapterPost(context, list, object : AdapterPost.BtnClickListener {
            override fun onClick(
                list: String,
                position: Int,
                mViewHolder: AdapterPost.DataHolder
            ) {
                adapterPost!!.setSelected(position)
            }
        })
        binding.rvPost.adapter = adapterPost
    }

    private fun setStoryListing() {
        var list: ArrayList<String> = ArrayList()
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        binding.rvStory.layoutManager =
            GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        adapterStory = AdapterStory(context, list, object : AdapterStory.BtnClickListener {
            override fun onClick(
                list: String,
                position: Int,
                mViewHolder: AdapterStory.DataHolder
            ) {
                adapterStory!!.setSelected(position)
            }
        })
        binding.rvStory.adapter = adapterStory
    }
}