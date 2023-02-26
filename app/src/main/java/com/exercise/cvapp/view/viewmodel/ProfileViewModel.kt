package com.exercise.cvapp.view.viewmodel

import androidx.lifecycle.*
import com.exercise.cvapp.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ProfileApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileRepository: ProfileRepository) : ViewModel() {
    private val _status = MutableLiveData<ProfileApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<ProfileApiStatus>
        get() = _status

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = SupervisorJob()

    // the Coroutine runs using the Main (UI) dispatcher
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        /*val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true*/
        /*if (isConnected) */getCandidateProfiles()
    }

    private fun getCandidateProfiles() {
        uiScope.launch {
            _status.value = ProfileApiStatus.LOADING
            profileRepository.fetchProfile()
            _status.value = ProfileApiStatus.DONE
        }
    }

    val profileData = profileRepository.profile
    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}