package com.example.yeremi_warriors.Home.pertemuan_9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.btnCari.setOnClickListener {
            val keyword = binding.etCari.text.toString()
            if (keyword.isNotEmpty()) {
                Toast.makeText(this, "Mencari wisata: $keyword", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Masukkan nama destinasi dulu ya", Toast.LENGTH_SHORT).show()
            }
        }

        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(this, "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        val kategoriClick: (String) -> Unit = { kategori ->
            Toast.makeText(this, "Menampilkan wisata kategori: $kategori", Toast.LENGTH_SHORT).show()
        }
        binding.btnPantai.setOnClickListener { kategoriClick("Pantai") }
        binding.btnGunung.setOnClickListener { kategoriClick("Gunung") }
        binding.btnBudaya.setOnClickListener { kategoriClick("Budaya") }
        binding.btnKuliner.setOnClickListener { kategoriClick("Kuliner") }
        binding.btnPenginapan.setOnClickListener { kategoriClick("Penginapan") }
        binding.btnOleh2.setOnClickListener { kategoriClick("Oleh-Oleh") }
    }
}