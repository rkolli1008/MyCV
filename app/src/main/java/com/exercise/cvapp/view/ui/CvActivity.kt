package com.exercise.cvapp.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exercise.cvapp.R
import com.facebook.stetho.Stetho

class CvActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)
    }
}
