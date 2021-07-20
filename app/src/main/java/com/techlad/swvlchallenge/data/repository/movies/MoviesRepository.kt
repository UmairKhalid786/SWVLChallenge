package com.techlad.swvlchallenge.data.repository.movies

import androidx.lifecycle.LiveData
import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.responses.MoviesResponse


/**
 * Created by Umair on 18,July,2021
 */

interface MoviesRepository {
    fun getMovies(page: Int): LiveData<Resource<List<MoviesResponse.Data.Movie>>>
    fun getMovies(text: String?): LiveData<Resource<List<MoviesResponse.Data.Movie>>>
    fun getMoviesOnlyFromDb(page: Int): LiveData<Resource<List<MoviesResponse.Data.Movie>>>
}