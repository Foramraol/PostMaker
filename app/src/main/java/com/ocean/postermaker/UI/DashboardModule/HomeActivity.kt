package com.ocean.postermaker.UI.DashboardModule

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.UI.DashboardModule.SettingsModule.SettingsFragment
import com.ocean.postermaker.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.lay_bottombar.*

class HomeActivity : BaseActivity() {
    val context: HomeActivity = this@HomeActivity
    private lateinit var binding: ActivityHomeBinding
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_home)
        onEventSelected()
        AdsUtils.showGoogleBannerAd(context, binding.adView)
        onCLick()
    }

    private fun onCLick() {
        llEvent.setOnClickListener {
            onEventSelected()
        }
        llGreetings.setOnClickListener {
            onGreetingsSelected()
        }
        llMotivational.setOnClickListener {
            onMotivationalSelected()
        }
        llSetting.setOnClickListener {
            onSettingsSelected()
        }
    }

    fun onEventSelected() {
        fragmentManager.beginTransaction().replace(
            R.id.containerHome,
            EventFragment.newInstance(context)
        ).commit()
        llEvent.background = ContextCompat.getDrawable(context, R.drawable.rect_transprent_10dp)
        llGreetings.background = null
        llMotivational.background = null
        llSetting.background = null
    }

    fun onGreetingsSelected() {
        fragmentManager.beginTransaction().replace(
            R.id.containerHome,
            GreetingsFragment.newInstance(context)
        ).commit()
        llEvent.background = null
        llGreetings.background = ContextCompat.getDrawable(context, R.drawable.rect_transprent_10dp)
        llMotivational.background = null
        llSetting.background = null
    }

    fun onMotivationalSelected() {
        fragmentManager.beginTransaction().replace(
            R.id.containerHome,
            MotivationalFragment.newInstance(context)
        ).commit()
        llEvent.background = null
        llGreetings.background = null
        llMotivational.background = ContextCompat.getDrawable(context, R.drawable.rect_transprent_10dp)
        llSetting.background = null
    }

    fun onSettingsSelected() {
        fragmentManager.beginTransaction().replace(
            R.id.containerHome,
            SettingsFragment.newInstance(context)
        ).commit()
        llEvent.background = null
        llGreetings.background = null
        llMotivational.background = null
        llSetting.background = ContextCompat.getDrawable(context, R.drawable.rect_transprent_10dp)
    }
}