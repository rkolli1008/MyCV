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


/*
fun Profile.asDatabaseModel(): DatabaseProfile {
    return DatabaseProfile(
        name = name,
        profile_image = profile_image,
        location = location,
        contact = contact,
        summary = summary,
        experienceList = experienceList,
        educationList = educationList,
        skills = skills)
}*/
