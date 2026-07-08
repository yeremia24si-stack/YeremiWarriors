package com.example.yeremi_warriors.Ulasan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.yeremi_warriors.databinding.ItemUlasanBinding
import com.google.android.material.snackbar.Snackbar

class UlasanAdapter(
    context: Context,
    private val ulasanList: List<UlasanModel>
) : ArrayAdapter<UlasanModel>(context, 0, ulasanList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemUlasanBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = ulasanList[position]

        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        binding.textNama.text = data.namaWisatawan
        binding.textKomentar.text = data.komentar

        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Ulasan dari ${data.namaWisatawan}: ${data.komentar}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}