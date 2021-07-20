package com.techlad.swvlchallenge.di.modules

import com.google.gson.Gson
import com.techlad.swvlchallenge.data.datasources.mappers.Mapper
import com.techlad.swvlchallenge.data.datasources.mappers.MoviesListMapper
import com.techlad.swvlchallenge.data.responses.MoviesResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MappersModule {
    @Provides
    @Singleton
    fun bindsMoviesListMapper(gson: Gson): Mapper<String, List<MoviesResponse.Data.Movie>> {
        return MoviesListMapper(gson)
    }
}
