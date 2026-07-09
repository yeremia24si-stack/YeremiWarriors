package com.example.yeremi_warriors.Home.pertemuan_13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.example.yeremi_warriors.databinding.ActivityThirteenthBinding

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Dokumentasi Wisata"
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val adapter = ThirteenthTabsAdapter(this)
        binding.viewPager.adapter = adapter

        val tabTitles = listOf("Capture", "Scan QR", "Buat QR")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}