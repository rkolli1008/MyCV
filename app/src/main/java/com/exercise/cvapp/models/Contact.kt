package com.exercise.cvapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val email: String,
    val phone: String,
    val profile_link: String
): Parcelable