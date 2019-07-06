package com.exercise.cvapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.*
import com.exercise.cvapp.models.Profile
import com.exercise.cvapp.util.EducationListConverter
import com.exercise.cvapp.util.ExperienceListConverter

@Dao
interface ProfileDao {
    @Query("select * from Profile")
    fun getProfile(): LiveData<Profile>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg profile: Profile)
}

@Database(entities = [Profile::class], version = 1, exportSchema = false)
@TypeConverters(value = [ExperienceListConverter::class, EducationListConverter::class])
abstract class ProfileDatabase : RoomDatabase() {
    abstract val profileDao: ProfileDao
}

private lateinit var INSTANCE: ProfileDatabase

fun getDatabase(context: Context): ProfileDatabase {
    synchronized(ProfileDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ProfileDatabase::class.java,
                    "profile").build()
        }
    }
    return INSTANCE
}
