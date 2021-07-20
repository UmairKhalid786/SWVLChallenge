package com.techlad.swvlchallenge.data.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.techlad.swvlchallenge.data.responses.MoviesResponse
import java.util.*


class MoviesConverters {

    var gson = Gson()

    @TypeConverter
    fun storedStringToMovies(data: String?): List<MoviesResponse.Data.Movie>? {

        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<ArrayList<MoviesResponse.Data.Movie?>?>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun moviesToStoredString(myObjects: List<MoviesResponse.Data.Movie>?): String? {
        val listType = object : TypeToken<ArrayList<MoviesResponse.Data.Movie?>?>() {}.type
        return Gson().toJson(myObjects, listType)
    }
}