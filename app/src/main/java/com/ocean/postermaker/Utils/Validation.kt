package com.oceanmtech.dmt.Utils

import android.text.TextUtils
import android.util.Patterns

class Validation {

    companion object {
        fun isValidMobile(phone: String): Boolean {
            return Patterns.PHONE.matcher(phone).matches()
        }


        fun isValidEmail(target: CharSequence?): Boolean {
            return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }

        fun isValidWebsite(target: CharSequence?): Boolean {
            return Patterns.WEB_URL.matcher(target).matches()
        }
    }
}