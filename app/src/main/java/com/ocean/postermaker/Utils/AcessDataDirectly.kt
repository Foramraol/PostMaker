package com.oceanmtech.dmt.Utils

import android.annotation.TargetApi
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import com.ocean.postermaker.R


var dialog: Dialog? = null

fun displayToast(context: Context, message: String) {

    Handler(Looper.getMainLooper()).post {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}

fun displayToast(context: Context, @StringRes message: Int) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(context, context.resources.getString(message), Toast.LENGTH_SHORT).show()
    }
}

fun isInternetAvailable(context: Context): Boolean {
    var connected = false
    try {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
//        if (connectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
//            connectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED
//        ) {
//            connected = true
//        } else
//            connected = false
//        return connected
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            connected = when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return isNetworkOnline1(
                    context
                )
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                else -> return false
            }
        } else {
            connected = when (connectivityManager.activeNetworkInfo?.type) {
                ConnectivityManager.TYPE_WIFI -> return isNetworkOnline1(context)
                ConnectivityManager.TYPE_MOBILE -> return true
                ConnectivityManager.TYPE_ETHERNET -> return true
                else -> return false
            }
        }
    } catch (e: Exception) {
        return connected
    }
}

@TargetApi(Build.VERSION_CODES.M)
fun isNetworkOnline1(context: Context): Boolean {
    var isOnline = false
    try {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            manager.getNetworkCapabilities(manager.activeNetwork) // need ACCESS_NETWORK_STATE permission
        isOnline =
            capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return isOnline
}

fun showLoader(context: Activity) {
    context.runOnUiThread(Runnable() {
        dismissLoader()
        dialog = Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setContentView(R.layout.dialog_loader)
            show()
        }
    });
}

fun dismissLoader() {

    try {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
            dialog = null
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun goTo(context: Context, intent: Intent) {
    context.startActivity(intent)
}

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService("input_method") as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
