package com.kerollosragaie.colorsapp.features.album_details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerollosragaie.colorsapp.core.models.Photo
import com.kerollosragaie.colorsapp.features.album_details.data.PhotosListState
import com.kerollosragaie.colorsapp.features.album_details.data.AlbumDetailsRepository
import com.kerollosragaie.colorsapp.features.album_details.presentation.view.PhotoRVAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(private var albumDetailsRepository: AlbumDetailsRepository) :
    ViewModel() {

    private lateinit var _photosList: List<Photo>
    private val _photosListData by lazy { MutableLiveData<PhotosListState>() }
    val photosListState: LiveData<PhotosListState> by lazy { _photosListData }

    var albumId = 0

    fun callAPI() {
        viewModelScope.launch {
            _photosList = albumDetailsRepository.getPhotos(albumId)
            _photosListData.value = PhotosListState.Loaded(_photosList)
        }
    }

    fun searchFun(strTyped: String) {
        val filteredList = arrayListOf<Photo>()

        for (item in _photosList) {
            if (item.title.lowercase(Locale.ROOT)
                    .contains(strTyped.lowercase(Locale.ROOT))
            ) {
                filteredList.add(item)
            }
        }

        if (filteredList.isEmpty() && strTyped.isNotEmpty()) {
            _photosListData.value = PhotosListState.Empty
        } else if (filteredList.isNotEmpty() && strTyped.isNotEmpty()) {
            _photosListData.value = PhotosListState.Loaded(filteredList)
        } else {
            _photosListData.value = PhotosListState.Loaded(_photosList)
        }
    }
}