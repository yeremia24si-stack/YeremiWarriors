package com.example.yeremi_warriors.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.yeremi_warriors.BaseActivity

/**
 * ReminderReceiver - BroadcastReceiver yang berfungsi sebagai penerima
 * sinyal ketika waktu pengingat (reminder) telah tercapai.
 *
 * Class ini bertugas mengeksekusi aksi lanjutan, yaitu menampilkan
 * notifikasi kepada pengguna melalui NotificationHelper, dan mengarahkan
 * pengguna ke halaman tertentu melalui Intent.
 *
 * ReminderReceiver bekerja di background dan akan tetap berjalan
 * meskipun aplikasi Desa Wisata Nusantara berada dalam kondisi tertutup.
 */
class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val title = intent.getStringExtra("title") ?: "Pengingat"
        val message = intent.getStringExtra("message") ?: "Waktunya melakukan sesuatu"
        val targetClassName = intent.getStringExtra("target_activity")

        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            val clazz = Class.forName(targetClassName)
            Intent(context, clazz).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        } else {
            Intent(context, BaseActivity::class.java)
        }

        NotificationHelper.showNotification(
            context = context,
            title = title,
            message = message,
            intent = targetIntent
        )
    }
}