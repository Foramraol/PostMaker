package com.ocean.postermaker.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

public class ImageStorage {
    public static String saveToSdCard(Bitmap bitmap, String filename, Context context,String FolderName) {

        String stored = null;
        File sdcard = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File folder = new File(sdcard.getAbsoluteFile(), FolderName);//the dot makes this directory hidden to the user
        folder.mkdirs();
        File file = new File(folder.getAbsoluteFile(), filename);
        if (file.exists())
            return stored;

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            stored = "success";
            Log.w("SagarSagarSagare","Path : "+stored);

        } catch (Exception e) {
            e.printStackTrace();
            Log.w("SagarSagarSagare","Path : "+e.getMessage());

        }

        return folder.getAbsoluteFile() + "/" + filename;
    }

    public static File getImage(Context context,String filename,String FolderName) {
        File mediaImage = null;
        try {
            File sdcard = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File folder = new File(sdcard.getAbsoluteFile(), FolderName);//the dot makes this directory hidden to the user
            File file = new File(folder.getAbsoluteFile(), filename);
            mediaImage = file;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mediaImage;
    }

    public static boolean checkifImageExists(Context context,String imagenam,String FolderName) {
        Bitmap b = null;
        File file = ImageStorage.getImage(context,"/" + imagenam,FolderName);
        String path = file.getAbsolutePath();
        if (!TextUtils.isEmpty(path) && new File(path).exists())
            b = BitmapFactory.decodeFile(path);

        if (b == null || b.equals("")) {
            return false;
        }
        return true;
    }
}
