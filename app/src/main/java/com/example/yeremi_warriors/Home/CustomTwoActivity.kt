package com.example.yeremi_warriors.Home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.databinding.ActivityCustomTwoBinding

class CustomTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Informasi Desa"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Terima data dari MainActivity
        val title = intent.getStringExtra("EXTRA_TITLE")
        val desc = intent.getStringExtra("EXTRA_DESC")
        binding.tvDataAsal.text = "Dari: $title\nInfo: $desc"

        // ✅ Tombol buka WebView Bina Desa
        binding.btnOpenWeb.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
