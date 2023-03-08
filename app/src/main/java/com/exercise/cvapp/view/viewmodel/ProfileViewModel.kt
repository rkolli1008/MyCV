package com.exercise.cvapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ProfileApiStatus {
    object LOADING : ProfileApiStatus()
    object ERROR : ProfileApiStatus()
    object DONE : ProfileApiStatus()
}

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileRepository: ProfileRepository) : ViewModel() {
    private val _status = MutableLiveData<ProfileApiStatus>()
    val profileData = profileRepository.profile

    // The external immutable LiveData for the request status
    val status: LiveData<ProfileApiStatus>
        get() = _status

    init {
        getCandidateProfiles()
    }

    private fun getCandidateProfiles() {
        viewModelScope.launch {
            try {
                _status.value = ProfileApiStatus.LOADING
                profileRepository.fetchProfile()
                _status.value = ProfileApiStatus.DONE
            } catch (exception: Exception) {
                _status.value = ProfileApiStatus.ERROR
            }
        }
    }
}