package com.example.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.*
import androidx.room.RoomDatabase
import com.example.data.models.Profile

@Dao
interface ProfileDao {
    @Query("select * from Profile")
    fun getProfileInRoom(): LiveData<Profile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg profile: Profile)
}

@Database(entities = [Profile::class], version = 1, exportSchema = false)
@TypeConverters(value = [ExperienceListConverter::class, EducationListConverter::class])
abstract class ProfileDatabase : RoomDatabase() {
    abstract val profileDao: ProfileDao
}