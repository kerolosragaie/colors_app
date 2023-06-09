package com.kerollosragaie.colorsapp.features.profile.di

import com.kerollosragaie.colorsapp.core.utils.network.ApiServices
import com.kerollosragaie.colorsapp.features.profile.data.ProfileRepository
import com.kerollosragaie.colorsapp.features.profile.data.ProfileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule{

    @Provides
    @Singleton
    fun provideProfileRepo(apiServices: ApiServices): ProfileRepository = ProfileRepositoryImpl(apiServices)
}