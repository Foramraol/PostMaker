package com.github.dhaval2404.imagepicker.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.R
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import com.github.dhaval2404.imagepicker.listener.DismissListener
import com.github.dhaval2404.imagepicker.listener.ResultListener

/**
 * Show Dialog
 *
 * @author Dhaval Patel
 * @version 1.0
 * @since 04 January 2018
 */
internal object DialogHelper {

    /**
     * Show Image Provide Picker Dialog. This will streamline the code to pick/capture image
     *
     */
    fun showChooseAppDialog(
        context: Context,
        listener: ResultListener<ImageProvider>,
        dismissListener: DismissListener?) {
        var dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_choose_app)
        dialog.getWindow()!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

//        val layoutParams1 = RelativeLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        dialog.findViewById<LinearLayout>(R.id.llImagePickerLayout).setLayoutParams(layoutParams1)

        dialog.findViewById<AppCompatTextView>(R.id.tvTitle).setText(ImagePicker.Companion.TitleLable)
        dialog.findViewById<AppCompatTextView>(R.id.tvGallery).setText(ImagePicker.Companion.GalleryLable)
        dialog.findViewById<AppCompatTextView>(R.id.tvCamera).setText(ImagePicker.Companion.CameraLable)
        dialog.findViewById<AppCompatTextView>(R.id.btLimitYES).setText(ImagePicker.Companion.CancelLable)
        dialog.setCancelable(true)

        dialog.findViewById<TextView>(R.id.btLimitYES).setOnClickListener {
            listener.onResult(null)
            dialog.dismiss()
        }
        dialog.findViewById<LinearLayout>(R.id.lytCameraPick).setOnClickListener {
            listener.onResult(ImageProvider.CAMERA)
            dialog.dismiss()
        }
        dialog.findViewById<LinearLayout>(R.id.lytGalleryPick).setOnClickListener {
            listener.onResult(ImageProvider.GALLERY)
            dialog.dismiss()
        }
        dialog.show()


    }
}
