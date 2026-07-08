package com.example.yeremi_warriors

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.databinding.ActivityLoginBinding
import com.example.yeremi_warriors.Home.pertemuan_8.EmailGmailActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPasswordToggle()
        setupLoginButton()

        // Tombol Register Gmail
        binding.btnRegisterGmail.setOnClickListener {
            val intent = Intent(this, EmailGmailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupPasswordToggle() {
        binding.ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                binding.etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or
                            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                binding.etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or
                            InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            binding.etPassword.setSelection(binding.etPassword.text.length)
        }
    }

    private fun setupLoginButton() {
        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty()) {
                binding.etUsername.error = "Username tidak boleh kosong"
                binding.etUsername.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password tidak boleh kosong"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.etPassword.error = "Password minimal 6 karakter"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            // SharedPreferences untuk login
            val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)

            val savedUsername = sharedPref.getString("username", null)
            val savedPassword = sharedPref.getString("password", null)

            // Login developer (username = password)
            val isDevBypass =
                username == password && username.isNotEmpty()

            // Login dari akun yang sudah tersimpan
            val isMatchSaved =
                username == savedUsername &&
                        password == savedPassword

            if (isDevBypass || isMatchSaved) {

                // Simpan status login
                getSharedPreferences("user_pref", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isLogin", true)
                    .putString("username", username)
                    .apply()

                val intent = Intent(this, BaseActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
                finish()

            } else {

                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password salah")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}