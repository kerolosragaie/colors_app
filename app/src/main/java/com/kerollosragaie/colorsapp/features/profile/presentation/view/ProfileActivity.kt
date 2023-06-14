package com.kerollosragaie.colorsapp.features.profile.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kerollosragaie.colorsapp.core.models.user.User
import com.kerollosragaie.colorsapp.databinding.ActivityProfileBinding
import com.kerollosragaie.colorsapp.features.profile.presentation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(
            layoutInflater
        )
    }
    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProvider(this@ProfileActivity)[ProfileViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        lifecycleScope.launch {
            profileViewModel.currentUser.collect { user ->
                setUpUserInfo(user)
            }
        }
        setupRvAlbums()
    }

    private fun setUpUserInfo(user: User?) {
        binding.tvUsername.text = user?.name
        binding.tvAddress.text =
            "${user?.address?.street}, ${user?.address?.suite}, ${user?.address?.city},"
        binding.tvZipcode.text = user?.address?.zipcode
    }

    private fun setupRvAlbums() {
        val albumRVAdapter = AlbumRVAdapter()
        binding.rvAlbums.apply {
            adapter = albumRVAdapter
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@ProfileActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)
        }
        lifecycleScope.launch {
            profileViewModel.albumsList.collect { albumsList ->
                albumRVAdapter.submitList(albumsList)
            }
        }
    }
}

