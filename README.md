Verve Ad SDK Android
====================

Modifying AndroidManifest.xml
-----------------------------
Add the following permissions to your project:
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

The following permission is optional: 
```
<uses-permission android:name="android.permission.VIBRATE" />
```

Add the following elements to your project's Application tag:
``` 
    <activity
            android:name="com.verve.activities.AdViewActivity"
            android:configChanges="orientation|screenSize|keyboard"
            android:hardwareAccelerated="true"
            android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" />
     <activity
            android:name="com.verve.activities.ClickOutActivity"
            android:screenOrientation="locked"
            android:configChanges="orientation|screenSize|keyboard"
            android:hardwareAccelerated="true"
            verve:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" />
```

Dependencies
------------------------------
```
implementation 'com.google.android.gms:play-services-ads:'
```

Implementing the Verve Ad SDK
------------------------------
### Rewarded Video Ads
The class in which you would like to display rewarded video ads will need to implement the IVerveAPIListener interface. The methods the class will implement provide the Activity with information regarding the SDK's ad state.

#### Initialization
To request a rewarded video ad, you will need to create a `RewardedVideoAd` object:
```
// 'this' refers to class that is implementing IVerveAPIListener
InterstitialAd interstitialAd = new InterstitialAd(context, APP_ID, this)    
```
Your `APP_ID` will be provided to you during the Verve onboarding process.

#### Loading A Rewarded Video Ad
To request a rewarded video ad, call the load method below: 
```
// Zone is the identifier used to request a interstitial ad
interstitialAd.loadInterstitialAd(zone)
```

#### Loading A Rewarded Video Ad with ad mark up
To request a rewarded video ad, call the load method below: 
```
interstitialAd.loadInterstitialAd(zone, id, adm)
```

#### Showing An Ad
After receiving the `onAdReady(String zone)` callback, the SDK is ready to show the ad you have loaded. To show an ad, call the method below:
```
interstitialAd.showInterstitialAd(zone, context) 
```

### Interstitial Ads
The class in which you would like to display interstitial ads will need to implement the IVerveAPIListener interface. The methods the class will implement provide the Activity with information regarding the SDK's ad state.

#### Initialization
To request an interstitial ad, you will need to create an `InterstitialAd` object:
```
// 'this' refers to class that is implementing IVerveAPIListener
RewardedVideoAd rewardedVideoAd = new RewardedVideoAd(context, APP_ID, this)    
```
Your `APP_ID` will be provided to you during the Verve onboarding process.


#### Loading A Interstitial Ad
To request an interstitial ad, call the load method below: 
```
// zone is the identifier used to request a rewarded video ad
rewardedVideoAd.loadRewardedVideoAd(zone)
```


#### Loading A Interstitial Ad with ad mark up
To request an interstitial ad, call the load method below: 
```
rewardedVideoAd.loadRewardedVideoAd(zone, id, adm)
```


#### Showing An Ad
After receiving the `onAdReady(String zone)` callback, the SDK is ready to show the ad you have loaded. To show an ad, call the method below:
```
rewardedVideoAd.showRewardedVideoAd(zone, context)
```


### Banner Ads
The class in which you would like to display banner ads will need to implement the IVerveAPIListener interface. The methods the class will implement provide the Activity with information regarding the SDK's ad state.

### Banner Ad Sizes
The ad SDK allows for various sized banner ads to be addded to your Android application. Please see the table below for the different sizes available: 

| Size          | Constant           | Description          |
| ------------- |:------------------:| --------------------:|
| 320x50        | BANNER             | Standard banner      |
| 728x90        | TABLET_BANNER	     | IAB Leaderboard      | 
| 300x250	    | MEDIUM_RECTANGLE   | IAB Medium Rectangle |

### Option 1: Create your banner in XML

#### Create a BannerAdView in the XML Resource

Note: Be sure to include the Verve namespace, xmlns:verve="http://schemas.android.com/apk/lib/com.verve.api.adview.adview", in the XML.
``` 
<com.verve.api.adview.BannerAdView
		android:id="@+id/bannerAdView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        verve:app_id="APP_ID"
        verve:ad_keyword="ZONE"
        verve:ad_size="BANNER"/>
```

In the class that is displaying the XML retrieve the BannerAdView id:
```
BannerAdView bannerAdView = (BannerAdView) findViewById(R.id.bannerAdView)
```
Then set the iVerveAPIListener to bannerAdView object:
```
bannerAdView.setIVerveAPIListener(this)
```

When creating BannerAdView via XML, the view will automatically fetch an ad when created. To request an ad after the first initial load call:
```
\\ zone is the identifier used to request a banner ad
bannerAdView.loadBannerAd(zone)
```


#### Option 2: Create your banner programmatically
##### Initialization
To request a banner ad, you will need to create a `BannerAdView` object:
```
// 'this' refers to class that is implementing IVerveAPIListener
BannerAdView bannerAdView = new BannerAdView(context, APP_ID, AdSize, this)    
```
Your `APP_ID` will be provided to you during the Verve onboarding process.


##### Loading & Showing A Banner Ad
To request a banner ad call, the load method below: 
```
\\ zone is the identifier used to request a banner ad
bannerAdView.loadBannerAd(zone)
```

If the bannerAdView successfully loads an ad it will display itself in the view. The bannerAdView will also inform `onAdReady()` callback that ad is ready to displayed.

## IVerveAPIListener
The class that implements IVerveAPIListener will recieve callbacks based on what event occured for an ad.
``` 
    // Called when an ad is successfully loaded, and ready to be shown
    @Override
    public void onAdReady(String target) {
    }

    // Called when an ad fails to load or show
    @Override
    public void onAdFailed(String target) {
    }
    
    // Called when an ad is going to be shown
    @Override
    public void onAdShown(String target) {
    }
    
    // Called when a user clicks on a clickable portion of the ad view
    @Override
    public void onAdClicked(String target) {
    }
    
    // Called when an ad view is closed
    @Override
    public void onAdClosed(String target) {
    }

    // Called when a user has earned a reward for completing a Rewarded Video view
    @Override
    public void onAdRewarded(String target) {
    }

``` 
