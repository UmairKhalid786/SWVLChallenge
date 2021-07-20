package com.techlad.swvlchallenge.data.repository.photos

import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.responses.FlickerResponse


/**
 * Created by Umair on 18,July,2021
 */

interface PhotosRepository {
    suspend fun getPhotos(search: String?): Resource<FlickerResponse>
}