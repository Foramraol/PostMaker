package com.ocean.postermaker.UI.DashboardModule

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ocean.postermaker.R
import com.ocean.postermaker.databinding.FragmentEventBinding
import com.ocean.postermaker.databinding.FragmentMotivationalBinding

class MotivationalFragment : Fragment(), View.OnClickListener {
    var activity: HomeActivity? = null
    lateinit var binding: FragmentMotivationalBinding
    var adapterCategory: AdapterCategory? = null
    var adapterHome: AdapterHome? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_motivational, container, false)
        setLable()
        init()
        return binding.root
    }

    private fun setLable() {
    }

    fun init() {
        setListingCategory()
        setListingHomeEvents()
//        binding.llMyProfile.setOnClickListener(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(activity: HomeActivity) =
            MotivationalFragment().apply {
                this.activity = activity
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setListingCategory() {
        var list: ArrayList<String> = ArrayList()
        list.add("All")
        list.add("Positive")
        list.add("Slogan")
        list.add("Achievement")
        list.add("Time Management")
        list.add("Growth")
        binding.rvCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterCategory =
            AdapterCategory(context as Activity, list, object : AdapterCategory.BtnClickListener {
                override fun onClick(
                    list: String,
                    position: Int,
                    mViewHolder: AdapterCategory.DataHolder
                ) {
                    adapterCategory!!.setSelected(position)
                }
            })
        binding.rvCategories.adapter = adapterCategory
    }

    private fun setListingHomeEvents() {
        var list: ArrayList<Int> = ArrayList()
        list.add(R.drawable.preview1)
        list.add(R.drawable.preview2)
        list.add(R.drawable.preview3)
        list.add(R.drawable.trial)
        list.add(R.drawable.preview4)
        list.add(R.drawable.preview5)
        list.add(R.drawable.trial2)
        binding.rvMotivational.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        adapterHome =
            AdapterHome(context as Activity, list, object : AdapterHome.BtnClickListener {
                override fun onClick(
                    list: Int,
                    position: Int,
                    mViewHolder: AdapterHome.DataHolder
                ) {

                }
            })
        binding.rvMotivational.adapter = adapterHome
    }

    override fun onClick(p0: View?) {

    }
}