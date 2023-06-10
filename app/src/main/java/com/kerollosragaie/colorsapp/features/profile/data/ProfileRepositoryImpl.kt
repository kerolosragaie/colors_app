package com.kerollosragaie.colorsapp.features.profile.data

import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.core.models.user.User
import com.kerollosragaie.colorsapp.core.utils.network.ApiServices

class ProfileRepositoryImpl constructor(private val apiServices: ApiServices) :
    ProfileRepository {
    override suspend fun getUser(id: Int): User = apiServices.getUser(id)
    override suspend fun getAlbums(userId: Int): List<Album> = apiServices.getAlbums(userId)

}