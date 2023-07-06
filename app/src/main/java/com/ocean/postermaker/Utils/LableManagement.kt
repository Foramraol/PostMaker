package com.oceanmtech.dmt.Utils

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import me.grantland.widget.AutofitTextView

class LableManagement {
    companion object{
        fun textView(textView : AutofitTextView, textValue : String){

            var textBreck= textValue.replace("==".toRegex(), "\n")
            textView.text= textBreck
        }

        fun textView1(textView : TextView, textValue : String){

            var textBreck= textValue.replace("==".toRegex(), "\n")
            textView.text= textBreck
        }
        fun textViewHint(textView : AutofitTextView, textValue : String){

            var textBreck= textValue.replace("==".toRegex(), "\n")
            textView.hint= textBreck
        }
        fun button(buttonView : Button, textValue : String){

            buttonView.text= textValue
        }
        fun editTextHint(ediView : AppCompatEditText, textValue : String){
            ediView.hint = textValue
        }
        fun editTextHintOld(ediView : EditText, textValue : String){
            ediView.hint = textValue
        }
    }
}