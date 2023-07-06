package com.oceanmtech.dmt.Data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.databinding.library.BuildConfig

class DataManager(context: Context) {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("AppPref", MODE_PRIVATE)
    private val editor: SharedPreferences.Editor

    companion object {
        var loginStartTime = "loginStartTime"
        var HomeTime = "HomeTime"
        var loginStartTimeCounter = "StartTimeCounter"

    }

    init {
        editor = sharedPref.edit()
    }

    fun setIsLoginNew(isLogin: Boolean) {
        editor.putBoolean("pref_isLoginNew", isLogin).apply()
    }

    fun isLoginNew(): Boolean {
        return sharedPref.getBoolean("pref_isLoginNew", false)
    }

    fun setIsLogin(isLogin: Boolean) {
        editor.putBoolean("pref_isLogin", isLogin).apply()
    }


    fun setAPIToken(apiToken: String) {
        editor.putString("pref_apiToken", apiToken).apply()
    }

    fun getAPIToken(): String {
        return sharedPref.getString("pref_apiToken", "").toString()
    }

    fun setToken(apiToken: String) {
        editor.putString("pref_token", apiToken).apply()
    }

    fun getToken(): String {
        return sharedPref.getString("pref_token", "").toString()
    }


    fun setExtraToken(apiToken: String) {
        editor.putString("pref_extra_token", apiToken).apply()
    }

    fun getExtraToken(): String {
        return sharedPref.getString("pref_extra_token", "").toString()
    }


    fun setAfterTelicallar(action: String) {
        editor.putString("after_telicallar", action).apply()
    }

    fun getAfterTelicallar(): String {
        return sharedPref.getString("after_telicallar", "").toString()
    }

    fun isLogin(): Boolean {
        return sharedPref.getBoolean("pref_isLogin", false)
    }

    fun setIsSocialLogin(isSocialLogin: Boolean) {
        editor.putBoolean("pref_isSocialLogin", isSocialLogin).apply()
    }


    fun getFrameList(): String {
        return sharedPref.getString("pref_frameList", "").toString()
    }

    fun setFrameList(response: String) {
        editor.putString("pref_frameList", response).apply()
    }

    fun isSocialLogin(): Boolean {
        return sharedPref.getBoolean("pref_isSocialLogin", false)
    }
//
//    fun setUserData(response: LoginRegisterModel?) {
//        setIsLogin(true)
//        if (response != null) {
//            editor.putString("pref_loginData", Gson().toJson(response)).apply()
//        }
//    }
//
//    fun getUserData(): LoginRegisterModel? {
//        return Gson().fromJson(sharedPref.getString("pref_loginData", ""), LoginRegisterModel::class.java)!!
//    }

    fun clearData() {
        editor.clear();
        editor.commit();
    }


    //    select business
    fun setUserBusinessID(id: Int) {
        editor.putInt("pref_businessID", id).apply()
    }

    fun getUserBusinessID(): Int {
        return sharedPref.getInt("pref_businessID", 0)
    }


    fun setName(mobileNo: String) {
        editor.putString("pref_name", mobileNo).apply()
    }

    fun getName(): String {
        return sharedPref.getString("pref_name", "").toString()
    }

    fun setBusinessName(mobileNo: String) {
        editor.putString("pref_businessName", mobileNo).apply()
    }

    fun getBusinessName(): String {
        return sharedPref.getString("pref_businessName", "").toString()
    }

    fun setBusinessTagline(mobileNo: String) {
        editor.putString("pref_businessTagline", mobileNo).apply()
    }

    fun getBusinessTagline(): String {
        return sharedPref.getString("pref_businessTagline", "").toString()
    }

    fun setEmail(email: String) {
        editor.putString("pref_email", email).apply()
    }

    fun getEmail(): String {
        return sharedPref.getString("pref_email", "").toString()
    }


    fun setAddress(address: String) {
        editor.putString("pref_address", address).apply()
    }

    fun getAddress(): String {
        return sharedPref.getString("pref_address", "").toString()
    }

    fun setWebsite(website: String) {
        editor.putString("pref_website", website).apply()
    }

    fun getWebsite(): String {
        return sharedPref.getString("pref_website", "").toString()
    }

    fun setLoginMobileNo(mobile_no: String) {
        editor.putString("pref_login_mobile", mobile_no).apply()
    }

    fun getLoginMobileNo(): String {
        return sharedPref.getString("pref_login_mobile", "").toString()
    }

    fun setLoginMobileNoTelicallar(mobile_no: String) {
        editor.putString("pref_login_mobile_telicallar", mobile_no).apply()
    }

    fun getLoginMobileNoTelicallar(): String {
        return sharedPref.getString("pref_login_mobile_telicallar", "").toString()
    }

    fun setMobileNo1(mobile_no: String) {
        editor.putString("pref_mobile1", mobile_no).apply()
    }

    fun getMobileNo1(): String {
        return sharedPref.getString("pref_mobile1", "").toString()
    }

    fun setMobileNo2(mobile_no: String) {
        editor.putString("pref_mobile2", mobile_no).apply()
    }

    fun getMobileNo2(): String {
        return sharedPref.getString("pref_mobile2", "").toString()
    }


    fun setInstaId(instaId: String) {
        editor.putString("instaId", instaId).apply()
    }

    fun getInstaId(): String {
        return sharedPref.getString("instaId", "").toString()
    }

    fun setFBId(instaId: String) {
        editor.putString("fbId", instaId).apply()
    }

    fun getFBId(): String {
        return sharedPref.getString("fbId", "").toString()
    }

    fun setLogo(logo: String) {
        editor.putString("pref_bLogo", logo).apply()
    }

    fun getLogo(): String {
        return sharedPref.getString("pref_bLogo", "").toString()
    }

    fun setBusinessImage(logo: String) {
        editor.putString("pref_bimage", logo).apply()
    }

    fun getBusinessImage(): String {
        return sharedPref.getString("pref_bimage", "").toString()
    }

      fun setBusinessTYpeName(name: String) {
          editor.putString("pref_business_type_name", name).apply()
    }

    fun getBusinessTYpeName(): String {
        return sharedPref.getString("pref_business_type_name", "").toString()
    }

    fun setBusinessTYpeId(id: Int) {
        editor.putInt("pref_business_type_id", id).apply()
    }

    fun getBusinessTYpeId(): Int {
        return sharedPref.getInt("pref_business_type_id", 0)
    }

    fun setSocialIcones(logo: String) {
        editor.putString("pref_social_icones", logo).apply()
    }

    fun getSocialIcones(): String {
        return sharedPref.getString("pref_social_icones", "").toString()
    }


    fun setBusinessCategory(businessList: String) {
        editor.putString("business_category", businessList).apply()
    }

    fun getBusinessCategory(): String {
        return sharedPref.getString("business_category", "").toString()
    }

    fun setBusinessCategoryId(businessList: String) {
        editor.putString("business_category_id", businessList).apply()
    }

    fun getBusinessCategoryId(): String {
        return sharedPref.getString("business_category_id", "").toString()
    }

    fun setUserLanguageId(businessList: String) {
        editor.putString("user_language_list", businessList).apply()
    }

    fun getUserLanguageId(): String {
        return sharedPref.getString("user_language_list", "0").toString()
    }

    fun setReligion(businessList: String) {
        editor.putString("religion_list", businessList).apply()
    }

    fun getReligion(): String {
        return sharedPref.getString("religion_list", "").toString()
    }

    fun setProfile(businessList: String) {
        editor.putString("my_profile", businessList).apply()
    }

    fun getProfile(): String {
        return sharedPref.getString("my_profile", "").toString()
    }


    fun setUserPaid(businessList: String) {
        editor.putString("user_paid", businessList).apply()
//        editor.putString("user_paid", "Yes").apply()
    }


    fun getUserPaid(): String {
        return sharedPref.getString("user_paid", "No").toString()
    }

    fun setMaxBusinessLimit(businessList: String) {
        editor.putString("business_limit", businessList).apply()
    }

    fun getMaxBusinessLimit(): String {
        return sharedPref.getString("business_limit", "5").toString()
    }


    fun setDeviceId(businessList: String) {
        editor.putString("my_device_id", businessList).apply()
    }

    fun getDeviceId(): String {
        return sharedPref.getString("my_device_id", "").toString()
    }


    fun setDeviceName(businessList: String) {
        editor.putString("my_device_name", businessList).apply()
    }

    fun getDeviceName(): String {
        return sharedPref.getString("my_device_name", "").toString()
    }


    fun setCountryCode(businessList: String) {
        editor.putString("my_country_code", businessList).apply()
    }

    fun getCountryCode(): String {
        return sharedPref.getString("my_country_code", "").toString()
    }


    fun setUniqId(businessList: String) {
        editor.putString("my_uniq_id", businessList).apply()
    }

    fun getUniqId(): String {
        return sharedPref.getString("my_uniq_id", "").toString()
    }

    fun setStartTime(businessList: String) {
        editor.putString("start_time", businessList).apply()
    }

    fun getStartTime(): String {
        return sharedPref.getString("start_time", "").toString()
    }


    fun setLogStatus(logStatus: Boolean) {
        editor.putBoolean("log_status", logStatus).apply()
    }

    fun getLogStatus(): Boolean {
        return sharedPref.getBoolean("log_status", false)
    }


    fun setString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun getString(key: String): String {
        return sharedPref.getString(key, "").toString()
    }

    fun setInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        return sharedPref.getInt(key, 0)
    }

    fun setSetting(successResponse: String?) {
        editor.putString("setting", successResponse).apply()
    }

    fun getSetting(): String {
        return sharedPref.getString("setting", "").toString()
    }

    fun setCondition(successResponse: String?) {
        editor.putString("condition", successResponse).apply()
    }

    fun getCondition(): String {
        return sharedPref.getString("condition", "").toString()
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor.putBoolean("IS_FIRST_TIME_LAUNCH", isFirstTime)
        editor.commit()
        editor.apply()
    }


    fun isFirstTimeLaunch(): Boolean {
        return sharedPref.getBoolean("IS_FIRST_TIME_LAUNCH", false)
    }


    fun setBannerAd(bannerAd: String) {
        if (BuildConfig.DEBUG)
            editor.putString("bannerAd", "ca-app-pub-3940256099942544/6300978111")
        else
            editor.putString("bannerAd", bannerAd)
        editor.commit()
        editor.apply()
    }

    fun getBanner(): String {
        return sharedPref.getString("bannerAd", "").toString()
    }

    fun setBannerAdX(bannerAd: String) {
        if (BuildConfig.DEBUG)
            editor.putString("bannerAdX", "/6499/example/banner")
        else
            editor.putString("bannerAdX", bannerAd)
        editor.commit()
        editor.apply()
    }

    fun getBannerX(): String {
        return sharedPref.getString("bannerAdX", "").toString()
    }

    fun setNativeAd(nativeAd: String) {
        if (BuildConfig.DEBUG)
            editor.putString("nativeAd", "ca-app-pub-3940256099942544/2247696110")
        else
            editor.putString("nativeAd", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getNative(): String {
        return sharedPref.getString("nativeAd", "").toString()
    }

    fun setNativeAdX(nativeAd: String) {
        if (BuildConfig.DEBUG)
            editor.putString("nativeAdX", "/6499/example/native")
        else
            editor.putString("nativeAdX", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getNativeX(): String {
        return sharedPref.getString("nativeAdX", "").toString()
    }

    fun setInterstitialAd(nativeAd: String) {
        if (BuildConfig.DEBUG)
            editor.putString("interstitialAd", "ca-app-pub-3940256099942544/1033173712")
        else
            editor.putString("interstitialAd", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getInterstitial(): String {
        return sharedPref.getString("interstitialAd", "").toString()
    }

    fun setInterstitialAdX(nativeAd: String) {
        if (BuildConfig.DEBUG)
            editor.putString("interstitialAdX", "/6499/example/interstitial")
        else
            editor.putString("interstitialAdX", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getInterstitialX(): String {
        return sharedPref.getString("interstitialAdX", "").toString()
    }

    fun setRewardedVideoAd(nativeAd: String) {
        if (BuildConfig.DEBUG)
            editor.putString("rewardedVideoAd", "ca-app-pub-3940256099942544/5224354917")
        else
            editor.putString("rewardedVideoAd", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getRewardedVideo(): String {
        return sharedPref.getString("rewardedVideoAd", "").toString()
    }

    fun setRewardedVideoAdX(nativeAd: String) {
        if (BuildConfig.DEBUG)
            editor.putString("rewardedVideoAdX", "/6499/example/rewarded")
        else
            editor.putString("rewardedVideoAdX", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getRewardedVideoX(): String {
        return sharedPref.getString("rewardedVideoAdX", "").toString()
    }

    fun setAdClickTotal(nativeAd: String) {

        editor.putString("ad_click", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getAdClickTotal(): String {
        return sharedPref.getString("ad_click", "7").toString()
    }

    fun setPosOfLoad(nativeAd: String) {

        editor.putString("position_of_load", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getPosOfLoad(): String {
        return sharedPref.getString("position_of_load", "5").toString()
    }

    fun setOpenAd(nativeAd: String) {
        editor.putString("appOpenAd", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getOpenAd(): String {
        return sharedPref.getString("appOpenAd", "").toString()
    }

    fun setOpenAdX(nativeAd: String) {
        editor.putString("OpenAdX", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getOpenAdX(): String {
        return sharedPref.getString("OpenAdX", "").toString()
    }

    fun setDefaultAppAd(nativeAd: String) {
        editor.putString("defaultad", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getDefaultAppAd(): String {
        return sharedPref.getString("defaultad", "").toString()
    }

    fun setAdStatus(nativeAd: String) {
        editor.putString("ad_status", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun clearPrefrense() {
        editor.clear().commit();
        editor.apply()
    }

    fun getAdStatus(): String {
        return sharedPref.getString("ad_status", "").toString()
    }




    fun setBusinessTypeIdDummy(nativeAd: Int) {
        editor.putInt("dummy_businesstype_id", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getBusinessTypeIdDummy(): Int {
        return sharedPref.getInt("dummy_businesstype_id", 0)
    }

    fun setBusinessTypeNameDummy(nativeAd: String) {
        editor.putString("dummy_businesscategoryname", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getBusinessTypeNameDummy(): String {
        return sharedPref.getString("dummy_businesscategoryname", "0").toString()
    }


    fun setBusinessCategoryDummy(nativeAd: String) {
        editor.putString("dummy_businesscategory1", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getBusinessCategoryDummy(): String {
        return sharedPref.getString("dummy_businesscategory1", "").toString()
    }

    fun setBusinessCategoryIdDummy(nativeAd: String) {
        editor.putString("dummy_businesscategoryid", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getBusinessCategoryIdDummy(): String {
        return sharedPref.getString("dummy_businesscategoryid", "0").toString()
    }

    fun setYourNameDummy(nativeAd: String) {
        editor.putString("dummy_yourname", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getYourNameDummy(): String {
        return sharedPref.getString("dummy_yourname", "").toString()
    }


    fun setBusinessNameDummy(nativeAd: String) {
        editor.putString("dummy_businessname", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getBusinessNameDummy(): String {
        return sharedPref.getString("dummy_businessname", "").toString()
    }

    fun setTagLineDummy(nativeAd: String) {
        editor.putString("dummy_tagline", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getTagLineDummy(): String {
        return sharedPref.getString("dummy_tagline", "").toString()
    }


    fun setEmailDummy(nativeAd: String) {
        editor.putString("dummy_email", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getEmailDummy(): String {
        return sharedPref.getString("dummy_email", "").toString()
    }

    fun setWebsiteDummy(nativeAd: String) {
        editor.putString("dummy_website", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getWebsiteDummy(): String {
        return sharedPref.getString("dummy_website", "").toString()
    }

    fun setAddressDummy(nativeAd: String) {
        editor.putString("dummy_address", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getAddressDummy(): String {
        return sharedPref.getString("dummy_address", "").toString()
    }

    fun setMobile1Dummy(nativeAd: String) {
        editor.putString("dummy_mobile1", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getMobile1Dummy(): String {
        return sharedPref.getString("dummy_mobile1", "").toString()
    }

    fun setMobile2Dummy(nativeAd: String) {
        editor.putString("dummy_mobile2", nativeAd)
        editor.commit()
        editor.apply()
    }

    fun getMobile2Dummy(): String {
        return sharedPref.getString("dummy_mobile2", "").toString()
    }


}