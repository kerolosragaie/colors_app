package com.kerollosragaie.colorsapp.features.album_details.data

import com.kerollosragaie.colorsapp.core.models.Photo

sealed class PhotosListState{
    data class Loaded(val photosList:List<Photo>): PhotosListState()
    object Empty : PhotosListState()
}