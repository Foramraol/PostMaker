package com.ocean.postermaker.UI.LoginModule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import com.ocean.postermaker.AdManager.NativeBanner
import com.ocean.postermaker.Base.BaseActivity
import com.ocean.postermaker.R
import com.ocean.postermaker.UI.DashboardModule.HomeActivity
import com.ocean.postermaker.UI.LoginModule.Language.SelectLanguageActivity
import com.ocean.postermaker.databinding.ActivityLoginBinding
import com.oceanmtech.dmt.Data.DataManager
import com.oceanmtech.dmt.Data.ImportantDataManager
import com.oceanmtech.dmt.Utils.Utils
import com.oceanmtech.dmt.Utils.Utils.printHashKey
import com.oceanmtech.dmt.Utils.dismissLoader
import com.oceanmtech.dmt.Utils.displayToast
import com.oceanmtech.dmt.Utils.showLoader
import org.json.JSONObject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class LoginActivity : BaseActivity() {

    val context: LoginActivity = this@LoginActivity
    private lateinit var binding: ActivityLoginBinding
    lateinit var gso: GoogleSignInOptions
    lateinit var gsc: GoogleSignInClient
    private var account: GoogleSignInAccount? = null
    private var callbackManager: CallbackManager = CallbackManager.Factory.create()
    private var loginManager: LoginManager? = null
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenOrientation()
        binding = DataBindingUtil.setContentView(context, R.layout.activity_login)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail().build()
        gsc = GoogleSignIn.getClient(this, gso)
        account = GoogleSignIn.getLastSignedInAccount(this)
        printHashKey(context)
        loginManager = LoginManager.getInstance()
        faceBookLogin()
        NativeBanner.loadNativeBannerAds(context, binding.llAdContainerShare)
        binding.llAdContainerShare.visibility = View.VISIBLE;
        onCLick()
    }

    private fun faceBookLogin() {
        loginManager?.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    Log.d("llFaceBook", "onSuccess: $loginResult")
                    getUserDetails(loginResult!!.accessToken)
                }


                override fun onCancel() {
                    Log.d("llFaceBook", "onCancel: ")
                }

                override fun onError(exception: FacebookException) {
                    Log.d("llFaceBook", "onError: ")
                }
            })
    }

    private fun getUserDetails(accessToken: AccessToken) {
        val graphRequest = GraphRequest.newMeRequest(
            accessToken
        ) { user, response ->
            Log.d("TAG", "getUserDetails: user:: ${Gson().toJson(user)}")
            Log.d("TAG", "getUserDetails: response:: ${Gson().toJson(response)}")
            val id = user!!.optString("id")
            val name = user.optString("name")
            callApi()
//            submitLoginSocial(id, "F", name)
            Log.d("TAG", "getUserDetails: $id :: $name")
        }
        val bundle = Bundle()
        bundle.putString(
            "fields", "id,name"
        )
        graphRequest.parameters = bundle
        graphRequest.executeAsync()
    }

    private fun onCLick() {
        binding.llFaceBook.setOnClickListener {
            Log.d("llFaceBook", "onCLick: ${DataManager(context).isLogin()}")
            if (!DataManager(context).isLogin()) {
                showLoader(context)
                loginManager?.logInWithReadPermissions(
                    context,
                    Arrays.asList(
                        "public_profile"
                    )
                )
            } else {
                startActivity(Intent(context, HomeActivity::class.java))
                finish()
            }
        }
        binding.llGoogle.setOnClickListener {
            Log.d("TAG", "onCLick: ${DataManager(context).isLogin()}")
            if (!DataManager(context).isLogin()) {
                showLoader(context)
                val signInIntent: Intent = gsc.signInIntent
                startActivityForResult(signInIntent, 101)
            } else {
                startActivity(Intent(context, HomeActivity::class.java))
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("TAG", "onActivityResult: $resultCode::${Activity.RESULT_OK}")
        callbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 101) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        Log.d("TAG", "signInResult:${Gson().toJson(completedTask)}")
        try {
            account = completedTask.getResult(ApiException::class.java)
            Log.d("TAG", "signInResult:success code=${Gson().toJson(account)}")
            callApi()
            // Signed in successfully, show authenticated UI.
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("TAG", "signInResult:failed code=${e.toString()}")
        }
    }

    fun callApi() {
        viewModel.callLoginApi()
        viewModel.mData.observe(context, androidx.lifecycle.Observer {
            dismissLoader()
            if (it != null) {
                if (it.success && it.status == Utils.dataFoundCode) {
                    DataManager(context).setToken("Bearer " + it.data.token)
                    DataManager(context).setIsLogin(true)
                    if(it.data.is_new==1){
                        DataManager(context).setIsLoginNew(true)
                        startActivity(Intent(context, SelectLanguageActivity::class.java))
                        finish()
                    }else {
                        DataManager(context).setIsLoginNew(false)
                        startActivity(Intent(context, HomeActivity::class.java))
                        finish()
                    }
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
}