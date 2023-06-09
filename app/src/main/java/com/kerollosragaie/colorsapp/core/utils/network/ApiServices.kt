package com.kerollosragaie.colorsapp.core.utils.network

import com.kerollosragaie.colorsapp.core.Constants
import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.core.models.Photo
import com.kerollosragaie.colorsapp.core.models.user.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET(Constants.END_POINT_USERS)
    fun getUser(@Query("id") id: Int): User

    @GET(Constants.END_POINT_PHOTOS)
    fun getPhotos(@Query("albumId") albumId: Int): List<Photo>

    @GET(Constants.END_POINT_PHOTOS)
    fun getAlbums(@Query("userId") userId: Int): List<Album>

}