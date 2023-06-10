package com.kerollosragaie.colorsapp.features.album_details.presentation.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerollosragaie.colorsapp.core.models.Photo
import com.kerollosragaie.colorsapp.features.album_details.data.AlbumDetailsRepository
import com.kerollosragaie.colorsapp.features.album_details.presentation.view.PhotoRVAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(private var albumDetailsRepository: AlbumDetailsRepository) :
    ViewModel() {

    private val _photosList by lazy { MutableLiveData<List<Photo>?>() }
    val photosList: LiveData<List<Photo>?> by lazy { _photosList }

    val photosRVAdapter by lazy { PhotoRVAdapter() }

    var albumId = 0

    fun callAPI() {
        viewModelScope.launch {
            _photosList.value = albumDetailsRepository.getPhotos(albumId)
        }
    }

    fun searchFun(strTyped: String, errorSearchLayout: View) {
        val filteredList = arrayListOf<Photo>()

        for (item in _photosList.value!!) {
            if (item.title.lowercase(Locale.ROOT)
                    .contains(strTyped.lowercase(Locale.ROOT))
            ) {
                filteredList.add(item)
            }
        }

        if (filteredList.size == 0) {
            errorSearchLayout.visibility = View.VISIBLE
        } else {
            errorSearchLayout.visibility = View.GONE
        }

        //Update list using adapter:
        photosRVAdapter.submitList(filteredList)
    }
}