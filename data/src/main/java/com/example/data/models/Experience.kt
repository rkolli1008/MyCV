package com.example.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Experience(
    val company_name: String,
    val date_from_to: String,
    val logo_url: String,
    val role_name: String,
    val location: String
): Parcelable