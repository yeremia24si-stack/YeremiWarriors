package com.example.yeremi_warriors.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import com.example.yeremi_warriors.LoginActivity
import com.example.yeremi_warriors.databinding.FragmentHomeBinding
import com.example.yeremi_warriors.Home.pertemuan_3.ThirdActivity
import com.example.yeremi_warriors.Home.pertemuan_4.FourthActivity
import com.example.yeremi_warriors.Home.pertemuan_5.FifthActivity
import com.example.yeremi_warriors.Home.pertemuan_7.SixthActivity
import com.example.yeremi_warriors.Home.pertemuan_8.EmailGmailActivity
import com.example.yeremi_warriors.Home.pertemuan_9.NinthActivity
import com.example.yeremi_warriors.Home.pertemuan_10.TenthActivity
import com.example.yeremi_warriors.Home.pertemuan_11.TutorialMessageActivity
import com.example.yeremi_warriors.Home.pertemuan_13.ThirteenthActivity
import com.example.yeremi_warriors.data.api.CatFactApiClient
import com.example.yeremi_warriors.data.api.PhotoApiClient
import com.example.yeremi_warriors.Home.pertemuan_11.PhotoAdapter
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Home"

        val sharedPref =
            requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Pengguna")

        // Rumus Bangun Ruang
        binding.btnRumus.setOnClickListener {
            startActivity(Intent(requireContext(), CalculatorActivity::class.java))
        }

        // Welcome
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(requireContext(), WelcomeActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // Custom Two
        binding.btnCustom2.setOnClickListener {
            startActivity(Intent(requireContext(), CustomTwoActivity::class.java))
        }

        // WebView
        binding.btnWebView.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Pertemuan 3
        binding.btnPertemuan3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }

        // Pertemuan 4
        binding.btnPertemuan4.setOnClickListener {
            startActivity(Intent(requireContext(), FourthActivity::class.java))
        }

        // Pertemuan 5
        binding.btnPertemuan5.setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }

        // Pertemuan 6
        binding.btnPertemuan6.setOnClickListener {
            startActivity(Intent(requireContext(), SixthActivity::class.java))
        }

        // Pertemuan 8
        binding.btnPertemuan8.setOnClickListener {
            startActivity(Intent(requireContext(), EmailGmailActivity::class.java))
        }

        // Pertemuan 9
        binding.btnPertemuan9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }

        // Pertemuan 10
        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        // Pertemuan 11
        binding.btnPertemuan11.setOnClickListener {
            startActivity(Intent(requireContext(), TutorialMessageActivity::class.java))
        }

        loadCatFact()
        loadPhoto()

        binding.btnPertemuan13.setOnClickListener {
            startActivity(Intent(requireContext(), ThirteenthActivity::class.java))
        }

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        // Logout
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->
                val sharedPref =
                    requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)

                sharedPref.edit().clear().apply()

                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
                requireActivity().finish()
            }
            .setNegativeButton("Tidak") { _, _ ->
                Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun loadCatFact() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = response.fact
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil data."
            }
        }
    }

    private fun loadPhoto() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()

                val adapter = PhotoAdapter(photos)

                binding.rvGallery.layoutManager =
                    LinearLayoutManager(requireContext())

                binding.rvGallery.adapter = adapter

            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Gagal memuat galeri foto",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
