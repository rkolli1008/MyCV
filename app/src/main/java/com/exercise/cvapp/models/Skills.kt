package com.exercise.cvapp.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Skills(
    val environment: String,
    val methodologies: String,
    val technologies: String,
    val programming_languages: String
): Parcelable