package com.example.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Skills(
    val environment: String,
    val methodologies: String,
    val technologies: String,
    val programming_languages: String
): Parcelable