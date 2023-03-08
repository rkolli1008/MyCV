package com.example.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val email: String,
    val phone: String,
    val profile_link: String
): Parcelable