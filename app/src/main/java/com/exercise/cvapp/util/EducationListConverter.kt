package com.exercise.cvapp.util

import androidx.room.TypeConverter
import com.exercise.cvapp.models.Education
import com.exercise.cvapp.models.Experience
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class EducationListConverter {
    @TypeConverter
    fun fromString(value: String): List<Education>? {
        val listType = object : TypeToken<List<Education>>() {}.type
        return Gson().fromJson<List<Education>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Education>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}