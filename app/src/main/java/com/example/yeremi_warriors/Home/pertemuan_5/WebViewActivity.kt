package com.example.yeremi_warriors.Home.pertemuan_5

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.R
import com.example.yeremi_warriors.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ── Setup Toolbar ──
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Wisata Desa Online"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // ── Konfigurasi WebView ──
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        // Load website pariwisata desa / website wisata Indonesia
        binding.webView.loadUrl("https://yeremi-global.alwaysdata.net/login")

        // ── Toolbar hide/show saat scroll web ──
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true) // sembunyikan toolbar
            } else if (scrollY < oldScrollY) {
                binding.appBar.setExpanded(true, true)  // tampilkan toolbar
            }
        }
    }

    // ── Tombol back: navigasi web atau keluar activity ──
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack() // Kembali ke halaman web sebelumnya
        } else {
            super.onBackPressed()    // Keluar activity sebelumnya
        }
    }

    // ── Handle back dari Toolbar ──
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}