package com.example.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Education(
    val course_title: String,
    val university_name: String,
    val logo_url: String,
    val period: String
): Parcelable