package com.exercise.cvapp.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = [("name")])
data class Profile(
    val name: String,
    val profile_image: String,
    val location: String,
    @Embedded var contact: Contact? = null,
    val summary: String,
    @Json(name = "experience")
    val experienceList: List<Experience>,
    @Json(name = "education")
    val educationList: List<Education>,
    @Embedded var skills: Skills? = null
): Parcelable