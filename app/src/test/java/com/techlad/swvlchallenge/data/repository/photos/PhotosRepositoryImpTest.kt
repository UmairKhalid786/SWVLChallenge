package com.techlad.swvlchallenge.data.repository.photos

import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.datasources.Resource.Status.ERROR
import com.techlad.swvlchallenge.data.datasources.Resource.Status.SUCCESS
import com.techlad.swvlchallenge.data.responses.FlickerResponse


class PhotosRepositoryImpTest : PhotosRepository {

    override suspend fun getPhotos(search: String?): Resource<FlickerResponse> {
        val sts = if (search != null) SUCCESS else ERROR
        return Resource(sts, null, "")
    }
}