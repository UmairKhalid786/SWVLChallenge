package com.techlad.swvlchallenge.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.techlad.swvlchallenge.MainCoroutineRule
import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.datasources.Resource.Status.ERROR
import com.techlad.swvlchallenge.data.datasources.Resource.Status.SUCCESS
import com.techlad.swvlchallenge.data.repository.photos.PhotosRepositoryImpTest
import com.techlad.swvlchallenge.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PhotosViewModelTest {

    private lateinit var viewModel: PhotosViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        viewModel = PhotosViewModel(PhotosRepositoryImpTest())
    }

    @Test
    fun `search in photos with null query, returns error`() {
        viewModel.getPhotos(null)
        val value = viewModel.content.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(ERROR)
    }

    @Test
    fun `search in photos with some text query, returns success`() {
        viewModel.getPhotos("Hi")
        val value = viewModel.content.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(SUCCESS)
    }


}