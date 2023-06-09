package com.kerollosragaie.colorsapp.features.profile.data

import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.core.models.user.User

interface ProfileRepository {
    fun getUser(id: Int): User
    fun getAlbums(userId: Int): List<Album>
}