package com.exercise.cvapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Experience(
    val company_name: String,
    val date_from_to: String,
    val logo_url: String,
    val role_name: String,
    val location: String
): Parcelable