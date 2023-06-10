package com.kerollosragaie.colorsapp.features.profile.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.core.models.user.User
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
        profileViewModel.currentUser.observe(this) { user ->
            setUpUserInfo(user)
        }
        profileViewModel.albumsList.observe(this) { albumsList ->
            setupRvAlbums(albumsList)
        }
    }

    private fun setUpUserInfo(user: User?) {
        binding.tvUsername.text = user?.name
        binding.tvAddress.text =
            "${user?.address?.street}, ${user?.address?.suite}, ${user?.address?.city},"
        binding.tvZipcode.text = user?.address?.zipcode
    }
    private fun setupRvAlbums(albumsList: List<Album>?) {
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