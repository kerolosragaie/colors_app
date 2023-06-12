package com.kerollosragaie.colorsapp.features.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.core.models.user.User
import com.kerollosragaie.colorsapp.features.profile.data.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ProfileViewModel @Inject constructor(var profileRepository: ProfileRepository) :
    ViewModel() {

    private val _currentUser by lazy { MutableStateFlow<User?>(null) }
    val currentUser: StateFlow<User?> by lazy { _currentUser }

    private val _albumsList by lazy { MutableStateFlow<List<Album>?>(null) }
    val albumsList: StateFlow<List<Album>?> by lazy { _albumsList }

    init {
        viewModelScope.launch {
            val randomUserId = Random.nextInt(1, 10)
            _currentUser.emit(profileRepository.getUser(randomUserId))
            _albumsList.emit(profileRepository.getAlbums(randomUserId))
        }
    }
}