package com.example.yeremi_warriors.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        binding.tvHeaderInfo.text = title ?: "Kalkulator Geometri"

        // Logic Hitung Segitiga
        binding.btnHitungSegitiga.setOnClickListener {
            val alas = binding.etAlas.text.toString().toDoubleOrNull()
            val tinggi = binding.etTinggi.text.toString().toDoubleOrNull()

            if (alas != null && tinggi != null) {
                val hasil = 0.5 * alas * tinggi
                binding.tvHasilSegitiga.text = "Hasil: $hasil"
            } else {
                binding.tvHasilSegitiga.text = "Input tidak valid"
            }
        }

        // Logic Hitung Balok
        binding.btnHitungBalok.setOnClickListener {
            val p = binding.etPanjang.text.toString().toDoubleOrNull()
            val l = binding.etLebar.text.toString().toDoubleOrNull()
            val t = binding.etTinggiBalok.text.toString().toDoubleOrNull()

            if (p != null && l != null && t != null) {
                val hasil = p * l * t
                binding.tvHasilBalok.text = "Hasil: $hasil"
            } else {
                binding.tvHasilBalok.text = "Input tidak valid"
            }
        }
    }
}
