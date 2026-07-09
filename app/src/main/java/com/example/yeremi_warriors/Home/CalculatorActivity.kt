package com.example.yeremi_warriors.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.R
import com.example.yeremi_warriors.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbarCalculator)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarCalculator.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Ambil data dari Intent (Bonus dari MainActivity)
        val title = intent.getStringExtra("EXTRA_TITLE")
        binding.tvHeaderInfo.text = title ?: "Rumus Bangun Datar"

        // Logic Hitung Persegi
        binding.btnHitungPersegi.setOnClickListener {
            val sisiStr = binding.etSisi.text.toString()
            if (sisiStr.isEmpty()) {
                binding.etSisi.error = getString(R.string.error_empty)
                return@setOnClickListener
            }

            val sisi = sisiStr.toDoubleOrNull()
            if (sisi == null) {
                binding.tvHasilPersegi.text = getString(R.string.invalid_input)
            } else if (sisi <= 0) {
                binding.etSisi.error = getString(R.string.error_zero)
            } else {
                val hasil = sisi * sisi
                binding.tvHasilPersegi.text = getString(R.string.result_label, hasil.toString())
            }
        }

        // Logic Hitung Persegi Panjang
        binding.btnHitungPersegiPanjang.setOnClickListener {
            val pStr = binding.etPanjang.text.toString()
            val lStr = binding.etLebar.text.toString()

            var isValid = true
            if (pStr.isEmpty()) {
                binding.etPanjang.error = getString(R.string.error_empty)
                isValid = false
            }
            if (lStr.isEmpty()) {
                binding.etLebar.error = getString(R.string.error_empty)
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            val p = pStr.toDoubleOrNull()
            val l = lStr.toDoubleOrNull()

            if (p == null || l == null) {
                binding.tvHasilPersegiPanjang.text = getString(R.string.invalid_input)
            } else if (p <= 0 || l <= 0) {
                if (p <= 0) binding.etPanjang.error = getString(R.string.error_zero)
                if (l <= 0) binding.etLebar.error = getString(R.string.error_zero)
            } else {
                val hasil = p * l
                binding.tvHasilPersegiPanjang.text = getString(R.string.result_label, hasil.toString())
            }
        }

        // Logic Hitung Segitiga
        binding.btnHitungSegitiga.setOnClickListener {
            val alasStr = binding.etAlas.text.toString()
            val tinggiStr = binding.etTinggi.text.toString()

            var isValid = true
            if (alasStr.isEmpty()) {
                binding.etAlas.error = getString(R.string.error_empty)
                isValid = false
            }
            if (tinggiStr.isEmpty()) {
                binding.etTinggi.error = getString(R.string.error_empty)
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            val alas = alasStr.toDoubleOrNull()
            val tinggi = tinggiStr.toDoubleOrNull()

            if (alas == null || tinggi == null) {
                binding.tvHasilSegitiga.text = getString(R.string.invalid_input)
            } else if (alas <= 0 || tinggi <= 0) {
                if (alas <= 0) binding.etAlas.error = getString(R.string.error_zero)
                if (tinggi <= 0) binding.etTinggi.error = getString(R.string.error_zero)
            } else {
                val hasil = 0.5 * alas * tinggi
                binding.tvHasilSegitiga.text = getString(R.string.result_label, hasil.toString())
            }
        }
    }
}
