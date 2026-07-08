package com.example.yeremi_warriors.Home.pertemuan_8

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.databinding.ActivityRegistrationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Email otomatis terisi dari Gmail dan tidak boleh diubah
        val email = intent.getStringExtra("EMAIL") ?: ""
        binding.etEmail.setText(email)

        binding.btnRegister.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            when {
                nama.isEmpty() || username.isEmpty() || password.isEmpty() ->
                    showError("Semua field wajib diisi")
                password.length < 6 ->
                    showError("Password minimal 6 karakter")
                username.contains(" ") ->
                    showError("Username tidak boleh mengandung spasi")
                else -> {
                    saveToSharedPreferences(nama, email, username, password)
                    startActivity(Intent(this, RegistrationSuccessActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun saveToSharedPreferences(nama: String, email: String, username: String, password: String) {
        val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        sharedPref.edit().apply {
            putString("nama", nama)
            putString("email", email)
            putString("username", username)
            putString("password", password)
            apply()
        }
    }

    private fun showError(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Registrasi Gagal")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}