package com.kerollosragaie.colorsapp.features.album_details.presentation.view

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kerollosragaie.colorsapp.core.Constants.ALBUM_DATA
import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.core.models.Photo
import com.kerollosragaie.colorsapp.databinding.ActivityAlbumDetailsBinding
import com.kerollosragaie.colorsapp.features.album_details.presentation.viewmodel.AlbumDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import kotlin.math.log


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
    private lateinit var photosAdapter: PhotosAdapter

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
            photosAdapter = PhotosAdapter(photosList!!)
            binding.gvPhotos.adapter = photosAdapter
        }

        setupSearchET()
    }

    private fun loadingAlbumData() {
        album = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(ALBUM_DATA, Album::class.java)!!
        } else {
            intent.getParcelableExtra(ALBUM_DATA)!!
        }
    }

    private fun setupSearchET(){
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(strTyped: Editable?) {
                searchFun(strTyped.toString())
            }

        })
    }
    private fun searchFun(strTyped: String) {
        val filteredList = arrayListOf<Photo>()

        for (item in albumDetailsViewModel.photosList.value!!) {
            if (item.title.lowercase(Locale.ROOT)
                    .contains(strTyped.lowercase(Locale.ROOT))
            ) {
                filteredList.add(item)
            }
        }

        if (filteredList.size == 0) {
            binding.errorSearchAlbumDetails.errorSearch.visibility = View.VISIBLE
        } else {
            binding.errorSearchAlbumDetails.errorSearch.visibility = View.GONE
        }

        //Update list using adapter:
        photosAdapter.updateList(filteredList)
    }
}