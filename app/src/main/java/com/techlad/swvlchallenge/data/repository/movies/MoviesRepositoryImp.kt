package com.techlad.swvlchallenge.data.repository.movies

import com.techlad.swvlchallenge.data.datasources.mappers.Mapper
import com.techlad.swvlchallenge.data.datasources.movies.MoviesDataSource
import com.techlad.swvlchallenge.data.db.MoviesDao
import com.techlad.swvlchallenge.data.responses.MoviesResponse
import com.techlad.swvlchallenge.utils.performGetOperation
import javax.inject.Inject


/**
 * Created by Umair on 18,July,2021
 */

class MoviesRepositoryImp @Inject constructor(
    private val moviesApi: MoviesDataSource,
    private val localDataSource: MoviesDao,
    private val languageDataMapper: Mapper<String, List<MoviesResponse.Data.Movie>>
) : MoviesRepository {

    override fun getMovies(page: Int) = performGetOperation(
        databaseQuery = { localDataSource.getAllMovies(page) },
        networkCall = { languageDataMapper.map(moviesApi.getContent().moviesString) },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    override fun getMovies(search: String?) = performGetOperation {
        localDataSource.getMovies(search)
    }

    override fun getMoviesOnlyFromDb(page: Int) = performGetOperation {
        localDataSource.getAllMovies(page)
    }
}