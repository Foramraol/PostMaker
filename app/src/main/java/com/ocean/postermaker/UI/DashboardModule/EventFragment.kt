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

class EventFragment : Fragment(), View.OnClickListener {
    var activity: HomeActivity? = null
    lateinit var binding: FragmentEventBinding
    var adapterUpcomingEvents: AdapterUpcomingEvents? = null
    var adapterHome: AdapterHome? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event, container, false)
        setLable()
        init()
        return binding.root
    }

    private fun setLable() {

    }

    fun init() {
        setListingUpcomingEvents()
        setListingHomeEvents()
//        binding.llMyProfile.setOnClickListener(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(activity: HomeActivity) =
            EventFragment().apply {
                this.activity = activity
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setListingUpcomingEvents() {
        var list: ArrayList<String> = ArrayList()
        list.add("Gujarati")
        list.add("Hindi")
        list.add("English")
        list.add("French")
        list.add("Spanish")
        list.add("German")
        list.add("Malayalam")
        binding.rvUpcomingEvents.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterUpcomingEvents =
            AdapterUpcomingEvents(context as Activity, list, object : AdapterUpcomingEvents.BtnClickListener {
                override fun onClick(
                    list: String,
                    position: Int,
                    mViewHolder: AdapterUpcomingEvents.DataHolder
                ) {

                }
            })
        binding.rvUpcomingEvents.adapter = adapterUpcomingEvents
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
        binding.rvHome.layoutManager =
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
        binding.rvHome.adapter = adapterHome
    }

    override fun onClick(p0: View?) {

    }
}