package com.caneryildirim.harypottermemorycard.view


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.caneryildirim.harypottermemorycard.databinding.ActivityMain2Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

    }



}