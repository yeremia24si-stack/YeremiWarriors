package com.example.yeremi_warriors.Home

/**
 * Model untuk list materi pertemuan pada Dashboard Desa.
 *
 * @param title contoh: "Pertemuan 13"
 * @param targetActivity kelas Activity tujuan saat item di-klik
 * @param isHighlighted true jika ingin ditandai (mis. materi terbaru)
 */
data class PertemuanItem(
    val title: String,
    val targetActivity: Class<*>,
    val isHighlighted: Boolean = false
)