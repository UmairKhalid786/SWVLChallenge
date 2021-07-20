package com.techlad.swvlchallenge.di.modules

import com.techlad.swvlchallenge.data.repository.movies.MoviesRepository
import com.techlad.swvlchallenge.data.repository.movies.MoviesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesModule {
    @Binds
    abstract fun provideMoviesRepository(contentRepositoryImpl: MoviesRepositoryImp): MoviesRepository
}