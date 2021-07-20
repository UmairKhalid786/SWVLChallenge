package com.techlad.swvlchallenge.data.datasources.movies

import com.techlad.swvlchallenge.data.MoviesClient
import com.techlad.swvlchallenge.data.responses.MoviesResponse


/**
 * Created by Umair on 18,July,2021
 */

class MoviesDataSourceImp constructor(val imp: MoviesClient) : MoviesDataSource {
    override suspend fun getContent(): MoviesResponse {
        return MoviesResponse(imp.fetchBlocksDataInString())
    }
}