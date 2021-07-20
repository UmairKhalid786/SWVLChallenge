package com.techlad.swvlchallenge.data.datasources.image_search


import com.techlad.swvlchallenge.BuildConfig
import com.techlad.swvlchallenge.data.responses.FlickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Umair on 18,July,2021
 */

//Try to abstract this API so no direct lower class access
//&api_key=b7d089e81dfcf70fedabac8503962318&format=json&nojsoncallback=1&text=sex&page=1&per_page=10
interface FlickerDataSource {
    @GET("rest/?method=flickr.photos.search")
    suspend fun getContent(
        @Query("api_key") apikey: String = BuildConfig.FLICKER_API_KEY,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
        @Query("text") text: String?,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
    ): Response<FlickerResponse>
}