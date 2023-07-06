package com.oceanmtech.dmt.Network.API

import com.ocean.postermaker.UI.DashboardModule.SettingsModule.faqModel
import com.ocean.postermaker.UI.DashboardModule.SettingsModule.ProfileModel
import com.ocean.postermaker.UI.DashboardModule.SettingsModule.getReligionsModel
import com.ocean.postermaker.UI.LoginModule.Language.LanguageModel
import com.ocean.postermaker.UI.LoginModule.Language.UpdateLanguageModel
import com.ocean.postermaker.UI.LoginModule.Location.UpdateLocationModel
import com.ocean.postermaker.UI.LoginModule.Location.CityModel
import com.ocean.postermaker.UI.LoginModule.Location.CountryModel
import com.ocean.postermaker.UI.LoginModule.Location.StateModel
import com.ocean.postermaker.UI.LoginModule.LoginModel
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    var value: Int;
    @FormUrlEncoded
    @POST("social-login")
    fun login(
        @Field("object_id") social_id: String,
        @Field("email") email: String,
        @Field("country_code") country_code: String,
        @Field("device_model") device_model: String,
        @Field("device_id") firebase_token: String,
        @Field("unique_id") unique_id: String,
        @Field("device_type") device_type: String,
        @Field("type") type: String
    ): Deferred<Response<LoginModel>>

    @FormUrlEncoded
    @POST("language/update")
    fun UpdateUserLanguage(
        @Header("Authorization") auth: String,
        @Field("user_language_id") user_language_id: String
    ): Deferred<Response<UpdateLanguageModel?>>?

    @FormUrlEncoded
    @POST("user/location/save")
    fun UpdateUserLocation(
        @Header("Authorization") auth: String,
        @Field("country") country: String,
        @Field("state") state: String,
        @Field("city") city: String
    ): Deferred<Response<UpdateLocationModel?>>?

    @GET("languages/user")
    fun getLanguageList(
        @Header("Authorization") auth: String
    ): Deferred<Response<LanguageModel?>>?

    @GET("countries/get")
    fun getCountryList(
        @Header("Authorization") auth: String
    ): Deferred<Response<CountryModel?>>?

    @GET("states/get")
    fun getStateList(
        @Header("Authorization") auth: String,
        @Query("country_id") country_id: String
    ): Deferred<Response<StateModel?>>?

    @GET("cities/get")
    fun getCitiesList(
        @Header("Authorization") auth: String,
        @Query("state_id") state_id: String
    ): Deferred<Response<CityModel?>>?

    @GET("profile/get")
    fun getProfile(
        @Header("Authorization") auth: String
    ): Deferred<Response<ProfileModel?>>?

    @GET("faqs/get")
    fun getFaqs(
        @Header("Authorization") auth: String
    ): Deferred<Response<faqModel?>>?

    @GET("religions/get")
    fun getReligions(
        @Header("Authorization") auth: String
    ): Deferred<Response<getReligionsModel?>>?

    @Multipart
    @POST("user/save")
    fun updateProfile(
        @HeaderMap token: MutableMap<String, String>,
        @Part("name") name: RequestBody,
        @Part("user_language_id") user_language_id: RequestBody,
        @Part("religion_id") religion_id: RequestBody,
        @Part("country_id") country_id: RequestBody,
        @Part("state_id") state_id: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part logo: MultipartBody.Part?
    ): Deferred<Response<ProfileModel>>

    @FormUrlEncoded
    @POST("mobile/update")
    fun updateMobile(
        @Header("Authorization") auth: String,
        @Field("mobile_no") name: String
    ): Deferred<Response<ProfileModel>>

    //    @FormUrlEncoded
//    @POST("api/v1/login")
//    fun UserRegister(
//        @Field("action") user_register: String?,
//        @Field("unique_id") unique_id: String?,
//        @Field("mobile_no") mobile_no: String?,
//        @Field("has_token") has_token: String?
//    ): Call<UserRegister?>?
//    @FormUrlEncoded
//    @POST("login")
//    fun login(
//        @Field("mobile_no") mobile_no: String,
//        @Field("has_token") has_token: String,
//        @Field("device_model") device_model: String,
//        @Field("device_id") firebase_token: String,
//        @Field("country_code") country_code: String,
//        @Field("unique_id") unique_id: String,
//        @Field("is_new") is_new: Boolean,
//        @Field("device_type") device_type: String
//    ): Deferred<Response<LoginRegisterModel>>
//
//    @FormUrlEncoded
//    @POST("otp/confirm")
//    fun verifyOTP(
//        @Field("otp") otp: String,
//        @Field("token") token: String,
//        @Field("unique_id") unique_id: String
//    ): Deferred<Response<OtpVerifyModel>>
//
//    @Multipart
//    @POST("business/save")
//    fun addNewBusiness(
//        @HeaderMap token: MutableMap<String, String>,
//        @Part("frame_type_id") type_id: RequestBody?,
//        @Part("category_id") category_id: RequestBody?,
//        @Part("name") name: RequestBody?,
//        @Part("business_name") business_name: RequestBody?,
//        @Part("tagline") business_tag_line: RequestBody?,
//        @Part("email") email: RequestBody?,
//        @Part("website") website: RequestBody?,
//        @Part("address") address: RequestBody?,
//        @Part("mobile_no") mobile_no: RequestBody?,
//        @Part("mobile_no_2") mobile_no_2: RequestBody?,
//        @Part("social_icones") social_icones: RequestBody?,
//        @Part logo: MultipartBody.Part?,
//        @Part("business_id") business_id: RequestBody?,
//        @Part("frame_ids") frame_ids: RequestBody?,
//        @Part("story_frame_ids") story_frame_ids: RequestBody?,
//        @Part businessLogo: MultipartBody.Part?,
//        @Part("political_party_id") political_party_id: RequestBody?,
//        @Part("is_delete_business_image") is_delete_business_image: RequestBody?,
//        @Part("is_delete_logo") is_delete_logo: RequestBody?
//    ): Deferred<Response<AddBusinessModel?>>?
//
//
//    @GET("businesses/get")
//    fun getBusinessList(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<BusinessListModel?>>?
//
//    @GET("businesses/get")
//    fun getBusinessList1(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<BusinessListModel>>
//
//    @FormUrlEncoded
//    @POST("business/delete")
//    fun deleteBusiness(
//        @Header("Authorization") auth: String,
//        @Field("business_id") business_id: String?
//    ): Deferred<Response<BusinessListModel?>>?
//
//    @FormUrlEncoded
//    @POST("business/default/set")
//    fun defaultBusiness(
//        @Header("Authorization") auth: String,
//        @Field("business_id") business_id: String?
//    ): Deferred<Response<BusinessListModel?>>?
//
//    @GET("business-category/get")
//    fun getBusinessCategory(
//        @Header("Authorization") auth: String,
//        @Query("type") type: String
//    ): Deferred<Response<BusinessCategoryModel?>>?
//
//
//    @GET("sticker/category/get")
//    fun getStickerCategory(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<StickerCategoryModel?>>?
//
//
//    @GET("sticker/get")
//    fun getCategorySticker(
//        @Header("Authorization") auth: String,
//        @QueryMap options: Map<String, String>
//    ): Call<CategoryStickerModel>?
//
//
//    @GET("languages/app")
//    fun getAppLanguage(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<LanguageModel?>>?
//
//    @GET("languages/user")
//    fun getUserLanguage(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<LanguageModel?>>?
//
//    @GET("languages/user")
//    fun getUserLanguageData(
//        @Header("Authorization") auth: String,
//        @Query("home_category_id") home_category_id: String,
//        @Query("business_category_id") business_category_id: String,
//        @Query("post_type") post_type: String,
//        @Query("type") type: String,
//        @Query("event_id") event_id: String,
//        @Query("data_type") data_type: String
//    ): Deferred<Response<LanguageModel?>>?
//
//    @GET("custom/post-data/get")
//    fun getCustom(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<CustomModel?>>?
//
//    @GET("political/party/get")
//    fun getPoliticalParty(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<PartyListModel?>>?
//
//    @GET("religions/get")
//    fun getReligions(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<ReligionModel?>>?
//
//    @GET("labels/get")
//    fun getAppLable(
//        @Header("Authorization") auth: String,
//        @Query("language_id") language_id: String
//    ): Deferred<Response<LableModel?>>?
//
//    @GET("countries/get")
//    fun getCountres(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<CountryStateCityModel?>>?
//
//    @GET("states/get")
//    fun getStates(
//        @Header("Authorization") auth: String,
//        @Query("country_id") country_id: String
//    ): Deferred<Response<CountryStateCityModel?>>?
//
//    @GET("cities/get")
//    fun getCities(
//        @Header("Authorization") auth: String,
//        @Query("state_id") state_id: String
//    ): Deferred<Response<CountryStateCityModel?>>?
//
//    @Multipart
//    @POST("user/save")
//    fun addProfile(
//        @HeaderMap token: MutableMap<String, String>,
//        @Part("name") name: RequestBody?,
//        @Part("user_language_id") user_language_id: RequestBody?,
//        @Part("religion_id") religion_id: RequestBody?,
//        @Part("email") email: RequestBody?,
//        @Part("country_id") country_id: RequestBody?,
//        @Part("state_id") state_id: RequestBody?,
//        @Part("city_id") city_id: RequestBody?,
//        @Part("gender") gender: RequestBody?,
//        @Part("date_of_birth") date_of_birth: RequestBody?,
//        @Part("anniversary_date") anniversary_date: RequestBody?,
//        @Part("father_name") father_name: RequestBody?,
//        @Part("mother_name") mother_name: RequestBody?,
//        @Part("spouse_name") spouse_name: RequestBody?,
//        @Part("children_json") children_json: RequestBody?,
//        @Part("brother_json") brother_json: RequestBody?,
//        @Part("sister_json") sister_json: RequestBody?,
//        @Part logo: MultipartBody.Part?
//    ): Deferred<Response<ProfileModel?>>?
//
//
//    @GET("profile/get")
//    fun getProfile(
//        @HeaderMap token: MutableMap<String, String>
//    ): Deferred<Response<ProfileModel?>>?
//
//
//    @GET("home/get")
//    fun getHomeCategory(
//        @Header("Authorization") auth: String,
//        @Query("post_type") postType: String,
//        @Query("type") type: String
//    ): Deferred<Response<HomeCategoryModel?>>?
//
//    @GET("dbvc/get")
//    fun getVisitingCard(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<VisitingCardModel?>>?
//
//    @GET("data/get")
//    fun getEditorPostData(
//        @Header("Authorization") auth: String,
//        @Query("home_category_id") home_category_id: String,
//        @Query("page") page: String,
//        @Query("language_id") language_id: String,
//        @Query("post_type") postType: String,
//        @Query("type") type: String
//    ): Deferred<Response<EditorPostModel?>>?
//
//    @GET("business-data/get")
//    fun getBusinessPostData(
//        @Header("Authorization") auth: String,
//        @Query("business_category_id") business_category_id: String,
//        @Query("page") page: String,
//        @Query("language_id") language_id: String,
//        @Query("post_type") postType: String,
//        @Query("type") type: String
//    ): Deferred<Response<EditorPostModel?>>?
//
//    @GET("event/data/get")
//    fun getEventData(
//        @Header("Authorization") auth: String,
//        @Query("event_id") event_id: String,
//        @Query("page") page: String,
//        @Query("language_id") language_id: String,
//        @Query("post_type") postType: String,
//        @Query("type") type: String
//    ): Deferred<Response<EditorPostModel?>>?
//
//
//    @GET("plans/get")
//    fun getPlan(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<PlanModel?>>?
//
//    @GET("saved/post/get")
//    fun getMyPhoto(
//        @Header("Authorization") auth: String,
//        @Query("page") page: String,
//        @Query("type") type: String
//    ): Deferred<Response<MyPhotoModel?>>?
//
//    @GET("saved/post/get")
//    fun getMyPhoto(
//        @Header("Authorization") auth: String,
//        @Query("page") page: String
//    ): Deferred<Response<MyPhotoModel?>>?
//
//    //    @Query("business_id") business_id : String,
//    @GET("frames/get")
//    fun getFrameList(
//        @Header("Authorization") auth: String,
//        @Query("business_id") business_id: String,
//        @Query("user_name") user_name: String,
//        @Query("business_name") business_name: String,
//        @Query("business_tagline") business_tagline: String,
//        @Query("email") email: String,
//        @Query("website") website: String,
//        @Query("address") address: String,
//        @Query("mobile_no") mobile_no: String,
//        @Query("mobile_no1") mobile_no1: String,
//        @Query("social_icon") social_icon: String,
//        @Query("frame_type_id") frame_type_id: Int,
//        @Query("instagram_id") instagram_id: String,
//        @Query("fb_id") fb_id: String
//    ): Deferred<Response<FrameListModel?>>?
//
//    @GET("bframes/get")
//    fun getFrameList1(
//        @Header("Authorization") auth: String,
//        @Query("frame_type_id") frame_type_id: String,
//        @Query("business_id") business_id: String,
//        @Query("logo") logo: String,
//        @Query("business_image") business_image: String,
//        @Query("name") name: String,
//        @Query("name_length") name_length: String,
//        @Query("position") position: String,
//        @Query("position_length") position_length: String,
//        @Query("tagline") tagline: String,
//        @Query("tagline_length") tagline_length: String,
//        @Query("email") email: String,
//        @Query("email_length") email_length: String,
//        @Query("mobile_no") mobile_no: String,
//        @Query("mobile_no1") mobile_no1: String,
//        @Query("party_name") party_name: String,
//        @Query("party_name_length") party_name_length: String,
//        @Query("business_name") business_name: String,
//        @Query("business_name_length") business_name_length: String,
//        @Query("fb_id") fb_id: String,
//        @Query("instagram_id") instagram_id: String,
//        @Query("social_icon") social_icon: String,
//        @Query("address") address: String,
//        @Query("address_length") address_length: String,
//        @Query("website") website: String,
//        @Query("website_length") website_length: String
//    ): Deferred<Response<FrameListModel?>>?
//
//
//    //    @Query("business_id") business_id : String,
//    @GET("profile/type/get")
//    fun getProfileList(@Header("Authorization") auth: String): Deferred<Response<BusinessTypeModel?>>?
//
//
//    @FormUrlEncoded
//    @POST("user/frame/save")
//    fun saveFrame(
//        @Header("Authorization") auth: String,
//        @Field("business_id") business_id: String,
//        @Field("frame_ids") frame_ids: String
//    ): Deferred<Response<FrameListModel?>>?
//
//    @FormUrlEncoded
//    @POST("user/active/log")
//    fun activeLogBackground(
//        @Header("Authorization") auth: String,
//        @Field("unique_id") unique_id: String,
//        @Field("start_datetime") start_datetime: String,
//        @Field("end_datetime") end_datetime: String
//    ): Call<ActiveLogModel>
//
//
//    @Multipart
//    @POST("user/download/save")
//    fun saveUserImage(
//        @HeaderMap token: MutableMap<String, String>,
//        @Part("data_id") data_id: RequestBody?,
//        @Part("frame_id") frame_id: RequestBody?,
//        @Part("unique_id") unique_id: RequestBody?,
//        @Part("type") type: RequestBody?,
//        @Part logo: MultipartBody.Part?
//    ): Deferred<Response<SaveImageModel?>>?
//
//
//    @Multipart
//    @POST("http://www.slizer.oceanmtechrnd.com/jpg_to_png")
//    fun PNGConverter2(@Part logo: MultipartBody.Part?): Deferred<Response<ResponseBody?>>?
//
//
//    @GET("settings/get")
//    fun getSetting(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<SettingModel?>>?
//
//    @GET("cms/get?slug=terms-condition")
//    fun getTermsCondition(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<TermsConditionModel?>>?
//
//    @GET("notifications/get")
//    fun getNotification(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<NotificationModel?>>?
//
//    @FormUrlEncoded
//    @POST("dmt-login")
//    fun getCRMData(
//        @Field("mobile_no") mobile_no: String?,
//        @Field("member_id") member_id: String?
//    ): Deferred<Response<CRMLoginModel?>>?
//
//    @FormUrlEncoded
//    @POST("business/logo/delete")
//    fun DeleteBusinessLogo(
//        @Header("Authorization") auth: String,
//        @Field("business_id") business_id: String?
//    ): Deferred<Response<BusinessListModel?>>?
//
//    @POST("user/logo/delete")
//    fun DeleteUserLogo(
//        @Header("Authorization") auth: String
//    ): Deferred<Response<BusinessListModel?>>?
//
//    @GET("mq/backgrounds/get")
//    fun BackgroundList(
//        @Header("Authorization") auth: String,
//        @QueryMap options: Map<String, String>
//    ): Call<BackgroundListModel>?
//
//    @Multipart
//    @POST("user/custum-post/download/save")
//    fun saveUserCustomeImage(
//        @HeaderMap token: MutableMap<String, String>,
//        @Part("unique_id") unique_id: RequestBody?,
//        @Part logo: MultipartBody.Part?
//    ): Deferred<Response<BusinessListModel?>>?
//
//    @FormUrlEncoded
//    @POST("transaction/save")
//    fun TransactionApi(
//        @Header("Authorization") auth: String,
//        @Field("plan_id") plan_id: String?,
//        @Field("transaction_id") transaction_id: String?,
//        @Field("payment_response") payment_response: String?,
//        @Field("payment_status") payment_status: String?,
//        @Field("business_id") business_id: String?,
//        @Field("payment_method_id") payment_method_id: String?
//    ): Deferred<Response<QuotesCategoryModel?>>?
//
//    @GET("payment/method/get")
//    fun PaymentMethodList(
//        @Header("Authorization") auth: String
//    ): Call<PaymentMethodModel?>?
//
//    @GET("mq/category/get")
//    fun QuotesCategoryList(
//        @Header("Authorization") auth: String
//    ): Call<QuotesCategoryModel?>?
//
//    @GET("transactions/get")
//    fun TransactionHistory(
//        @Header("Authorization") auth: String,
//        @Query("to_date") to_date: String,
//        @Query("from_date") from_date: String,
//        @Query("status") status: String,
//        @Query("s") s: String
//    ): Call<PaymentHistoryModel?>?
//
//    @GET("mq/quotes/get")
//    fun GetQuotes(
//        @Header("Authorization") auth: String,
//        @Query("page") page: Int,
//        @Query("user_language_id") user_language_id: String,
//        @Query("category_id") category_id: String
//    ): Call<CategoryQuotesaModel?>?
//
//    @GET("home/visiting-cards/get")
//    fun DMVCCategoryList(
//        @Header("Authorization") auth: String,
//    ): Deferred<Response<DBVCCategoryModel?>>?
//
//    //    @FormUrlEncoded
////    @POST("transaction/save")
////    fun TransactionApi(
////        @Header("Authorization") auth: String,
////        @Field("plan_id") plan_id: String?,
////        @Field("payment_response") payment_response: String?,
////        @Field("payment_status") payment_status: String?,
////        @Field("business_id") business_id: String?,
////        @Field("transaction_id") transaction_id: String?
////    ): Call<QuotesCategoryModel?>?
//    @GET("products/get")
//    fun GetVisitingCards(
//        @Header("Authorization") auth: String,
//        @Query("page") page: String,
//        @Query("more_id") user_language_id: String,
//        @Query("type") type: String,
//        @Query("post_type") post_type: String
//    ): Call<CardListModel>
//
//    @GET("custom/frames/get")
//    fun GetCustomFrame(
//        @Header("Authorization") auth: String,
//        @Query("business_id") page: String
//    ): Call<CustomFrameListModel>
//
//    @GET("home/custom-frame/get")
//    fun GetCustomFrameHome(
//        @Header("Authorization") auth: String,
//        @Query("post_type") post_type: String
//    ): Call<CustomFrameListHomeModel>
//
//    @GET("custom/frames/get")
//    fun GetCustomFrameUser(
//        @Header("Authorization") auth: String,
//        @Query("post_type") post_type: String,
//        @Query("business_id") business_id: String
//    ): Call<UserCustomFrameListModel>
//
//    @FormUrlEncoded
//    @POST("user/frame/save")
//    fun GetCustomFrameSave(
//        @Header("Authorization") auth: String,
//        @Field("frame_ids") frame_ids: String,
//        @Field("business_id") business_id: String
//    ): Call<LogoListModel>
//
//    @GET("home/logo/get")
//    fun GetCustomLogoHome(
//        @Header("Authorization") auth: String
//    ): Call<LogoListModel>
//
//    @GET("product2.json")
//    fun getProductDemo(): Call<ProductDemoModel>?
//
//    @GET("visiting1.json")
//    fun getTemplateDemo(): Call<ProductDemoModel>?
//
//    @FormUrlEncoded
//    @POST("order/save")
//    fun OrderSave(
//        @Header("Authorization") auth: String,
//        @Field("type") type: String?,
//        @Field("total_amount") total_amount: String?,
//        @Field("products") products: String?
//    ): Deferred<Response<FrameOrderSaveModel>>
//
//    @FormUrlEncoded
//    @POST("order/payment/save")
//    fun OrderPayment(
//        @Header("Authorization") auth: String,
//        @Field("order_id") order_id: String?,
//        @Field("transaction_id") transaction_id: String?,
//        @Field("payment_response") payment_response: String?,
//        @Field("payment_status") payment_status: String?,
//        @Field("payment_method_id") payment_method_id: String?
//    ): Deferred<Response<QuotesCategoryModel?>>?
//
//    @GET("orders/get")
//    fun GetOrderHistory(
//        @Header("Authorization") auth: String,
//        @Query("type") type: String,
//        @Query("order_date") order_date: String,
//        @Query("status") status: String,
//        @Query("s") s: String
//    ): Call<OrderHistoryModel>
//
//
//    @Multipart
//    @POST("feedback/save")
//    fun FeedbackSave(
//        @HeaderMap token: MutableMap<String, String>,
//        @Part("title") title: RequestBody?,
//        @Part("description") description: RequestBody?,
//        @Part file_url: MultipartBody.Part?
//    ): Deferred<Response<LogoListModel?>>?
//
//    @Multipart
//    @POST("feedback/save")
//    fun FeedbackSave2(
//        @HeaderMap token: MutableMap<String, String>,
//        @Part("title") title: RequestBody?,
//        @Part("description") description: RequestBody?,
//    ): Deferred<Response<LogoListModel?>>?
}