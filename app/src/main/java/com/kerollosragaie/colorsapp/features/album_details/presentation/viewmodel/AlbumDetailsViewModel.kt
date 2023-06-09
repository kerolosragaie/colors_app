package com.kerollosragaie.colorsapp.features.album_details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerollosragaie.colorsapp.core.models.Photo
import com.kerollosragaie.colorsapp.features.album_details.data.AlbumDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(private var albumDetailsRepository: AlbumDetailsRepository) :
    ViewModel() {

    private var photosList: List<Photo>? = null

}