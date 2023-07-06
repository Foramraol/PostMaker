package com.ocean.postermaker.UI.DashboardModule.SettingsModule

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.ocean.postermaker.AdManager.AdsUtils
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.util.FileUriUtils
import com.ocean.postermaker.UI.LoginModule.Language.SelectLanguageActivity
import com.ocean.postermaker.UI.LoginModule.Location.SelectLocationActivity
import com.ocean.postermaker.databinding.ActivityMyProfileBinding
import com.oceanmtech.dmt.Utils.Utils
import com.oceanmtech.dmt.Utils.dismissLoader
import com.oceanmtech.dmt.Utils.displayToast
import com.oceanmtech.dmt.Utils.showLoader
import kotlinx.android.synthetic.main.activity_my_profile.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.File

class MyProfileActivity : BaseActivity() {

    var mobileNo: String = ""
    val context: MyProfileActivity = this@MyProfileActivity
    private lateinit var binding: ActivityMyProfileBinding
    private val viewModel by viewModel<getProfileViewModel>()
    private val viewModelupdateProfile by viewModel<updateProfileViewModel>()
    var selectedLanguage: String = ""
    var selectedReligion: String = ""
    var selectedCountry: String = ""
    var selectedState: String = ""
    var Mother: String = ""
    var Father: String = ""
    var Spouse: String = ""
    var Children: String = ""
    var Brother: String = ""
    var Sister: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_my_profile)
        callApi()
        setTitle()
        AdsUtils.showGoogleBannerAd(context, binding.adView)
        onCLick()
    }

    fun callApi() {
        showLoader(context)
        viewModel.callgetPRofileViewModel()
        viewModel.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    Log.d("TAG", "callApi: ${Gson().toJson(it.data)}")
                    setDetails(it.data)
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

    fun callApiUpdateProfile(filePath: String) {
        showLoader(context)
        viewModelupdateProfile.callUpdateProfileViewModel(filePath)
        viewModelupdateProfile.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    Log.d("TAG", "callApi: ${Gson().toJson(it.data)}")
                    toast("Profile Updated Successfully.")
                    onBackPressed()
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

    fun callApiUpdateMobile() {
        showLoader(context)
        viewModelupdateProfile.callUpdateMobileViewModel()
        viewModelupdateProfile.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    Log.d("TAG", "callApi: ${Gson().toJson(it.data)}")
                    setDetails(it.data)
                    callApiUpdateProfile(filePath)
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

    private fun setDetails(data: ProfileModel.Data) {
        binding.tvSelectedLang.text = data.user_language_name
        binding.tvSelectedReligion.text = data.religion_name
        binding.tvDob.text = data.date_of_birth
        binding.tvSelectedCountry.text = data.country_name
        binding.tvSelectedState.text = data.state_name
        binding.edtName.setText(data.name)
        binding.edtMobileNo.setText(data.mobile_no)
        if (binding.edtMobileNo.text.toString().length >= 10) {
            binding.edtMobileNo.isEnabled = false
            mobileNo = data.mobile_no
        }
        binding.edtEmail.setText(data.email)
        binding.edtAnniversaryDate.setText(data.anniversary_date)
        Log.d("TAG", "setDetails: ${data.gender}")
        if (data.gender.equals("female")) {
            binding.rgGender.rbFemale.performClick()
            binding.rgGender.rbFemale.isSelected = true
            binding.rgGender.rbMale.isSelected = false
        } else if (data.gender.equals("male")) {
            binding.rgGender.rbMale.performClick()
            binding.rgGender.rbMale.isSelected = true
            binding.rgGender.rbFemale.isSelected = false
        }
    }

    private fun setTitle() {
        binding.toolbar.tvTitle.text = getString(R.string.my_profile)
        binding.toolbar.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.ivBack.setColorFilter(ContextCompat.getColor(context, R.color.white))
        binding.toolbar.tvTitle.visibility = View.VISIBLE
        binding.toolbar.ivHome.visibility = View.GONE
    }

    fun toast(str: String) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }

    private fun onCLick() {
        val PERMISSIONS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_AUDIO
            )
        } else {
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
        }
        binding.llUploadProfile.setOnClickListener {
            Dexter.withActivity(context)
                .withPermissions(
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_VIDEO,
                    Manifest.permission.READ_MEDIA_AUDIO
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.areAllPermissionsGranted()) {
//                                showLoader(context)
                            ImagePicker.Companion.with(context)
                                .galleryOnly()
                                .maxResultSize(1080, 1080)
                                .start(106)
//                                openGallary(context)
                        }
                        if (report.isAnyPermissionPermanentlyDenied) {
                            Utils.showSettingsDialog(
                                context
                            )
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: List<PermissionRequest?>?,
                        token: PermissionToken
                    ) {
                        token.continuePermissionRequest()
                    }
                }).check()
        }
        binding.tvSave.setOnClickListener {
            if (binding.edtMobileNo.hint.toString().length < 10) {
                Log.d("TAG", "onCLick: ${binding.edtMobileNo.hint.toString().length}")
                toast("Please update your valid Mobile number.")
            } else if (binding.tvSelectedLang.text == "") {
                toast("Please update your language.")
            } else if (binding.tvSelectedReligion.text == "") {
                toast("Please update your religion.")
            } else if (binding.edtName.hint.toString() == "") {
                toast("Please enter your name.")
            } else if (binding.tvSelectedCountry.text == "") {
                toast("Please update your Country.")
            } else if (binding.tvSelectedState.text == "") {
                toast("Please update your State.")
            } else if (!binding.rbFemale.isSelected
                && !binding.rbMale.isSelected
            ) {
                toast("Please select your gender.")
            } else if (binding.edtMobileNo.text.toString() != mobileNo) {
                Log.d("TAG", "onCLick mono:1 ${binding.edtMobileNo.text.toString()} :: ${mobileNo}")
                callApiUpdateMobile()
            } else {
                Log.d("TAG", "onCLick mono:2 ${binding.edtMobileNo.text.toString()} :: ${mobileNo}")
                callApiUpdateProfile(filePath)
            }
        }
        binding.rlLanguage.setOnClickListener {
            startActivityForResult(
                Intent(context, SelectLanguageActivity::class.java).putExtra(
                    "Type",
                    "MyProfile"
                ), 101
            )
        }
        binding.rlCountry.setOnClickListener {
            Log.d("TAG", "onCLick: Country")
            startActivityForResult(
                Intent(context, SelectLocationActivity::class.java).putExtra(
                    "Type",
                    "MyProfile"
                ).putExtra("Location", "Country"), 102
            )
        }
        binding.rlState.setOnClickListener {
            Log.d("TAG", "onCLick: State")
            startActivityForResult(
                Intent(context, SelectLocationActivity::class.java).putExtra(
                    "Type",
                    "MyProfile"
                ).putExtra("Location", "State"), 103
            )
        }
        binding.rlReligion.setOnClickListener {
            Log.d("TAG", "onCLick: Religion")
            startActivityForResult(
                Intent(context, SelectReligionActivity::class.java).putExtra(
                    "Type",
                    "MyProfile"
                ).putExtra("Location", "Religion"), 104
            )
        }
        binding.rlFamilyMember.setOnClickListener {
            Log.d("TAG", "onCLick: Religion")
            startActivityForResult(
                Intent(context, FamilyDetailsActivity::class.java), 105
            )
        }
    }

    var filePath: String = ""
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK) {
            if (data != null) {
                selectedLanguage = data.extras?.getString("selectedLanguage")!!
                binding.tvSelectedLang.text = selectedLanguage
            }
        } else if (requestCode == 102 && resultCode == RESULT_OK) {
            if (data != null) {
                selectedCountry = data.extras?.getString("selectedCountry")!!
                binding.tvSelectedCountry.text = selectedCountry
            }
        } else if (requestCode == 103 && resultCode == RESULT_OK) {
            if (data != null) {
                selectedState = data.extras?.getString("selectedState")!!
                binding.tvSelectedState.text = selectedState
            }
        } else if (requestCode == 104 && resultCode == RESULT_OK) {
            if (data != null) {
                selectedReligion = data.extras?.getString("selectedReligion")!!
                binding.tvSelectedReligion.text = selectedReligion
            }
        } else if (requestCode == 105 && resultCode == RESULT_OK) {
            if (data != null) {
                Father = data.extras?.getString("Father")!!
                Mother = data.extras?.getString("Mother")!!
                Spouse = data.extras?.getString("Spouse")!!
                Children = data.extras?.getString("Children")!!
                Brother = data.extras?.getString("Brother")!!
                Sister = data.extras?.getString("Sister")!!
            }
        } else if (requestCode == 106) {
            dismissLoader()
            if (resultCode == RESULT_OK) {
                val fileUri = data!!.data
                Log.d("TAGfileUri", "fileUri: $fileUri")
                try {
                    filePath = FileUriUtils.getRealPath(this, data.data!!)
//                    filePath = BitmapUtils.getCropUriPath().toString()
                    binding.llUploadProfile.visibility = View.GONE
                    binding.llSelectedProfile.visibility = View.VISIBLE
                    binding.ivDeleteProfile.visibility = View.VISIBLE
                    Glide.with(context)
                        .load(File(filePath))
                        .placeholder(R.drawable.ic_upload)
//                    .transition(DrawableTransitionOptions.withCrossFade())
                        .into(binding.ivSelectedProfile)
                } catch (e: Exception) {
                    filePath = ""
//                    Toast.makeText(context, getError(data), Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
                Log.d("TAG", "onActivityResult: ${filePath}")
//                val imageuri = data!!.data
//                filePath = Utils.getPath(context, imageuri!!).toString()
//                if (filePath != null && filePath != "") {
//                    val intent = Intent(context, BackgroundActivity::class.java)
//                    intent.putExtra("imagePath", filePath)
//                    intent.putExtra("action", "profile")
//                    startActivityForResult(intent, 102)
//                } else Toast.makeText(
//                    context,
//                    "There is an issue in image pick. Please choose another image.",
//                    Toast.LENGTH_SHORT
//                ).show()


            }
        }
    }

}