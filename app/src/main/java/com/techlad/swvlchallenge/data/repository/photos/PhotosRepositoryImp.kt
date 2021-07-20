package com.techlad.swvlchallenge.data.repository.photos

import com.techlad.swvlchallenge.data.datasources.Resource
import com.techlad.swvlchallenge.data.datasources.image_search.FlickerDataSourceImp
import com.techlad.swvlchallenge.data.responses.FlickerResponse
import javax.inject.Inject


/**
 * Created by Umair on 18,July,2021
 */
class PhotosRepositoryImp @Inject constructor(
    private val api: FlickerDataSourceImp
) : PhotosRepository {

    override suspend fun getPhotos(search: String?): Resource<FlickerResponse> =
        api.getPhotos(search)
}