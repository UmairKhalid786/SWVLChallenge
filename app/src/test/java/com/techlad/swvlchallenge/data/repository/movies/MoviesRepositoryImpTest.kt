package com.techlad.swvlchallenge.data.repository.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.responses.MoviesResponse.Data.Movie
import org.junit.Rule

class MoviesRepositoryImpTest : MoviesRepository {

    @get:Rule
    val instantRuleExecutor = InstantTaskExecutorRule()

    private val movies = mutableListOf<Movie>()
    private val observableMoviesItems =
        MutableLiveData<Resource<List<Movie>>>(Resource.loading(null))

    private fun refreshLiveData(success: Boolean = true) {
        if (success)
            observableMoviesItems.postValue(Resource.success(movies))
        else
            observableMoviesItems.postValue(Resource.error("error"))
    }


    override fun getMovies(page: Int): LiveData<Resource<List<Movie>>> {
        movies.add(Movie(0, "", 0, null, null, 0))
        movies.add(Movie(1, "", 0, null, null, 0))
        refreshLiveData(page > 0)
        return observableMoviesItems
    }

    override fun getMovies(text: String?): LiveData<Resource<List<Movie>>> {
        movies.add(Movie(0, text ?: "", 0, null, null, 0))
        refreshLiveData(!text.isNullOrBlank())
        return observableMoviesItems
    }

    override fun getMoviesOnlyFromDb(page: Int): LiveData<Resource<List<Movie>>> {
        movies.add(Movie(0, "", 0, null, null, 0))
        movies.add(Movie(1, "", 0, null, null, 0))
        refreshLiveData(page > 0)
        return observableMoviesItems
    }
}