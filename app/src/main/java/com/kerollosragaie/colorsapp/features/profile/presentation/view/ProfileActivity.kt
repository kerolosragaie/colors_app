package com.kerollosragaie.colorsapp.features.profile.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kerollosragaie.colorsapp.databinding.ActivityProfileBinding
import com.kerollosragaie.colorsapp.features.profile.presentation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


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
        profileViewModel.currentUser.observe(this) {
            //view = it
        }
        profileViewModel.albumsList.observe(this) { albumsList ->
            binding.rvAlbums.layoutManager = LinearLayoutManager(this@ProfileActivity)
            binding.rvAlbums.adapter = AlbumsRecyclerViewAdapter(albumsList!!)
            binding.rvAlbums.addItemDecoration(
                DividerItemDecoration(
                    this@ProfileActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}