package com.example.yeremi_warriors.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // AKTIFKAN TOOLBAR
        setSupportActionBar(binding.toolbarWelcome)
        supportActionBar?.apply {
            title = "Welcome Page"
            setDisplayHomeAsUpEnabled(true) // Tombol panah kembali
        }

        val username = intent.getStringExtra("USERNAME") ?: "Pengguna"
        binding.tvWelcomeUsername.text = "Halo, $username! 👋"

        binding.btnContinue.setOnClickListener {
            finish()
        }
    }

    // FUNGSI TOMBOL BACK TOOLBAR
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
