package com.exercise.cvapp.di

import android.content.Context
import androidx.room.Room
import com.exercise.cvapp.database.ProfileDao
import com.exercise.cvapp.database.ProfileDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideLogDao(database: ProfileDatabase): ProfileDao {
        return database.profileDao
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ProfileDatabase {
        return Room.databaseBuilder(appContext,
            ProfileDatabase::class.java,
            "profile").build()
    }
}