package com.example.yeremi_warriors.Home.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yeremi_warriors.databinding.FragmentKategoriBinding

class FragmentKategori : Fragment() {

    private var _binding: FragmentKategoriBinding? = null
    private val binding get() = _binding!!

    private val dataKategoriWisata = listOf(
        mapOf("title" to "Pantai Pasir Emas", "desc" to "Pantai berpasir putih di sisi barat desa"),
        mapOf("title" to "Air Terjun Sendang", "desc" to "Air terjun alami setinggi 20 meter"),
        mapOf("title" to "Bukit Panorama", "desc" to "Spot terbaik melihat matahari terbenam"),
        mapOf("title" to "Sanggar Tari Tradisional", "desc" to "Pertunjukan tari adat setiap akhir pekan"),
        mapOf("title" to "Pasar Kuliner Desa", "desc" to "Aneka makanan khas desa wisata")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SimpleAdapter(
            requireContext(),
            dataKategoriWisata,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listViewKategori.adapter = adapter

        binding.listViewKategori.setOnItemClickListener { _, _, position, _ ->
            val selected = dataKategoriWisata[position]
            Toast.makeText(
                requireContext(),
                "Kamu pilih: ${selected["title"]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}