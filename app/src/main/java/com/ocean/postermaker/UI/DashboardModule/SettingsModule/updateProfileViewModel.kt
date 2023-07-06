package com.ocean.postermaker.UI.DashboardModule.SettingsModule

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.ocean.postermaker.Base.BaseViewModel
import com.oceanmtech.dmt.Data.DataManager
import com.oceanmtech.dmt.Network.API.ApiService
import com.oceanmtech.dmt.Utils.isInternetAvailable
import com.oceanmtech.dmt.Utils.isNetworkOnline1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class updateProfileViewModel (
    private val context: Context,
    private val apiService: ApiService,
    private val dataManager: DataManager) : BaseViewModel() {

        internal var mData: MutableLiveData<ProfileModel> = MutableLiveData()
        var requestCallLabel: Job? = null

        fun callUpdateProfileViewModel(filePath: String) {
            Log.d("isInternetAvailable", isInternetAvailable(context).toString())
            Log.d("isInternetAvailable2", isNetworkOnline1(context).toString())

            val headers: MutableMap<String, String> = HashMap()
            headers["Authorization"] = DataManager(context).getToken()
            val name = RequestBody.create("text/plain".toMediaTypeOrNull(), "Jinal Patel")
            val user_language_id = RequestBody.create("text/plain".toMediaTypeOrNull(), "1")
            val religion_id = RequestBody.create("text/plain".toMediaTypeOrNull(), "1")
            val country_id = RequestBody.create("text/plain".toMediaTypeOrNull(), "101")
            val state_id = RequestBody.create("text/plain".toMediaTypeOrNull(), "12")
            val gender = RequestBody.create("text/plain".toMediaTypeOrNull(), "female")
            val requestBody: RequestBody =
                RequestBody.create("image/*".toMediaTypeOrNull(), File(filePath))
            var profile: MultipartBody.Part? = null

            if (filePath.isNotEmpty()) {
                profile =
                    MultipartBody.Part.createFormData("logo", File(filePath).name, requestBody)
            }

            if (isInternetAvailable(context)) {
                requestCallLabel = CoroutineScope(Dispatchers.IO).launch {
                    val requestApi = apiService.updateProfile(headers,name,user_language_id,religion_id,
                        country_id,state_id,gender,profile)
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

        fun callUpdateMobileViewModel() {
            Log.d("isInternetAvailable", isInternetAvailable(context).toString())
            Log.d("isInternetAvailable2", isNetworkOnline1(context).toString())
            if (isInternetAvailable(context)) {
                requestCallLabel = CoroutineScope(Dispatchers.IO).launch {
                    val requestApi = apiService.updateMobile(DataManager(context).getToken(),"8956231470")
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