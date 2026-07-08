package com.example.yeremi_warriors.Home.pertemuan_8

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.databinding.ActivityEmailGmailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EmailGmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmailGmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailGmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()

            when {
                email.isEmpty() -> showError("Email tidak boleh kosong")
                !email.endsWith("@gmail.com") -> showError("Email harus menggunakan domain @gmail.com")
                else -> {
                    val intent = Intent(this, RegistrationActivity::class.java)
                    intent.putExtra("EMAIL", email)
                    startActivity(intent)
                }
            }
        }
    }

    private fun showError(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Email Tidak Valid")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}