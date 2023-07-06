package com.ocean.postermaker.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.ocean.postermaker.R;

import java.io.File;
import java.util.ArrayList;

public class ImageUtils {
    public static double small = 1.2;
    public static float screenHeight;
    public static float screenWidth;
    public static float screenHeightPreview;
    public static float screenWidthPreview;
    public static String frameRatio = "1:1";
    public static int frameWidth = 1080;
    public static int frameHeight = 1080;
    public static String type = "Post";
    public static String typeHome = "Post";

    public static int previewPostCard[] = {R.drawable.preview1, R.drawable.preview2, R.drawable.preview3, R.drawable.preview4, R.drawable.preview5};

    public static int[] getSizeUsingRatio(int wRatio, int hRatio, int canvasWidth, int canvasHeight) {
        int[] iArr = {canvasWidth, canvasHeight};
        float f = ((float) wRatio) / ((float) hRatio);
        float f2 = (float) canvasWidth;
        float f3 = f2 / f;
        float f4 = (float) canvasHeight;
        float f5 = f * f4;
        if (f2 <= f2 && f3 <= f4) {
            iArr[0] = (int) f2;
            iArr[1] = (int) f3;
        } else if (f5 <= f2 && f4 <= f4) {
            iArr[0] = (int) f5;
            iArr[1] = (int) f4;
        }
        return iArr;
    }

    public static float dpToPx(Context context, float f) {
        context.getResources();
        return f * Resources.getSystem().getDisplayMetrics().density;
    }


    public static Bitmap getBitmapFromStorage() {
        String photoPath = "/storage/emulated/0/Documents/VideoData/8/Frame1/Background-01.png";
        return BitmapFactory.decodeFile(photoPath);
    }


    public static Bitmap getBitmapAsRatio(String ratio, Bitmap bitmap2, boolean preview) {

        String[] split = ratio.split(":");
        if (isNumericRegex(split[0]) && isNumericRegex(split[1])) {
            bitmap2 = cropInRatio(bitmap2, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            Bitmap finalBitmap = bitmap2;
            if (preview)
                return ImageUtils.resizeBitmap(finalBitmap, (int) screenWidthPreview, (int) screenHeightPreview);
            else
                return ImageUtils.resizeBitmap(finalBitmap, (int) screenWidth, (int) screenHeight);
        } else {

            return ImageUtils.resizeBitmap(Bitmap.createBitmap((int) screenWidth, (int) screenHeight, Bitmap.Config.ARGB_8888), (int) screenWidth, (int) screenHeight);
        }
    }

    public static Bitmap getBitmapAsRatioPreview(String ratio, Bitmap bitmap2) {

        String[] split = ratio.split(":");
        bitmap2 = cropInRatio(bitmap2, Integer.parseInt(split[0]), Integer.parseInt(split[1]));


        Bitmap finalBitmap = bitmap2;

        return ImageUtils.resizeBitmap(finalBitmap, (int) screenWidthPreview, (int) screenWidthPreview);

    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        try {
            float width = (float) bitmap.getWidth();
            float height = (float) bitmap.getHeight();
            float width2 = ((float) bitmap.getWidth()) / ((float) bitmap.getHeight());
            float f = (float) i;
            float f2 = f / width2;
            float f3 = (float) i2;
            float f4 = width2 * f3;
            if (f <= f && f2 <= f3) {
                width = (float) ((int) f);
                height = (float) ((int) f2);
            } else if (f4 <= f && f3 <= f3) {
                width = (float) ((int) f4);
                height = (float) ((int) f3);
            }
            return Bitmap.createScaledBitmap(bitmap, (int) width, (int) height, false);
        } catch (Error | Exception e) {
            e.printStackTrace();
            return bitmap;
        }
    }

    public static boolean isNumericRegex(String str) {
        if (str == null)
            return false;
        return str.matches("-?\\d+");
    }


    public static Bitmap cropInRatio(Bitmap bitmap2, int i, int i2) {
        Bitmap bitmap3;
        Bitmap bitmap4 = null;

        float width = (float) bitmap2.getWidth();
        float height = (float) bitmap2.getHeight();

        float f = getnewHeight(i, i2, width, height);
        float f2 = getnewWidth(i, i2, width, height);

        if (f2 <= width) {
            if (f2 < width) {
                if (width == 0)
                    width = 1;

                if (height == 0)
                    height = 1;

                if (f2 == 0)
                    f2 = 1;

                bitmap4 = Bitmap.createBitmap(bitmap2, (int) ((width - f2) / 2.0f), 0, (int) f2, (int) height);
            } else {
                // TODO: 19, Feb 2020 Add Else condition because other API template no work
                bitmap4 = bitmap2;
            }
        }

        if (f <= height) {
            if (f < height) {
                bitmap3 = Bitmap.createBitmap(bitmap2, 0, (int) ((height - f) / 2.0f), (int) width, (int) f);
                if (f2 == width || f != height) {
                    return bitmap3;
                }
                return bitmap2;
            }
        }

        bitmap3 = bitmap4;
        if (f2 == width) {
        }
        return bitmap3;
    }


    public static String getDataDir(Context activity) {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator + "VideoData";
    }

    public static String getVideoDataDir(Context activity, int id) {
        return getDataDir(activity) + File.separator + id;
    }


//    Mathematics Calculation

    private static float getnewHeight(int i, int i2, float f, float f2) {
        return (((float) i2) * f) / ((float) i);
    }

    private static float getnewWidth(int i, int i2, float f, float f2) {
        return (((float) i) * f2) / ((float) i2);
    }


    public static ArrayList<Float> getXY(int x, int y) {

        String[] aryRatio = frameRatio.split(":");
        int wRatio = Integer.parseInt(aryRatio[0]);
        int hRatio = Integer.parseInt(aryRatio[1]);

        int[] sizeUsingRatio = ImageUtils.getSizeUsingRatio(wRatio, hRatio, frameWidth, frameHeight);
        int[] sizeUsingRatio2 = ImageUtils.getSizeUsingRatio(wRatio, hRatio, (int) screenWidth, (int) screenHeight);
        float ratioWidth = ((float) sizeUsingRatio2[0]) / ((float) sizeUsingRatio[0]);
        float ratioHeight = ((float) sizeUsingRatio2[1]) / ((float) sizeUsingRatio[1]);
        ArrayList<Float> xy = new ArrayList<>();
        float X = x * ratioWidth;
        float Y = y * ratioHeight;
        xy.add(X);
        xy.add(Y);
        return xy;
    }

    public static ArrayList<Float> getXYPreview(int x, int y) {

        String[] aryRatio = frameRatio.split(":");
        int wRatio = Integer.parseInt(aryRatio[0]);
        int hRatio = Integer.parseInt(aryRatio[1]);

        int[] sizeUsingRatio = ImageUtils.getSizeUsingRatio(wRatio, hRatio, frameWidth, frameHeight);
        int[] sizeUsingRatio2 = ImageUtils.getSizeUsingRatio(wRatio, hRatio, (int) screenWidthPreview, (int) screenWidthPreview);
        float ratioWidth = ((float) sizeUsingRatio2[0]) / ((float) sizeUsingRatio[0]);
        float ratioHeight = ((float) sizeUsingRatio2[1]) / ((float) sizeUsingRatio[1]);
        ArrayList<Float> xy = new ArrayList<>();
        float X = x * ratioWidth;
        float Y = y * ratioHeight;
        xy.add(X);
        xy.add(Y);
        return xy;
    }

    public static ArrayList<Float> getXYPreviewStory(int x, int y) {

//        String[] aryRatio = frameRatio.split(":");
//        int wRatio = Integer.parseInt(aryRatio[0]);
//        int hRatio = Integer.parseInt(aryRatio[1]);
//
//        int[] sizeUsingRatio = ImageUtils.getSizeUsingRatio(wRatio, hRatio, frameWidth, frameHeight);
//        int[] sizeUsingRatio2 = ImageUtils.getSizeUsingRatio(wRatio, hRatio, (int) screenWidthPreview, (int) screenWidthPreview);
//        float ratioWidth = ((float) sizeUsingRatio2[0]) / ((float) sizeUsingRatio[0]);
//        float ratioHeight = ((float) sizeUsingRatio2[1]) / ((float) sizeUsingRatio[1]);
        ArrayList<Float> xy = new ArrayList<>();
        float X = (x * screenWidthPreview) / 1080;
        float Y = (y * screenHeightPreview) / 1920;
        xy.add(X);
        xy.add(Y);
        return xy;
    }

    public static ArrayList<Integer> getTextSize(int size, int tickness) {
        String[] aryRatio = frameRatio.split(":");
        int wRatio = Integer.parseInt(aryRatio[0]);
        int hRatio = Integer.parseInt(aryRatio[1]);

        int[] sizeUsingRatio = ImageUtils.getSizeUsingRatio(wRatio, hRatio, frameWidth, frameHeight);
        int[] sizeUsingRatio2 = ImageUtils.getSizeUsingRatio(wRatio, hRatio, (int) screenWidth, (int) screenHeight);
        int ratioWidth = ((int) sizeUsingRatio2[0]) / ((int) sizeUsingRatio[0]);
        int ratioHeight = ((int) sizeUsingRatio2[1]) / ((int) sizeUsingRatio[1]);
        ArrayList<Integer> xy = new ArrayList<>();
        int X = size * ratioWidth;
        int Y = tickness * ratioHeight;
        xy.add(X);
        xy.add(Y);
        return xy;
    }

    public static ArrayList<Integer> getTextSizePreview(int size, int tickness) {
        String[] aryRatio = frameRatio.split(":");
        int wRatio = Integer.parseInt(aryRatio[0]);
        int hRatio = Integer.parseInt(aryRatio[1]);

        int[] sizeUsingRatio = ImageUtils.getSizeUsingRatio(wRatio, hRatio, frameWidth, frameHeight);
        int[] sizeUsingRatio2 = ImageUtils.getSizeUsingRatio(wRatio, hRatio, (int) screenWidthPreview, (int) screenHeightPreview);
        int ratioWidth = ((int) sizeUsingRatio2[0]) / ((int) sizeUsingRatio[0]);
        int ratioHeight = ((int) sizeUsingRatio2[1]) / ((int) sizeUsingRatio[1]);
        ArrayList<Integer> xy = new ArrayList<>();
        int X = size * ratioWidth;
        int Y = tickness * ratioHeight;
        xy.add(X);
        xy.add(Y);
        return xy;
    }


    public static ArrayList<Integer> getWH(int w, int h) {
        String[] aryRatio = frameRatio.split(":");
        if (isNumericRegex(aryRatio[0]) && isNumericRegex(aryRatio[1])) {
            int wRatio = Integer.parseInt(aryRatio[0]);
            int hRatio = Integer.parseInt(aryRatio[1]);

            int[] sizeUsingRatio = ImageUtils.getSizeUsingRatio(wRatio, hRatio, frameWidth, frameHeight);
            int[] sizeUsingRatio2 = ImageUtils.getSizeUsingRatio(wRatio, hRatio, (int) screenWidth, (int) screenHeight);
            float ratioWidth = ((float) sizeUsingRatio2[0]) / ((float) sizeUsingRatio[0]);
            float ratioHeight = ((float) sizeUsingRatio2[1]) / ((float) sizeUsingRatio[1]);
            ArrayList<Integer> xy = new ArrayList<>();
            int X = Math.round(((float) w) * ratioWidth);
            int Y = Math.round(((float) h) * ratioHeight);
            xy.add(X);
            xy.add(Y);
            return xy;
        } else {
            ArrayList<Integer> xy = new ArrayList<>();
            xy.add(1080);
            xy.add(1080);
            return xy;
        }
    }

    public static ArrayList<Integer> getWHPreview(int w, int h) {
        String[] aryRatio = frameRatio.split(":");
        int wRatio = Integer.parseInt(aryRatio[0]);
        int hRatio = Integer.parseInt(aryRatio[1]);
        Log.d("SagarSagarSagarSagar", "Logo Remove : " + ImageUtils.type);
        Log.d("SagarSagarSagarSagar", "Logo Remove : " + screenWidthPreview);
        Log.d("SagarSagarSagarSagar", "Logo Remove : " + screenWidthPreview);

        int[] sizeUsingRatio = ImageUtils.getSizeUsingRatio(wRatio, hRatio, frameWidth, frameHeight);
        int[] sizeUsingRatio2 = ImageUtils.getSizeUsingRatio(wRatio, hRatio, (int) screenWidthPreview, (int) screenHeightPreview);
        float ratioWidth = ((float) sizeUsingRatio2[0]) / ((float) sizeUsingRatio[0]);
        float ratioHeight = ((float) sizeUsingRatio2[1]) / ((float) sizeUsingRatio[1]);

        ArrayList<Integer> xy = new ArrayList<>();
        int X = Math.round(((float) w) * ratioWidth);
        int Y = Math.round(((float) h) * ratioHeight);
        xy.add(X);
        xy.add(Y);
        return xy;
    }

    public static ArrayList<Integer> getWHPreviewStory(int w, int h) {
//        String[] aryRatio = frameRatio.split(":");
//        int wRatio = Integer.parseInt(aryRatio[0]);
//        int hRatio = Integer.parseInt(aryRatio[1]);
//        int[] sizeUsingRatio = ImageUtils.getSizeUsingRatio(wRatio, hRatio, frameWidth, frameHeight);
//        int[] sizeUsingRatio2 = ImageUtils.getSizeUsingRatio(wRatio, hRatio, (int) screenWidthPreview, (int) screenHeightPreview);
//        float ratioWidth = ((float) sizeUsingRatio2[0]) / ((float) sizeUsingRatio[0]);
//        float ratioHeight = ((float) sizeUsingRatio2[1]) / ((float) sizeUsingRatio[1]);

        ArrayList<Integer> xy = new ArrayList<>();
        int X = (int) ((w * screenWidthPreview) / 1080);
        int Y = (int) ((h * screenHeightPreview) / 1920);
        xy.add(X);
        xy.add(Y);
        return xy;
    }

}
