package com.kerollosragaie.colorsapp.features.album_details.presentation.view

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kerollosragaie.colorsapp.core.Constants.ALBUM_DATA
import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.databinding.ActivityAlbumDetailsBinding
import com.kerollosragaie.colorsapp.features.album_details.data.PhotosListState
import com.kerollosragaie.colorsapp.features.album_details.presentation.viewmodel.AlbumDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


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

    private val photosRVAdapter by lazy { PhotoRVAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        val album = getAlbumData()
        binding.tvAlbumName.text = album.title
        albumDetailsViewModel.albumId = album.id
        binding.gvPhotos.apply {
            layoutManager = GridLayoutManager(this@AlbumDetailsActivity, 3)
            adapter = photosRVAdapter
        }

        albumDetailsViewModel.callAPI()

        lifecycleScope.launch {
            albumDetailsViewModel.state.collect {
                handlePhotosListState(it)
            }
        }

        setupSearchET()
    }

    private fun getAlbumData(): Album =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(ALBUM_DATA, Album::class.java)!!
        } else {
            intent.getParcelableExtra(ALBUM_DATA)!!
        }

    private fun setupSearchET() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(strTyped: Editable?) {
                albumDetailsViewModel.filterPhotosList(strTyped.toString())
            }
        })
    }

    private fun handlePhotosListState(state: PhotosListState) {
        when (state) {
            is PhotosListState.Empty -> {
                binding.gvPhotos.visibility = View.GONE
                binding.errorSearchAlbumDetails.errorSearch.visibility = View.VISIBLE
            }
            is PhotosListState.Loaded -> {
                binding.gvPhotos.visibility = View.VISIBLE
                binding.errorSearchAlbumDetails.errorSearch.visibility = View.GONE
                photosRVAdapter.submitList(state.photosList)
            }
        }
    }

}