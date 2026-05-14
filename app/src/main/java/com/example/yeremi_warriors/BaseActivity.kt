package com.example.yeremi_warriors

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.yeremi_warriors.BreakApps.About.AboutFragment
import com.example.yeremi_warriors.BreakApps.Home.HomeFragment
import com.example.yeremi_warriors.BreakApps.Profile.ProfileFragment
import com.example.yeremi_warriors.R
import com.example.yeremi_warriors.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Cek Login
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)
        if (!isLogin) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Tampilkan HomeFragment saat pertama kali buka
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.bottomNavView.selectedItemId = R.id.menu_home // Paksa highlight ke menu home
        }

        // 3. Listener Klik Menu
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.menu_about -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.menu_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}
