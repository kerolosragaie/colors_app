package com.kerollosragaie.colorsapp.features.album_details.data

import com.kerollosragaie.colorsapp.core.models.Photo

interface AlbumDetailsRepository {
    fun getPhotos(albumId: Int): List<Photo>
}