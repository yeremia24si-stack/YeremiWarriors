package com.example.yeremi_warriors.Ulasan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yeremi_warriors.databinding.FragmentUlasanBinding

class FragmentUlasan : Fragment() {

    private var _binding: FragmentUlasanBinding? = null
    private val binding get() = _binding!!

    private val ulasanList = listOf(
        UlasanModel("Alya", "Desanya asri banget, cocok buat healing!", "https://avatar.iran.liara.run/public/1"),
        UlasanModel("Budi", "Kulinernya enak-enak, harga terjangkau", "https://avatar.iran.liara.run/public/2"),
        UlasanModel("Citra", "Pemandangan air terjunnya luar biasa indah", "https://avatar.iran.liara.run/public/3"),
        UlasanModel("Dika", "Warga desanya ramah dan sangat membantu", "https://avatar.iran.liara.run/public/4"),
        UlasanModel("Eka", "Wajib mampir ke bukit panorama saat sore", "https://avatar.iran.liara.run/public/5")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUlasanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UlasanAdapter(requireContext(), ulasanList)
        binding.listUlasanItem.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}