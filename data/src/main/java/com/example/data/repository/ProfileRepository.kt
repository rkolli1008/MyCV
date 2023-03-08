package com.example.data.repository

import androidx.lifecycle.LiveData
import com.example.data.ProfileDao
import com.example.data.network.ProfileNetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileDao: ProfileDao) {

    /**
     * A profile that can be shown on the screen.
     */
    val profile: LiveData<com.example.data.models.Profile> = profileDao.getProfileInRoom()

    /**
     * Refresh the profile stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the profile for use, observe [profile]
     */
    suspend fun fetchProfile() {
        withContext(Dispatchers.IO) {
            val profile = ProfileNetworkService.RETROFIT_SERVICE.getProfileAsync().await()
            profileDao.insertAll(profile)
        }
    }
}
