package com.ocean.postermaker.UI.DashboardModule.SettingsModule.BusinessModule

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.ocean.postermaker.AdManager.NativeBanner
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.UI.LoginModule.Language.SelectLanguageActivity
import com.ocean.postermaker.UI.LoginModule.LoginActivity
import com.ocean.postermaker.databinding.ActivityAddBusinessBinding
import com.ocean.postermaker.databinding.ActivityLoginBinding

class AddBusinessActivity : BaseActivity() {

    val context: AddBusinessActivity = this@AddBusinessActivity
    private lateinit var binding: ActivityAddBusinessBinding
    var from: String = ""
    var cat: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_add_business)
        NativeBanner.loadNativeBannerAds(context, binding.llAdContainerShare)
        binding.llAdContainerShare.visibility = View.VISIBLE;
        var bundel: Bundle? = intent.extras
        if (bundel != null) {
            from = bundel.getString("From").toString()
            cat = bundel.getString("Category").toString()
            Log.d("TAG", "onCreate: ::$from")
        }
        setTitle()
        onCLick()
    }

    @SuppressLint("SetTextI18n")
    private fun setTitle() {
        Log.d("TAG", "setTitle: ")
        binding.toolbar.ivBack.visibility = View.VISIBLE
        binding.toolbar.ivHome.visibility = View.VISIBLE
        binding.toolbar.tvTitle.text = "New Business"
        binding.toolbar.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivBack.setColorFilter(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivHome.setColorFilter(ContextCompat.getColor(context, R.color.white))
        if (from == "selectProfile") {
            if (cat != "") {
                binding.llSelectCat.visibility = View.VISIBLE
                binding.tvSelectCat.text = "Select $cat Category"
                binding.tvSelectProfile.text = cat
            }else{
                binding.llSelectCat.visibility = View.GONE
            }
        }
    }

    private fun onCLick() {
        binding.llSelectProfile.setOnClickListener {
            startActivityForResult(Intent(context, SelectProfileActivity::class.java),101)
        }
        binding.llSelectCat.setOnClickListener {
            startActivityForResult(Intent(context, SelectCatActivity::class.java).putExtra("cat",cat),102)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101&&resultCode== RESULT_OK){
            if (data != null) {
                from = data.getStringExtra("From").toString()
                cat = data.getStringExtra("Category").toString()
                Log.d("TAG", "onCreate: $cat::$from")
            }
            setTitle()
        }
    }
}