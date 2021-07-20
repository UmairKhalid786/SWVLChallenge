package com.techlad.swvlchallenge.data.datasources.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.responses.MoviesResponse
import java.lang.reflect.Type
import javax.inject.Inject


/**
 * Created by Umair on 18,July,2021
 */

class MoviesListMapper @Inject constructor(var gson: Gson) :
    Mapper<String, List<MoviesResponse.Data.Movie>> {
    override fun map(input: String?): Resource<List<MoviesResponse.Data.Movie>> {
        try {
            val listType: Type = object : TypeToken<MoviesResponse.Data>() {}.type
            val data: MoviesResponse.Data = Gson().fromJson(input, listType)
            return Resource.success(data.movies)
        } catch (e: Exception) {
            return Resource.error(e.message!!, arrayListOf())
        }
    }
}