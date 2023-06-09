package com.kerollosragaie.colorsapp.features.album_details.data

import com.kerollosragaie.colorsapp.core.models.Photo
import com.kerollosragaie.colorsapp.core.utils.network.ApiServices

class AlbumDetailsRepositoryImpl constructor(private val apiServices: ApiServices) : AlbumDetailsRepository {
    override suspend fun getPhotos(albumId: Int): List<Photo> = apiServices.getPhotos(albumId)
}