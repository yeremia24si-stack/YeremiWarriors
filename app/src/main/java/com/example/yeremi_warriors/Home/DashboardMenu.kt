package com.example.yeremi_warriors.Home

/**
 * Model untuk kartu menu layanan pada Dashboard Desa.
 *
 * @param iconRes resource ikon vector (mis. R.drawable.ic_ruler)
 * @param title judul menu, contoh: "Rumus Bangun Ruang"
 * @param iconBgColorRes warna background lingkaran ikon
 * @param iconTintColorRes warna ikon
 * @param targetActivity kelas Activity tujuan saat menu di-klik
 */
data class DashboardMenu(
    val iconRes: Int,
    val title: String,
    val iconBgColorRes: Int,
    val iconTintColorRes: Int,
    val targetActivity: Class<*>
)