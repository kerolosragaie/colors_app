package com.kerollosragaie.colorsapp.features.profile.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerollosragaie.colorsapp.core.models.Album
import com.kerollosragaie.colorsapp.core.models.user.User
import com.kerollosragaie.colorsapp.features.profile.data.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ProfileViewModel @Inject constructor(var profileRepository: ProfileRepository) :
    ViewModel() {

    private val _currentUser by lazy { MutableLiveData<User?>() }
    val currentUser: LiveData<User?> by lazy { _currentUser }

    private val _albumsList by lazy { MutableLiveData<List<Album>?>() }
    val albumsList: LiveData<List<Album>?> by lazy { _albumsList }

    init {
        viewModelScope.launch {
            val randomUserId = Random.nextInt(1, 10)
            _currentUser.value = profileRepository.getUser(randomUserId)
            _albumsList.value = profileRepository.getAlbums(randomUserId)
        }
    }
}