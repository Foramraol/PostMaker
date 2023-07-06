package com.oceanmtech.dmt.Utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.Settings
import android.util.Base64
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.ocean.postermaker.OceanmtechPosterMakerApp
import com.ocean.postermaker.Utils.ImageUtils
import java.io.*
import java.net.URISyntaxException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.math.roundToInt


object Utils {


    @kotlin.jvm.JvmField
    public var profileImage: Bitmap? = null
    public var customFramImage: Bitmap? = null
    public var flagAutoSlid: Boolean = true
    public var dataFoundCode = 200
    public var tokenExpiredCode = 401
    public var apiNotFoundCode = 402
    public var dataNotFoundCode = 404
    public var validationCode = 405
    public var userLimitCode = 406
    public var internalServerCode = 500
    public var loadMyPhoto: Boolean = true
    public var frameResFolder = "frame_res"


    @SuppressLint("HardwareIds")
    fun getDeviceId(mContext: Context): String {
        return Settings.Secure.getString(mContext.contentResolver, Settings.Secure.ANDROID_ID)
    }

//    fun singleImagePart(key: String, path: String): MultipartBody.Part? {
//        var formData: MultipartBody.Part? = null
//        if (!TextUtils.isEmpty(path)) {
//            val file = File(path)
//            val requestBody = MultipartBody.create(MediaType.parse("image/*"), file)
//            formData = MultipartBody.Part.createFormData(key, file.name, requestBody)
//        } else {
//            val attachmentEmpty = RequestBody.create(MediaType.parse("text/plain"), "")
//            formData = MultipartBody.Part.createFormData("attachment", "", attachmentEmpty)
//        }
//        return formData
//    }

    fun printHashKey(pContext: Context) {
        try {
            val info =
                pContext.packageManager.getPackageInfo(
                    pContext.packageName,
                    PackageManager.GET_SIGNATURES
                )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey: String = String(Base64.encode(md.digest(), 0))
                Log.d("TAG", "printHashKey: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
        } catch (e: Exception) {
        }
    }

    /*
 * Gets the file path of the given Uri.
 */
    @SuppressLint("NewApi")
    @Throws(URISyntaxException::class)
    fun getPath(context: Context, uri: Uri): String? {
        var uri = uri
        val needToCheckUri = Build.VERSION.SDK_INT >= 19
        var selection: String? = null
        var selectionArgs: Array<String>? = null
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (needToCheckUri && DocumentsContract.isDocumentUri(context.applicationContext, uri)) {
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
//                uri = ContentUris.withAppendedId(
//
//                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
//                )
                val file = File(
                    context.getCacheDir(),
                    "Test" + Objects.requireNonNull(
                        context.getContentResolver().getType(uri)
                    )!!.split("/").get(1)
                )
                try {
                    context.getContentResolver().openInputStream(uri).use { inputStream ->
                        FileOutputStream(file).use { output ->
                            val buffer = ByteArray(4 * 1024) // or other buffer size
                            var read: Int
                            while (inputStream!!.read(buffer).also { read = it } != -1) {
                                output.write(buffer, 0, read)
                            }
                            output.flush()
                            return file.absolutePath
                        }
                    }
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
                return null
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                if ("image" == type) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                selection = "_id=?"
                selectionArgs = arrayOf(split[1])
            }
        }
        if ("content".equals(uri.scheme, ignoreCase = true)) {
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            var cursor: Cursor? = null
            try {
                cursor =
                    context.contentResolver.query(uri, projection, selection, selectionArgs, null)
                val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index)
                }
            } catch (e: java.lang.Exception) {
            }
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }


    fun getScreenWidth(activity: Context): Int {
        val displayMetrics = DisplayMetrics()
        (activity as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        return displayMetrics.widthPixels
    }

    fun setViewSize(activity: Context, view: View, preview: Boolean) {
        val layoutParams = view.layoutParams
        if (preview) {
            if (ImageUtils.type.equals("Story")) {

                ImageUtils.screenWidthPreview =
                    ((((getScreenWidth(activity) / ImageUtils.small) * 9)) / 16).toFloat()
                ImageUtils.screenHeightPreview =
                    (getScreenWidth(activity) / ImageUtils.small).toFloat()

                var widthStory: Int =
                    (((getScreenWidth(activity) / ImageUtils.small) * 9) / 16).roundToInt()
                layoutParams.width = widthStory
                layoutParams.height = (getScreenWidth(activity) / ImageUtils.small).roundToInt()
            } else {

                layoutParams.width = (getScreenWidth(activity) / ImageUtils.small).roundToInt()
                layoutParams.height = (getScreenWidth(activity) / ImageUtils.small).roundToInt()
            }
        } else {
            layoutParams.width = getScreenWidth(activity)
            layoutParams.height = getScreenWidth(activity)
            Log.d("layoutParams.width", layoutParams.width.toString())
            Log.d("layoutParams.height", layoutParams.height.toString())
        }
        view.layoutParams = layoutParams
    }

    fun setViewSizeEditor(activity: Activity, view: View, preview: Boolean) {
        val layoutParams = view.layoutParams
        if (preview) {
//            if (ImageUtils.type.equals("Story")) {
//
//                ImageUtils.screenWidthPreview = ((((getScreenWidth(activity) / ImageUtils.small)*9))/16).toFloat()
//                ImageUtils.screenHeightPreview = (getScreenWidth(activity) / ImageUtils.small).toFloat()
//
//                var widthStory : Int = (((getScreenWidth(activity) / ImageUtils.small)*9)/16).roundToInt()
//                layoutParams.width = widthStory
//                layoutParams.height = (getScreenWidth(activity) / ImageUtils.small).roundToInt()
//            } else {

            layoutParams.width = (getScreenWidth(activity) / ImageUtils.small).roundToInt()
            layoutParams.height = (getScreenWidth(activity) / ImageUtils.small).roundToInt()
//            }
        } else {
            layoutParams.width = getScreenWidth(activity)
            layoutParams.height = getScreenWidth(activity)
        }
        view.layoutParams = layoutParams
    }

    fun setViewSizeStory(activity: Activity, view: View, preview: Boolean) {
        val layoutParams = view.layoutParams
        if (preview) {
//            if (ImageUtils.type.equals("story")) {

            ImageUtils.screenWidthPreview =
                ((((getScreenWidth(activity) / ImageUtils.small) * 9)) / 16).toFloat()
            ImageUtils.screenHeightPreview = (getScreenWidth(activity) / ImageUtils.small).toFloat()


            var widthStory: Int =
                (((getScreenWidth(activity) / ImageUtils.small) * 9) / 16).roundToInt()
            layoutParams.width = widthStory
            layoutParams.height = (getScreenWidth(activity) / ImageUtils.small).roundToInt()
//            } else {
//
//                layoutParams.width = (getScreenWidth(activity) / ImageUtils.small).roundToInt()
//                layoutParams.height = (getScreenWidth(activity) / ImageUtils.small).roundToInt()
//            }
        } else {
            layoutParams.width = getScreenWidth(activity)
            layoutParams.height = getScreenWidth(activity)
        }
        view.layoutParams = layoutParams
    }

    fun getBitmapFromView(view: View): Bitmap {

        val returnedBitmap: Bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
//        val paint = Paint()
//        paint.isAntiAlias = true
//        paint.isFilterBitmap = true
//        paint.isDither = true

        val canvas = Canvas(returnedBitmap)
//        canvas.drawColor(-1)
//        canvas.drawBitmap(returnedBitmap, 0.0f, 0.0f, paint)
        view.draw(canvas)
        return returnedBitmap
    }

    fun getBitmapFromViewPNG(view: View): Bitmap {
        val returnedBitmap: Bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.isFilterBitmap = true
        paint.isDither = true

        val canvas = Canvas(returnedBitmap)
//        canvas.drawColor(-1)
        canvas.drawBitmap(returnedBitmap, 0.0f, 0.0f, paint)
        view.draw(canvas)
        return returnedBitmap
    }

    fun openGallary(activity: Activity) {
        val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        i.type = "image/*";
        activity.startActivityForResult(i, 101)
    }

    fun ReadFromfile(fileName: String?, context: Context): String? {
        val returnString = StringBuilder()
        var fIn: InputStream? = null
        var isr: InputStreamReader? = null
        var input: BufferedReader? = null
        try {
            fIn = context.resources.assets
                .open(fileName!!, Context.MODE_WORLD_READABLE)
            isr = InputStreamReader(fIn)
            input = BufferedReader(isr)
            var line: String? = ""
            while (input.readLine().also { line = it } != null) {
                returnString.append(line)
            }
        } catch (e: java.lang.Exception) {
            e.message
        } finally {
            try {
                if (isr != null) isr.close()
                if (fIn != null) fIn.close()
                if (input != null) input.close()
            } catch (e2: java.lang.Exception) {
                e2.message
            }
        }
        return returnString.toString()
    }

    fun showSettingsDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Title")
        builder.setMessage("Message")
        builder.setPositiveButton(
            "Go to Settings"
        ) { dialog, which ->
            dialog.cancel()
            openSettings(context)
        }
        builder.setNegativeButton(
            "Cancle"
        ) { dialog, which -> dialog.cancel() }
        builder.show()
    }

    fun openSettings(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        ActivityCompat.startActivityForResult((context as Activity), intent, 102, null)
    }
}