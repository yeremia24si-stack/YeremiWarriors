package com.example.yeremi_warriors.Home.pertemuan_3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.yeremi_warriors.databinding.ActivityThirdBinding
import com.example.yeremi_warriors.utils.PermissionHelper
import com.example.yeremi_warriors.utils.ReminderHelper
import java.util.Calendar

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Meminta izin notifikasi (Android 13+)
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS

            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        binding.btnKirim.setOnClickListener {

            val noTujuan = binding.inputNoTujuan.text.toString()

            // Reminder muncul 1 menit dari sekarang
            val calendar = Calendar.getInstance().apply {
                add(Calendar.MINUTE, 1)
            }

            ReminderHelper.setReminder(
                context = this,
                hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE),
                title = "Reservasi Wisata Desa",
                message = "Halo $noTujuan, reservasi kunjungan Desa Wisata Anda sedang diproses",
                targetActivity = ThirdResultActivity::class.java
            )

            Toast.makeText(
                this,
                "Silahkan tunggu 1 menit untuk menerima notifikasi reservasi...",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}