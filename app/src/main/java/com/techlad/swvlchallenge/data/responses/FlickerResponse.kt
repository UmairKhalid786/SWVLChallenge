package com.techlad.swvlchallenge.data.responses

import com.techlad.swvlchallenge.utils.Utils


/**
 * Created by Umair on 17,July,2021
 */

data class FlickerResponse(
    val photos: Photos
) {

    data class Photos(
        val photo: List<Photo>
    ) {

        data class Photo(
            val id: String,
            val owner: String,
            val secret: String,
            val title: String,
            val farm: String,
            val server: String
        ) {

            fun image() = Utils.replaceAll(this)
        }
    }
}