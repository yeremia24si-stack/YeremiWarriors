package com.example.yeremi_warriors.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yeremi_warriors.LoginActivity
import com.example.yeremi_warriors.R
import com.example.yeremi_warriors.data.api.CatFactApiClient
import com.example.yeremi_warriors.data.api.PhotoApiClient
import com.example.yeremi_warriors.databinding.FragmentHomeBinding
import com.example.yeremi_warriors.Home.pertemuan_3.ThirdActivity
import com.example.yeremi_warriors.Home.pertemuan_4.FourthActivity
import com.example.yeremi_warriors.Home.pertemuan_5.FifthActivity
import com.example.yeremi_warriors.Home.pertemuan_7.SixthActivity
import com.example.yeremi_warriors.Home.pertemuan_8.EmailGmailActivity
import com.example.yeremi_warriors.Home.pertemuan_9.NinthActivity
import com.example.yeremi_warriors.Home.pertemuan_10.TenthActivity
import com.example.yeremi_warriors.Home.pertemuan_11.TutorialMessageActivity
import com.example.yeremi_warriors.Home.pertemuan_11.PhotoAdapter
import com.example.yeremi_warriors.Home.pertemuan_13.ThirteenthActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

/**
 * NB: CalculatorActivity, WelcomeActivity, CustomTwoActivity, WebViewActivity
 * tidak perlu di-import karena satu package dengan HomeFragment
 * (com.example.yeremi_warriors.Home).
 */
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

        // Setup Toolbar (tetap sama seperti versi asli)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Home"

        setupMenuLayanan()
        setupMateriPertemuan()

        loadCatFact()
        loadPhoto()

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun setupMenuLayanan() {
        val sharedPref =
            requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Pengguna")

        val menuList = listOf(
            DashboardMenu(
                iconRes = R.drawable.ic_notes,
                title = "Rumus Bangun Datar",
                iconBgColorRes = R.color.icon_bg_teal,
                iconTintColorRes = R.color.icon_teal,
                targetActivity = CalculatorActivity::class.java
            ),
            DashboardMenu(
                iconRes = R.drawable.ic_home,
                title = "Halaman Welcome",
                iconBgColorRes = R.color.icon_bg_purple,
                iconTintColorRes = R.color.icon_purple,
                targetActivity = WelcomeActivity::class.java
            )
        )

        binding.rvMenu.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMenu.adapter = DashboardMenuAdapter(menuList) { item ->
            val intent = Intent(requireContext(), item.targetActivity)
            // Halaman Welcome butuh extra USERNAME, sama seperti versi asli
            if (item.targetActivity == WelcomeActivity::class.java) {
                intent.putExtra("USERNAME", username)
            }
            startActivity(intent)
        }
    }

    private fun setupMateriPertemuan() {
        val pertemuanList = listOf(
            PertemuanItem("Pertemuan 3", ThirdActivity::class.java),
            PertemuanItem("Pertemuan 4", FourthActivity::class.java),
            PertemuanItem("Pertemuan 5", FifthActivity::class.java),
            PertemuanItem("Pertemuan 6", SixthActivity::class.java),
            PertemuanItem("Pertemuan 8", EmailGmailActivity::class.java),
            PertemuanItem("Pertemuan 9", NinthActivity::class.java),
            PertemuanItem("Pertemuan 10", TenthActivity::class.java),
            PertemuanItem("Pertemuan 11", TutorialMessageActivity::class.java),
            PertemuanItem("Pertemuan 13", ThirteenthActivity::class.java, isHighlighted = true)
        )

        binding.rvPertemuan.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPertemuan.adapter = PertemuanAdapter(pertemuanList) { item ->
            startActivity(Intent(requireContext(), item.targetActivity))
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

                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())
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