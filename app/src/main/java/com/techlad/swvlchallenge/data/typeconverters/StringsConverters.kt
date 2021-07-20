package com.techlad.swvlchallenge.data.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class StringsConverters {

    @TypeConverter
    fun storedStringToStrings(data: String?): List<String>? {

        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun listToStoredString(myObjects: List<String>?): String? {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().toJson(myObjects, listType)
    }
}