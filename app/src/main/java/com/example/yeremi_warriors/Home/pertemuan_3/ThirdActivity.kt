package com.example.yeremi_warriors.Home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            val noTujuan = binding.inputNoTujuan.text

            // Pindah ke ThirdResultActivity menggunakan Intent
            val intent = Intent(this, ThirdResultActivity::class.java)
            startActivity(intent)
        }
    }
}