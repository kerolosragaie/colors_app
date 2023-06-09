package com.kerollosragaie.colorsapp.core.utils.network

import com.kerollosragaie.colorsapp.core.Constants
import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.core.models.Photo
import com.kerollosragaie.colorsapp.core.models.user.User
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("${Constants.END_POINT_USERS}/{id}")
    suspend fun getUser(@Path("id") id: Int): User

    @GET(Constants.END_POINT_PHOTOS)
    suspend fun getPhotos(@Query("albumId") albumId: Int): List<Photo>

    @GET(Constants.END_POINT_ALBUMS)
    suspend fun getAlbums(@Query("userId") userId: Int): List<Album>

}