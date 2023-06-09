package com.kerollosragaie.colorsapp.features.profile.data

import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.core.models.user.User
import com.kerollosragaie.colorsapp.core.utils.network.ApiServices


class ProfileRepositoryImpl(private val apiServices: ApiServices) : ProfileRepository {
    override fun getUser(id: Int): User = apiServices.getUser(id)

    override fun getAlbums(userId: Int): List<Album> = apiServices.getAlbums(userId)
}