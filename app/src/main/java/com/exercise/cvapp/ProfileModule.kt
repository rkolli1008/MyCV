package com.exercise.cvapp

import android.content.Context
import androidx.room.Room
import com.example.data.ProfileDao
import com.example.data.ProfileDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {
   @Provides
    fun provideLogDao(database: ProfileDatabase): ProfileDao {
        return database.profileDao
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ProfileDatabase {
        return Room.databaseBuilder(appContext,
            ProfileDatabase::class.java,
            "profile_database").build()
    }
}