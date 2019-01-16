Verve Ad SDK Android
====================

Modifying AndroidManifest.xml
-----------------------------
Add the following permissions:
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

The following permission is optional: 
```
<uses-permission android:name="android.permission.VIBRATE" />
```

Add the following elements within your project's Application tag:
``` 
    <activity
            android:name="com.verve.adview.AdViewActivity"
            android:configChanges="orientation|screenSize|keyboard"
            android:hardwareAccelerated="true"
            android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" />
     <activity
            android:name="com.verve.adview.ClickOutActivity"
            android:screenOrientation="locked"
            android:configChanges="orientation|screenSize|keyboard"
            android:hardwareAccelerated="true"
            verve:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" />
```


Implementing the Verve Ad SDK
------------------------------
### Rewarded Video Ads
The class in which you would like to display rewarded video ads will need to implement the IVerveAPIListener interface. The methods that the class will implement provides the Activity with information regarding the SDK's ad state.

#### Initialization
To request a rewarded video ad, you will need to create an of `RewardedVideoAd` object:
```
RewardedVideoAd rewardedVideoAd = new RewardedVideoAd(context, APP_ID, this)    
// 'this' refers to class that is implementing IVerveAPIListener
```
Your APP_ID will be provided to you during the Verve onboarding process.

#### Loading A Rewarded Video Ad
To reuqest a rewarded video ad call the load method below: 
```
rewardedVideoAd.loadRewardedVideoAd(zone)
\\ zone is the identifier used to request a rewarded video ad
```

#### Loading A Rewarded Video Ad with ad mark up
To reuqest a rewarded video ad call the load method below: 
```
rewardedVideoAd.loadRewardedVideoAd(zone, id, adm)
\\ zone is the string identifier used to request a rewarded video ad
\\ id is the string used to represent the bid id
\\ adm is the string used to pass the ad markup
```

#### Showing An Ad
After receiving the `onAdReady(String zone)` callback, the SDK is ready to show the ad that you have loaded. To show an ad call the method below:
```
rewardedVideoAd.showRewardedVideoAd(zone, context)
```

### Interstitial Ads
The class in which you would like to display interstitial ads will need to implement the IVerveAPIListener interface. The methods that the class will implement provides the Activity with information regarding the SDK's ad state.

#### Initialization
To request a interstitial ad, you will need to create an of `InterstitialAd` object:
```
InterstitialAd interstitialAd = new InterstitialAd(context, APP_ID, this)    
// 'this' refers to class that is implementing IVerveAPIListener
```
Your APP_ID will be provided to you during the Verve onboarding process.

#### Loading A Interstitial Ad
To reuqest a interstitial ad call the load method below: 
```
interstitialAd.loadInterstitialAd(zone) \\ zone is the identifier used to request a interstitial ad
```

#### Loading A Interstitial Ad with ad mark up
To reuqest a interstitial ad call the load method below: 
```
interstitialAd.loadInterstitialAd(zone, id, adm)
\\ zone is the string identifier used to request a interstitial ad
\\ id is the string used to represent the bid id
\\ adm is the string used to pass the ad markup
```

#### Showing An Ad
After receiving the `onAdReady(String zone)` callback, the SDK is ready to show the ad that you have loaded. To show an ad call the method below:
```
interstitialAd.showInterstitialAd(zone, context) 
```

### Banner Ads
The class in which you would like to display banner ads will need to implement the IVerveAPIListener interface. The methods that the class will implement provides the Activity with information regarding the SDK's ad state.

#### Banner Ad Sizes
The ad SDK allows for various sized banner ads to be addded to your Android application. Please see the table below for the different sizes available: 

| Size          | Constant           | Description          |
| ------------- |:------------------:| --------------------:|
| 320x50        | BANNER             | Standard banner      |
| 728x90        | TABLET_BANNER	     | IAB Leaderboard      | 
| 300x250	    | MEDIUM_RECTANGLE   | IAB Medium Rectangle |

#### Option 1: Create your banner in XML
##### Create a BannerAdView in the XML Resource

Note: Be sure to include the Verve namespace, xmlns:verve="http://schemas.android.com/apk/lib/com.verve.adview", in the XML.
``` 
<com.verve.adview.BannerAdView
		android:id="@+id/bannerAdView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        verve:app_id="APP_ID"
        verve:ad_keyword="ZONE"
        verve:ad_size="BANNER"/>
```

In the class that is displaying the XML retrieve the BannerAdView id :
```
BannerAdView bannerAdView = (BannerAdView) findViewById(R.id.bannerAdView)
```
Afterwards, set the iVerveAPIListener to bannerAdView object:
```
bannerAdView.setiVerveAPIListener(this)
```

When creating BannerAdView via XML, the view will automatically fetch an ad when created. To call request an ad after the first initial load call:
```
bannerAdView.loadBannerAd(zone)
\\ zone is the identifier used to request a banner ad
```

#### Option 2: Create your banner programmatically
##### Initialization
To request a banner ad, you will need to create an of `BannerAdView` object:
```
BannerAdView bannerAdView = new BannerAdView(context, APP_ID, AdSize, this)    
// 'this' refers to class that is implementing IVerveAPIListener
```
Your APP_ID will be provided to you during the Verve onboarding process.

##### Loading & Showing A Banner Ad
To request a banner ad call the load method below: 
```
bannerAdView.loadBannerAd(zone)
\\ zone is the identifier used to request a banner ad
```

If the bannerAdView successfully loads an ad it will display itself in the view. The bannerAdView will also inform `onAdReady()` callback that ad is ready to displayed.

#### IVerveAPIListener
The class that implements IVerveAPIListener will recieve callbacks based on what event occured for an ad.
``` @Override
    public void onAdReady(String target) {
    }

    @Override
    public void onAdFailed(String target) {
    }

    @Override
    public void onAdShown(String target) {
    }

    @Override
    public void onAdClicked(String target) {
    }

    @Override
    public void onAdClosed(String target) {
    }

    @Override
    public void onAdRewarded(String target) {
    } 
``` 