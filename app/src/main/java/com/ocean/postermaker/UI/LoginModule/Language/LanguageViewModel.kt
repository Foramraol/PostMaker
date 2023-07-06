package com.ocean.postermaker.UI.LoginModule.Language

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.ocean.postermaker.Base.BaseViewModel
import com.oceanmtech.dmt.Data.DataManager
import com.oceanmtech.dmt.Data.ImportantDataManager
import com.oceanmtech.dmt.Network.API.ApiService
import com.oceanmtech.dmt.Utils.isInternetAvailable
import com.oceanmtech.dmt.Utils.isNetworkOnline1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LanguageViewModel (
    private val context: Context,
    private val apiService: ApiService,
    private val dataManager: DataManager) : BaseViewModel() {

        internal var mData: MutableLiveData<LanguageModel> = MutableLiveData()
        var requestCallLabel: Job? = null

        fun callLangListApi() {
            Log.d("isInternetAvailable", isInternetAvailable(context).toString())
            Log.d("isInternetAvailable2", isNetworkOnline1(context).toString())
            if (isInternetAvailable(context)) {
                requestCallLabel = CoroutineScope(Dispatchers.IO).launch {
                    val requestApi = apiService.getLanguageList(DataManager(context).getToken())
                    val response = requestApi?.await()
                    if (response?.body()!!.status == 200) {
                        val gson = Gson()
                        val successResponse = gson.toJson(response.body())
                        Log.d("TAG", "callLoginApi: $successResponse")
                    }
                    mData.postValue(response.body())
                }
            }
        }
}