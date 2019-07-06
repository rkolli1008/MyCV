package com.exercise.cvapp.repository

import androidx.lifecycle.LiveData
import com.exercise.cvapp.database.ProfileDatabase
import com.exercise.cvapp.models.Profile
import com.exercise.cvapp.network.ProfileApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository(private val database: ProfileDatabase) {

    /**
     * A profile that can be shown on the screen.
     */
    val profile: LiveData<Profile> = database.profileDao.getProfile()

    /**
     * Refresh the profile stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the profile for use, observe [profile]
     */
    suspend fun refreshProfile() {
        withContext(Dispatchers.IO) {
            val profile = ProfileApi.retrofitService.getProfileAsync().await()
            database.profileDao.insertAll(profile)
        }
    }
}
