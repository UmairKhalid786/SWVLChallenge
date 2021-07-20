package com.techlad.swvlchallenge.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.repository.photos.PhotosRepository
import com.techlad.swvlchallenge.data.responses.FlickerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val useCase: PhotosRepository
) : ViewModel() {

    private val _content = MutableLiveData<Resource<FlickerResponse>>()
    val content: LiveData<Resource<FlickerResponse>> = _content

    fun getPhotos(text: String?) {
        viewModelScope.launch {
            val content = withContext(Dispatchers.IO) {
                useCase.getPhotos(text)
            }
            _content.value = content
        }
    }
}