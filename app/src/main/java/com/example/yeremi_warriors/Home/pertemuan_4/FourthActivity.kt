package com.example.yeremi_warriors.Home.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.MainActivity
import com.example.yeremi_warriors.databinding.ActivityFourthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LifeCycle Log
        Log.e("onCreate", "FourthActivity dibuat pertama kali")

        // ── Ambil data dari Intent (dikirim dari MainActivity) ──
        val name = intent.getStringExtra("name")
        val from = intent.getStringExtra("from")
        val age  = intent.getIntExtra("age", 0)
        Log.e("Data Intent", "Nama: $name , Usia: $age, Asal: $from")

        // Tampilkan data di UI
        binding.tvNamaDesa.text   = name ?: "Desa Wisata Nusantara"
        binding.tvAsalDesa.text   = "Lokasi: ${from ?: "-"}"
        binding.tvRatingDesa.text = "Tahun Berdiri: $age"

        // ── Tombol Kembali ke MainActivity ──
        binding.btnKembali.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // hapus FourthActivity dari stack
        }

        // ── Snackbar ──
        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Selamat datang di Desa Wisata Nusantara! 🌿",
                Snackbar.LENGTH_SHORT
            )
                .setAction("Tutup") {
                    Log.e("Info Snackbar", "Snackbar ditutup")
                }
                .show()
        }

        // ── AlertDialog ──
        binding.btnShowAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Kunjungan")
                .setMessage("Apakah Anda yakin ingin mendaftarkan kunjungan ke desa wisata ini?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Ya!")
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Tidak!")
                }
                .show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: FourthActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "FourthActivity dihapus dari stack")
    }
}