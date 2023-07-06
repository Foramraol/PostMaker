package com.ocean.postermaker.UI.DashboardModule.SettingsModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ocean.postermaker.R
import com.ocean.postermaker.UI.DashboardModule.HomeActivity
import com.ocean.postermaker.UI.DashboardModule.SettingsModule.BusinessModule.MyBusinessActivity
import com.ocean.postermaker.UI.LoginModule.Location.SelectLocationActivity
import com.ocean.postermaker.databinding.FragmentSettingsBinding
import com.oceanmtech.dmt.Utils.goTo

class SettingsFragment : Fragment(), View.OnClickListener {
    var activity: HomeActivity? = null
    lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        setLable()
        init()
        return binding.root
    }

    private fun setLable() {
    }

    fun init() {
        binding.llMyProfile.setOnClickListener(this)
        binding.llMyBusiness.setOnClickListener(this)
        binding.llFeedback.setOnClickListener(this)
        binding.llHelpSupport.setOnClickListener(this)
        binding.llFAQs.setOnClickListener(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(activity: HomeActivity) =
            SettingsFragment().apply {
                this.activity = activity
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.llMyProfile -> {
                startActivity(Intent(context, MyProfileActivity::class.java))
            }
            R.id.llMyBusiness -> {
                startActivity(Intent(context, MyBusinessActivity::class.java))
            }
            R.id.llFeedback -> {
                startActivity(Intent(context, FeedbackActivity::class.java))
            }
            R.id.llFAQs -> {
                startActivity(Intent(context, FAQsActivity::class.java))
            }
            R.id.llHelpSupport -> {
                startActivity(Intent(context, HelpSupportActivity::class.java))
            }
        }
    }
}