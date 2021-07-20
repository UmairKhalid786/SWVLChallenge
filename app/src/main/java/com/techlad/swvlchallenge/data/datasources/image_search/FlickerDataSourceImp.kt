package com.techlad.swvlchallenge.data.datasources.image_search

import com.techlad.swvlchallenge.data.datasources.BaseDataSource
import javax.inject.Inject


/**
 * Created by Umair on 18,July,2021
 */

class FlickerDataSourceImp @Inject constructor(
    private val dataSource: FlickerDataSource
) : BaseDataSource() {

    suspend fun getPhotos(
        text: String?
    ) = getResult { dataSource.getContent(text = text) }
}