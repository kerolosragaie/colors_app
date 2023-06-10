package com.kerollosragaie.colorsapp.features.album_details.presentation.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kerollosragaie.colorsapp.core.Constants.ALBUM_DATA
import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.databinding.ActivityAlbumDetailsBinding
import com.kerollosragaie.colorsapp.features.album_details.presentation.viewmodel.AlbumDetailsViewModel
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

    private lateinit var album: Album

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadingAlbumData()
        initUI()
    }

    private fun initUI() {
        binding.tvAlbumName.text = album.title

        albumDetailsViewModel.albumId = album.id
        albumDetailsViewModel.callAPI()
        albumDetailsViewModel.photosList.observe(this) { photosList ->
            binding.gvPhotos.layoutManager = GridLayoutManager(this@AlbumDetailsActivity, 3)
            binding.gvPhotos.adapter = PhotosAdapter(photosList!!)
        }
    }

    private fun loadingAlbumData() {
        album = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(ALBUM_DATA, Album::class.java)!!
        } else {
            intent.getParcelableExtra(ALBUM_DATA)!!
        }
    }
}