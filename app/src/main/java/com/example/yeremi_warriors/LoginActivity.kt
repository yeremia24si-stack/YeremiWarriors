package com.example.yeremi_warriors

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPasswordToggle()
        setupLoginButton()
    }

    private fun setupPasswordToggle() {
        binding.ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                binding.etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                binding.etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            binding.etPassword.setSelection(binding.etPassword.text.length)
        }
    }

    private fun setupLoginButton() {
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            when {
                username.isEmpty() -> {
                    binding.etUsername.error = "Username tidak boleh kosong"
                    binding.etUsername.requestFocus()
                }
                password.isEmpty() -> {
                    binding.etPassword.error = "Password tidak boleh kosong"
                    binding.etPassword.requestFocus()
                }
                password.length < 6 -> {
                    binding.etPassword.error = "Password minimal 6 karakter"
                    binding.etPassword.requestFocus()
                }
                else -> {
                    // Simpan isLogin ke SharedPreferences
                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    sharedPref.edit()
                        .putBoolean("isLogin", true)
                        .putString("username", username)
                        .apply()

                    // ✅ Ke BaseActivity (bukan MainActivity)
                    val intent = Intent(this, BaseActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }
    }
}
