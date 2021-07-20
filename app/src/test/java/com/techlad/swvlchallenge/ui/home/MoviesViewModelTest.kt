package com.techlad.swvlchallenge.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.techlad.swvlchallenge.MainCoroutineRule
import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.datasources.Resource.Status.ERROR
import com.techlad.swvlchallenge.data.datasources.Resource.Status.SUCCESS
import com.techlad.swvlchallenge.data.repository.movies.MoviesRepositoryImpTest
import com.techlad.swvlchallenge.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        viewModel = MoviesViewModel(MoviesRepositoryImpTest())
    }


    @Test
    fun `next page add method, returns page + 1`() {
        val lastPageSize = viewModel.getCurrentPage()
        viewModel.nextPage()

        Truth.assertThat(viewModel.getCurrentPage()).isEqualTo(lastPageSize + 1)
    }

    @Test
    fun `while searching page size, returns same page`() {
        val lastPageSize = viewModel.getCurrentPage()
        viewModel.search("")
        Truth.assertThat(viewModel.getCurrentPage()).isEqualTo(lastPageSize)
    }

    @Test
    fun `search in movies with null text query, returns ERROR`() {
        viewModel.search(null)
        val value = viewModel.moviesLiveData.getOrAwaitValueTest()
        Truth.assertThat(value.status).isEqualTo(ERROR)
    }

    @Test
    fun `search in movies with some text query, returns success`() {
        viewModel.search("Hi")
        val value = viewModel.moviesLiveData.getOrAwaitValueTest()
        Truth.assertThat(value.status).isEqualTo(SUCCESS)
    }

    @Test
    fun `search in movies with some text query, returns value contains query text`() {
        val query = "hi"
        viewModel.search(query)
        val value = viewModel.moviesLiveData.getOrAwaitValueTest()
        Truth.assertThat(value.data?.get(0)?.title).contains(query)
    }

    @Test
    fun `search in movies with some text query, returns some data`() {
        viewModel.search("Hi")
        val value = viewModel.moviesLiveData.getOrAwaitValueTest()
        Truth.assertThat(value.data).isNotNull()
    }

    @Test
    fun `search in movies with some text query, returns null data`() {
        viewModel.search(null)
        val value = viewModel.moviesLiveData.getOrAwaitValueTest()
        Truth.assertThat(value.data).isNull()
    }

    @Test
    fun `search in movies with some text query, returns error status`() {
        viewModel.search(null)
        val value = viewModel.moviesLiveData.getOrAwaitValueTest()
        Truth.assertThat(value.status).isEqualTo(ERROR)
    }

    @Test
    fun `while searching with empty query, returns error status`() {
        viewModel.search("")
        val value = viewModel.moviesLiveData.getOrAwaitValueTest()
        Truth.assertThat(value.status).isEqualTo(ERROR)
    }

    @Test
    fun `search in movies empty text query, returns null data`() {
        viewModel.search("")
        val value = viewModel.moviesLiveData.getOrAwaitValueTest()
        Truth.assertThat(value.data).isNull()
    }
}