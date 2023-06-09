package com.kerollosragaie.colorsapp.features.album_details.di

import com.kerollosragaie.colorsapp.core.utils.network.ApiServices
import com.kerollosragaie.colorsapp.features.album_details.data.AlbumDetailsRepository
import com.kerollosragaie.colorsapp.features.album_details.data.AlbumDetailsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlbumDetailsModule {
    @Provides
    @Singleton
    fun provideAlbumDetailsRepo(apiServices: ApiServices): AlbumDetailsRepository = AlbumDetailsRepositoryImpl(apiServices)
}