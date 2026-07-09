package com.example.yeremi_warriors

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.yeremi_warriors.About.AboutFragment
import com.example.yeremi_warriors.Home.HomeFragment
import com.example.yeremi_warriors.Profile.ProfileFragment
import com.example.yeremi_warriors.Note.NoteFragment
import com.example.yeremi_warriors.R
import com.example.yeremi_warriors.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cek Login
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (!isLogin) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fragment pertama
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.bottomNavView.selectedItemId = R.id.menu_home
        }

        // Bottom Navigation
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

                R.id.note -> {
                    replaceFragment(NoteFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}