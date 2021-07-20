package com.techlad.swvlchallenge.ui.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.repository.movies.MoviesRepository
import com.techlad.swvlchallenge.data.responses.MoviesResponse.Data.Movie
import com.techlad.swvlchallenge.utils.Constants.PAGE_SIZE
import com.techlad.swvlchallenge.utils.Utils.calculateCountSize
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val useCase: MoviesRepository
) : ViewModel() {

    val moviesLiveData: MediatorLiveData<Resource<List<Movie>>> by lazy {
        MediatorLiveData()
    }

    private var page = 1

    fun startDataLoad() {
        moviesLiveData.addSource(useCase.getMovies(calculateCountSize(page))) { value ->
            moviesLiveData.setValue(
                value
            )
        }
    }

    fun nextPage() {
        page++
        moviesLiveData.addSource(useCase.getMoviesOnlyFromDb(calculateCountSize(page))) { value ->
            moviesLiveData.setValue(
                value
            )
        }
    }

    fun search(query: String?) {
        moviesLiveData.addSource(useCase.getMovies(query)) { value -> moviesLiveData.setValue(value) }
    }


    fun getCurrentPage() = page
}