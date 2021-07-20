package com.techlad.swvlchallenge.di.modules

import android.content.Context
import com.techlad.swvlchallenge.data.MoviesClient
import com.techlad.swvlchallenge.data.datasources.movies.MoviesDataSource
import com.techlad.swvlchallenge.data.datasources.movies.MoviesDataSourceImp
import com.techlad.swvlchallenge.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Umair on 18,July,2021
 */

@InstallIn(SingletonComponent::class)
@Module
class MainModule {


    @Singleton
    @Provides
    fun provideApi(client: MoviesClient): MoviesDataSource {
        return MoviesDataSourceImp(client)
    }

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(context = appContext)
    }

    @Singleton
    @Provides
    fun provideMoviesDao(db: AppDatabase) = db.moviesDao()

    @Singleton
    @Provides
    fun providesMoviesClient(@ApplicationContext appContext: Context) = MoviesClient(appContext)
}