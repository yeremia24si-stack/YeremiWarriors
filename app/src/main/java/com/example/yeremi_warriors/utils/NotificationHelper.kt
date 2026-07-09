package com.example.yeremi_warriors.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.yeremi_warriors.R

/**
 * NotificationHelper - Global helper untuk menampilkan Local Notification
 * pada aplikasi Desa Wisata Nusantara. Bisa dipanggil dari Activity
 * maupun Fragment (gunakan requireContext() jika dipanggil di Fragment).
 */
object NotificationHelper {

    private const val CHANNEL_ID = "desa_wisata_channel"

    fun showNotification(
        context: Context,
        title: String,
        message: String,
        intent: Intent
    ) {
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Desa Wisata Nusantara",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        val pending = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(pending)
            .build()

        manager.notify(System.currentTimeMillis().toInt(), notification)
    }
}