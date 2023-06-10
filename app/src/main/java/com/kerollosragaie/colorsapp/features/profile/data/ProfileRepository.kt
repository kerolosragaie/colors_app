package com.kerollosragaie.colorsapp.features.profile.data

import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.core.models.user.User
import retrofit2.Response

interface ProfileRepository {
    suspend fun getUser(id: Int): User
    suspend fun getAlbums(userId: Int): List<Album>
}