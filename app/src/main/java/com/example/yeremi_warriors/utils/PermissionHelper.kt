package com.example.yeremi_warriors.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

/**
 * PermissionHelper - Global helper untuk mengecek & meminta permission
 * (dipakai untuk permission Notification dan Camera di aplikasi
 * Desa Wisata Nusantara)
 */
object PermissionHelper {

    fun hasPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(
        launcher: ActivityResultLauncher<String>,
        permission: String
    ) {
        launcher.launch(permission)
    }

    fun isNotificationPermissionRequired(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    }
}