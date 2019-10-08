package com.verve.demoapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.verve.ad.AdSize
import com.verve.api.InterstitialAd
import com.verve.api.RewardedVideoAd
import com.verve.api.adview.BannerAdView
import com.verve.listeners.IVerveAPIListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.crypto.spec.IvParameterSpec

class MainActivity : AppCompatActivity() {
    private lateinit var rewardedVideoAd: RewardedVideoAd
    private lateinit var interstitialAd: InterstitialAd
    private lateinit var bannerAdView: BannerAdView
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this;
        setContentView(R.layout.activity_main)
        onInitReward()
        onInitInterstitial()
        onInitBanner()
        initButtonListeners()
    }

    fun onInitReward() {
        val rewardListener : IVerveAPIListener =  object : IVerveAPIListener {
            override fun onAdReady(p0: String?) {
                Toast.makeText(context, "Rewarded Ad Ready", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailed(p0: String?) {
                Toast.makeText(context, "Rewarded Ad Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onAdShown(p0: String?) {

            }

            override fun onAdClicked(p0: String?) {

            }

            override fun onAdRewarded(p0: String?) {

            }

            override fun onAdClosed(p0: String?) {

            }

        }

        rewardedVideoAd = RewardedVideoAd(this, "6c8bcc4e765e46b1bf7743a00a364921", rewardListener)
    }

    fun onInitInterstitial() {
        val interstitialListener : IVerveAPIListener =  object : IVerveAPIListener {
            override fun onAdReady(p0: String?) {
                Toast.makeText(context, "Inter Ad Ready", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailed(p0: String?) {
                Toast.makeText(context, "Inter Ad Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onAdShown(p0: String?) {

            }

            override fun onAdClicked(p0: String?) {

            }

            override fun onAdRewarded(p0: String?) {

            }

            override fun onAdClosed(p0: String?) {

            }

        }

        interstitialAd = InterstitialAd(this, "6c8bcc4e765e46b1bf7743a00a364921", interstitialListener)
    }

    fun onInitBanner() {
        val bannerListenerProxy : IVerveAPIListener = object : IVerveAPIListener {
            override fun onAdReady(p0: String?) {
                Toast.makeText(context, "Banner Ad Ready", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailed(p0: String?) {
                runOnUiThread {
                    Toast.makeText(context, "Banner Ad Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onAdShown(p0: String?) {

            }

            override fun onAdClicked(p0: String?) {

            }

            override fun onAdRewarded(p0: String?) {

            }

            override fun onAdClosed(p0: String?) {

            }
        }

        bannerAdView = BannerAdView(this, "6c8bcc4e765e46b1bf7743a00a364921", AdSize.BANNER, bannerListenerProxy)
    }

    fun initButtonListeners() {
        loadReward.setOnClickListener {
            rewardedVideoAd.loadRewardedVideoAd("reward")
        }

        showReward.setOnClickListener {
            rewardedVideoAd.showRewardedVideoAd("reward", this)
        }

        loadInter.setOnClickListener {
            interstitialAd.loadInterstitialAd("rally")
        }

        showInter.setOnClickListener {
            interstitialAd.showInterstitialAd("rally", this)
        }

        loadBanner.setOnClickListener {
            bannerAdView.loadBannerAd("")
        }
    }
}
