package com.techlad.swvlchallenge.di.modules

import com.techlad.swvlchallenge.data.repository.photos.PhotosRepository
import com.techlad.swvlchallenge.data.repository.photos.PhotosRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Created by Umair on 18,July,2021
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class PhotosModule {
    @Binds
    abstract fun providePhotosRepository(contentRepositoryImpl: PhotosRepositoryImp): PhotosRepository
}