package com.ocean.postermaker.UI.LoginModule.Location

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.UI.LoginModule.Interest.SelectInterestActivity
import com.ocean.postermaker.databinding.ActivitySelectLocationBinding
import com.oceanmtech.dmt.Utils.Utils
import com.oceanmtech.dmt.Utils.dismissLoader
import com.oceanmtech.dmt.Utils.displayToast
import com.oceanmtech.dmt.Utils.showLoader
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.ArrayList

class SelectLocationActivity : BaseActivity() {

    val context: SelectLocationActivity = this@SelectLocationActivity
    private lateinit var binding: ActivitySelectLocationBinding
    var adapterCountry: AdapterCountry? = null
    var adapterState: AdapterState? = null
    var adapterCity: AdapterCity? = null
    private val viewModelUpdateLocation by viewModel<UpdateLocationViewModel>()
    private val viewModelCountry by viewModel<CountryViewModel>()
    private val viewModelState by viewModel<StateViewModel>()
    private val viewModelCity by viewModel<CityViewModel>()
    var type: String = ""
    var SelectedCountry: String = ""
    var SelectedState: String = ""
    var SelectedCountryId: String = ""
    var SelectedStateId: String = ""
    var SelectedCity: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_select_location)
        var bundel: Bundle? = intent.extras
        if (bundel != null) {
            type = bundel.getString("Type").toString()
            var location: String = bundel.getString("Location").toString()
            Log.d("TAG", "onCreate: $type::$location")
            if (type != null) {
                if (location == "Country") {
                    callApi()
                    binding.toolBar.ivClose.visibility = View.VISIBLE
                    binding.tvNext.visibility = View.GONE
                } else if (location == "State") {
                    callApiState()
                    binding.toolBar.ivClose.visibility = View.VISIBLE
                    binding.tvNext.visibility = View.GONE
                }
            }
            Log.d("TAG", "onCreate: " + bundel.getString("Type"))
        } else {
            callApi()
        }
        AdsUtils.showGoogleBannerAd(context, binding.adView)
        onCLick()
    }

    fun callApi(){
        showLoader(context)
        viewModelCountry.callCountryListApi()
        viewModelCountry.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    setListingCountry(it.data)
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

    fun callApiState(){
        showLoader(context)
        viewModelState.callStateListApi(SelectedCountryId)
        viewModelState.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    setListingState(it.data)
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

    fun callApiCity(){
        showLoader(context)
        viewModelCity.callCityListApi(SelectedStateId)
        viewModelCity.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    setListingCity(it.data)
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
        binding.tvNext.setOnClickListener {
            if (binding.toolBar.tvTitle.text.equals(getString(R.string.select_country))) {
                if(!SelectedCountry.equals("")) {
                    callApiState()
                }else{
                    Toast.makeText(context,"Please select country.",Toast.LENGTH_SHORT).show()
                }
            } else if (binding.toolBar.tvTitle.text.equals(getString(R.string.select_state))) {
                if(!SelectedCountry.equals("")) {
                    callApiCity()
                }else{
                    Toast.makeText(context,"Please select state.",Toast.LENGTH_SHORT).show()
                }
            } else if (binding.toolBar.tvTitle.text.equals(getString(R.string.select_city))) {
                if(!SelectedCountry.equals("")) {
                    callApiUpdateLocation()
                }else{
                    Toast.makeText(context,"Please select city.",Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.toolBar.ivClose.setOnClickListener {
            onBackPressed()
        }
    }

    private fun callApiUpdateLocation() {
        showLoader(context)
        viewModelUpdateLocation.callUpdateLocationApi(SelectedCountry,SelectedState,SelectedCity)
        viewModelUpdateLocation.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    startActivity(Intent(context, SelectInterestActivity::class.java))
                    finish()
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

    private fun setListingCountry(data: java.util.ArrayList<CountryModel.Data>) {
        setTitle(getString(R.string.select_country), getString(R.string.search_your_country))
        var list: java.util.ArrayList<CountryModel.Data> = data
//        list.add("India")
//        list.add("Australia")
//        list.add("Bhutan")
//        list.add("Canada")
//        list.add("Spain")
//        list.add("Germany")
//        list.add("Canada")
//        list.add("US")
        binding.rvCountry.visibility = View.VISIBLE
        binding.rvState.visibility = View.GONE
        binding.rvCity.visibility = View.GONE
        binding.rvCountry.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapterCountry = AdapterCountry(context, list, object : AdapterCountry.BtnClickListener {
            override fun onClick(
                list: CountryModel.Data, position: Int, mViewHolder: AdapterCountry.DataHolder
            ) {
                adapterCountry!!.setSelected(position)
                SelectedCountry=list.name
                SelectedCountryId= list.id.toString()
                if (type.equals("MyProfile")){
                    var intent = Intent()
                    intent.putExtra("selectedCountry", list.name);
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        })
        binding.rvCountry.adapter = adapterCountry
    }

    private fun setListingState(data: java.util.ArrayList<StateModel.Data>) {
        setTitle(getString(R.string.select_state), getString(R.string.search_your_state))
        var list: ArrayList<StateModel.Data> = data
//        list.add("Gujarat")
//        list.add("Maharashtra")
//        list.add("Asam")
//        list.add("Goa")
//        list.add("Kerala")
//        list.add("Jammu-Kashmir")
//        list.add("Haryana")
//        list.add("Punjab")
//        list.add("Rajasthan")
//        list.add("Bihar")
//        list.add("Sikkim")
        binding.rvCountry.visibility = View.GONE
        binding.rvState.visibility = View.VISIBLE
        binding.rvCity.visibility = View.GONE
        binding.rvState.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapterState = AdapterState(context, list, object : AdapterState.BtnClickListener {
            override fun onClick(
                list: StateModel.Data, position: Int, mViewHolder: AdapterState.DataHolder
            ) {
                adapterState!!.setSelected(position)
                SelectedState=list.name
                SelectedStateId= list.id.toString()
                if (type.equals("MyProfile")){
                    var intent = Intent()
                    intent.putExtra("selectedState", list.name);
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        })
        binding.rvState.adapter = adapterState
    }

    private fun setListingCity(data: java.util.ArrayList<CityModel.Data>) {
        setTitle(getString(R.string.select_city), getString(R.string.search_your_city))
        var list: java.util.ArrayList<CityModel.Data> = data
//        list.add("Ahmedabad")
//        list.add("Vadodara")
//        list.add("Jamnagar")
//        list.add("Rajkot")
//        list.add("Junagadh")
//        list.add("Dang")
//        list.add("Surat")
//        list.add("Dwarka")
//        list.add("Sasan-Gir")
//        list.add("Somnath")
//        list.add("Bhuj")
        binding.rvCountry.visibility = View.GONE
        binding.rvState.visibility = View.GONE
        binding.rvCity.visibility = View.VISIBLE
        binding.rvCity.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapterCity = AdapterCity(context, list, object : AdapterCity.BtnClickListener {
            override fun onClick(
                list: CityModel.Data, position: Int, mViewHolder: AdapterCity.DataHolder
            ) {
                adapterCity!!.setSelected(position)
                SelectedCity=list.name
            }
        })
        binding.rvCity.adapter = adapterCity
    }
}