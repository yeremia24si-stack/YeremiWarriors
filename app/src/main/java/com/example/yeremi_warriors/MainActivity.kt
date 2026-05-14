package com.example.yeremi_warriors

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.BreakApps.Home.CalculatorActivity
import com.example.yeremi_warriors.BreakApps.Home.CustomTwoActivity
import com.example.yeremi_warriors.BreakApps.Home.WebViewActivity
import com.example.yeremi_warriors.BreakApps.Home.WelcomeActivity
import com.example.yeremi_warriors.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Cek SharedPreferences — kalau belum login, redirect ke Login
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)
        if (!isLogin) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = sharedPref.getString("username", "Pengguna")
        val titleValue = binding.tvTitleMain.text.toString()
        val descValue = binding.tvDescMain.text.toString()

        // Tombol 1: Ke Kalkulator
        binding.btnRumus.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            intent.putExtra("EXTRA_TITLE", titleValue)
            intent.putExtra("EXTRA_DESC", descValue)
            startActivity(intent)
        }

        // Tombol 2: Ke Welcome
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra("EXTRA_TITLE", titleValue)
            intent.putExtra("EXTRA_DESC", descValue)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // Tombol 3: Ke Custom Two (ada WebView di dalamnya)
        binding.btnCustom2.setOnClickListener {
            val intent = Intent(this, CustomTwoActivity::class.java)
            intent.putExtra("EXTRA_TITLE", titleValue)
            intent.putExtra("EXTRA_DESC", descValue)
            startActivity(intent)
        }

        // Tombol: Bina Desa (WebView)
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            // Kalau lo mau kirim data title/desc juga seperti tombol lain, tambahin ini:
            intent.putExtra("EXTRA_TITLE", titleValue)
            intent.putExtra("EXTRA_DESC", descValue)
            startActivity(intent)
        }


        // Tombol 4: Logout
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->
                // ✅ Hapus SharedPreferences saat logout
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                sharedPref.edit().clear().apply()

                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            .setNegativeButton("Tidak") { _, _ ->
                Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
            }
            .show()
    }
}
