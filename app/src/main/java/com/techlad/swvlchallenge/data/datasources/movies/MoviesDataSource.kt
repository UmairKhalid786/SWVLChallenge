package com.techlad.swvlchallenge.data.datasources.movies

import com.techlad.swvlchallenge.data.responses.MoviesResponse


/**
 * Created by Umair on 18,July,2021
 */

//Try to abstract this API so no direct lower class access
interface MoviesDataSource {
    suspend fun getContent(): MoviesResponse
}