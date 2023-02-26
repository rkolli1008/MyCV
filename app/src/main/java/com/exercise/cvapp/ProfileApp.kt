package com.exercise.cvapp;

import android.app.Application;

import com.exercise.cvapp.database.ProfileDatabase;
import com.exercise.cvapp.repository.ProfileRepository

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
class ProfileApp: Application() {
    @Inject lateinit var database: ProfileDatabase
    @Inject lateinit var profileRepository: ProfileRepository
}
