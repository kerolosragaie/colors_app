package com.kerollosragaie.colorsapp.features.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.core.models.user.User
import com.kerollosragaie.colorsapp.features.profile.data.ProfileRepository
import com.kerollosragaie.colorsapp.features.profile.data.ProfileRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ProfileViewModel @Inject constructor(private var profileRepository: ProfileRepository) :
    ViewModel() {

    private var currentUser: User? = null
    private var albumsList: List<Album>? = null

    init {
        viewModelScope.launch {
            val randomUserId = Random.nextInt(1, 10)
            currentUser = profileRepository.getUser(randomUserId)
            albumsList = profileRepository.getAlbums(randomUserId)
        }
    }
}