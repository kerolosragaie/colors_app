package com.kerollosragaie.colorsapp.features.album_details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerollosragaie.colorsapp.core.models.Photo
import com.kerollosragaie.colorsapp.features.album_details.data.AlbumDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(private var albumDetailsRepository: AlbumDetailsRepository) :
    ViewModel() {

    private val _photosList by lazy { MutableLiveData<List<Photo>?>() }
    val photosList: LiveData<List<Photo>?> by lazy { _photosList }

    var albumId = 0

    fun callAPI() {
        viewModelScope.launch {
            _photosList.value = albumDetailsRepository.getPhotos(albumId)
        }
    }
}