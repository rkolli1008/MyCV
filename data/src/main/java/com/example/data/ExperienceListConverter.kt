package com.example.data

import androidx.room.TypeConverter
import com.example.data.models.Experience
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class ExperienceListConverter {
    @TypeConverter
    fun fromString(value: String): List<Experience>? {
        val listType = object : TypeToken<List<Experience>>() {}.type
        return Gson().fromJson<List<Experience>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Experience>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}