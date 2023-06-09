package com.kerollosragaie.colorsapp.features.album_details.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kerollosragaie.colorsapp.databinding.ActivityAlbumDetailsBinding
import com.kerollosragaie.colorsapp.features.album_details.presentation.viewmodel.AlbumDetailsViewModel
import com.kerollosragaie.colorsapp.features.profile.presentation.view.AlbumsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AlbumDetailsActivity : AppCompatActivity() {
    private val binding: ActivityAlbumDetailsBinding by lazy {
        ActivityAlbumDetailsBinding.inflate(
            layoutInflater
        )
    }

    private val albumDetailsViewModel: AlbumDetailsViewModel by lazy {
        ViewModelProvider(this@AlbumDetailsActivity)[AlbumDetailsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {

        albumDetailsViewModel.photosList.observe(this) { photosList ->
            binding.gvPhotos.layoutManager = GridLayoutManager(this@AlbumDetailsActivity,3)
            binding.gvPhotos.adapter = PhotosAdapter(photosList!!)
        }

    }
}