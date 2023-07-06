package com.ocean.postermaker.AdManager;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.ocean.postermaker.R;

public class NativeBanner {
    public static void loadNativeBannerAds(Activity activity, LinearLayout adLoaded) {
        adLoaded.setVisibility(View.VISIBLE);
        ShowAdMobNativeBanner(activity, adLoaded, "ca-app-pub-3940256099942544/2247696110");
    }

    public static void ShowAdMobNativeBanner(Activity activity, LinearLayout adLoaded, String adId) {
        AdLoader.Builder builder = new AdLoader.Builder(activity, adId);
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                final ViewGroup viewGroup = null;
                com.google.android.gms.ads.nativead.NativeAdView adView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.ads_native_admob, viewGroup);
                inflateAdMobNative(adView, nativeAd);
                adLoaded.removeAllViews();
                adLoaded.addView(adView);
            }
        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(true)
                .build();

        com.google.android.gms.ads.nativead.NativeAdOptions adOptions = new com.google.android.gms.ads.nativead.NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                adLoaded.removeAllViews();
                super.onAdFailedToLoad(loadAdError);
            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }

    public static void inflateAdMobNative(com.google.android.gms.ads.nativead.NativeAdView adView, com.google.android.gms.ads.nativead.NativeAd nativeAd) {
        try {
            // Set the media viewTop. Media content will be automatically populated in the media viewTop once
            // adView.setNativeAd() is called.
            MediaView mediaView = adView.findViewById(R.id.ad_media);
            // mediaView.getLayoutParams().width = ADHelper.screenWidth * 7 / 3;
            // mediaView.getLayoutParams().height = ADHelper.screenHeight * 3 / 10;
            adView.setMediaView(mediaView);

            // Set other ad assets.
            TextView ad_headline = adView.findViewById(R.id.ad_headline);
            adView.setHeadlineView(ad_headline);

            TextView ad_body = adView.findViewById(R.id.ad_body);
            adView.setBodyView(ad_body);

            Button ad_call_to_action = adView.findViewById(R.id.ad_call_to_action);
            adView.setCallToActionView(ad_call_to_action);

            adView.setIconView(adView.findViewById(R.id.ad_app_icon));

            TextView ad_price = adView.findViewById(R.id.ad_price);
            adView.setPriceView(ad_price);
            ad_price.setVisibility(View.GONE);

            adView.setStarRatingView(adView.findViewById(R.id.ad_stars));

            TextView ad_store = adView.findViewById(R.id.ad_store);
            adView.setStoreView(ad_store);
            ad_store.setVisibility(View.GONE);

            TextView ad_advertiser = adView.findViewById(R.id.ad_advertiser);
            adView.setAdvertiserView(ad_advertiser);

            // The headline is guaranteed to be in every UnifiedNativeAd.
            ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

            // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
            // check before trying to display them.
            if (nativeAd.getBody() == null) {
                adView.getBodyView().setVisibility(View.GONE);
            } else {
                adView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
            }

            if (nativeAd.getCallToAction() == null) {
                adView.getCallToActionView().setVisibility(View.GONE);
            } else {
                adView.getCallToActionView().setVisibility(View.VISIBLE);
                ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
            }

            if (nativeAd.getIcon() == null) {
                adView.getIconView().setVisibility(View.GONE);
            } else {
                ((ImageView) adView.getIconView()).setImageDrawable(
                        nativeAd.getIcon().getDrawable());
                adView.getIconView().setVisibility(View.VISIBLE);
            }

            if (nativeAd.getPrice() == null) {
                adView.getPriceView().setVisibility(View.GONE);
            } else {
                adView.getPriceView().setVisibility(View.VISIBLE);
                ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
            }

            if (nativeAd.getStore() == null) {
                adView.getStoreView().setVisibility(View.GONE);
            } else {
                adView.getStoreView().setVisibility(View.VISIBLE);
                ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
            }

            if (nativeAd.getStarRating() == null) {
                adView.getStarRatingView().setVisibility(View.GONE);
            } else {
                ((RatingBar) adView.getStarRatingView())
                        .setRating(nativeAd.getStarRating().floatValue());
                adView.getStarRatingView().setVisibility(View.VISIBLE);
            }

            if (nativeAd.getAdvertiser() == null) {
                adView.getAdvertiserView().setVisibility(View.GONE);
            } else {
                ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
                adView.getAdvertiserView().setVisibility(View.VISIBLE);
            }

            // This method tells the Google Mobile Ads SDK that you have finished populating your
            // native ad viewTop with this native ad. The SDK will populate the adView's MediaView
            // with the media content from this native ad.
            adView.setNativeAd(nativeAd);

            MediaContent mediaContent = nativeAd.getMediaContent();
            VideoController vc = mediaContent.getVideoController();
            if (vc.hasVideoContent()) {


                vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    @Override
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
