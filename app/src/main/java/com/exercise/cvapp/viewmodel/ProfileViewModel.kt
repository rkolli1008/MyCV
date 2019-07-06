package com.exercise.cvapp.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.*
import com.exercise.cvapp.database.getDatabase
import com.exercise.cvapp.repository.ProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

enum class ProfileApiStatus { LOADING, ERROR, DONE }

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val _status = MutableLiveData<ProfileApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<ProfileApiStatus>
        get() = _status

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = SupervisorJob()

    // the Coroutine runs using the Main (UI) dispatcher
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val database = getDatabase(application)
    private val profileRepository = ProfileRepository(database)

    init {
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true
        if (isConnected) getCandidateProfiles()
    }

    private fun getCandidateProfiles() {
        uiScope.launch {
            _status.value = ProfileApiStatus.LOADING
            profileRepository.refreshProfile()
            _status.value = ProfileApiStatus.DONE
        }
    }

    val profile = profileRepository.profile
    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProfileViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}