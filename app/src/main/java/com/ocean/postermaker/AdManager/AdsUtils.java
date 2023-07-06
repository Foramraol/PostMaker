package com.ocean.postermaker.AdManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ocean.postermaker.R;

public class AdsUtils {
    static AdView mAdView;

    public static void showGoogleBannerAd(Context context, LinearLayout googleAdView) {

        googleAdView.setVisibility(View.VISIBLE);
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView = new AdView(context);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        //mAdView.setAdUnitId(PreferenceHelper.getString(Constants.banner, ""));
        mAdView.loadAd(adRequest);
        //Load Banner Ad
//        MobileAds.initialize(context, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
        googleAdView.addView(mAdView);
    }

    public static boolean canLoadInterstitial = true;
    public static Dialog progressDialog;
    public static InterstitialAd adMobInterstitial;

    public static void loadIndertial(Activity activity) {
        loadAdMobInterstitial(activity, "ca-app-pub-3940256099942544/1033173712");
    }

    private static void loadAdMobInterstitial(Activity activity, String adId) {
        adMobInterstitial = null;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, adId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                adMobInterstitial = null;
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                adMobInterstitial = interstitialAd;
                super.onAdLoaded(interstitialAd);
            }
        });
    }

    public static void showAdMobInterstitial(Activity activity) {
        try {
            Log.d("TAG", "showAdMobInterstitial: "+activity.getLocalClassName());
            if (adMobInterstitial != null && canLoadInterstitial) {
                adMobInterstitial.show(activity);
                adMobInterstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
//                        activity.onBackPressed();
                        adMobInterstitial = null;
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
//                        activity.onBackPressed();
                        adMobInterstitial = null;
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                    }
                });
//                if (adMobInterstitial == null)
//                    activity.onBackPressed();


            } else {
//                activity.onBackPressed();
            }
        } catch (Exception ex) {
//            activity.onBackPressed();
            ex.printStackTrace();
        }
    }

    public static void showAdMobInterstitial(final Intent intent, Activity activity) {
        try {
            Log.d("TAG", "showAdMobInterstitial: Intent");
            if (adMobInterstitial != null && canLoadInterstitial) {
                adMobInterstitial.show(activity);
                adMobInterstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        intentAfterInterstitial(intent, activity);
                        adMobInterstitial = null;
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                        intentAfterInterstitial(intent, activity);
                        adMobInterstitial = null;
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                    }
                });
                if (adMobInterstitial == null)
                    intentAfterInterstitial(intent, activity);
            } else {
                intentAfterInterstitial(intent, activity);
            }
        } catch (Exception ex) {
            intentAfterInterstitial(intent, activity);
            ex.printStackTrace();
        }
    }

    public static void intentAfterInterstitial(Intent intent, Activity activity) {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
        activity.startActivityForResult(intent, 1001);
    }

    public static void showIndertial(Activity activity, Intent intent) {
        showAdMobInterstitial(intent, activity);
    }
}
