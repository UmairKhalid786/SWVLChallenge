package com.techlad.swvlchallenge.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.techlad.swvlchallenge.BuildConfig
import com.techlad.swvlchallenge.data.datasources.image_search.FlickerDataSource
import com.techlad.swvlchallenge.data.datasources.image_search.FlickerDataSourceImp
import com.techlad.swvlchallenge.network.NoInternetInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Umair on 18,July,2021
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class NetModule {

    companion object {

        @Singleton
        @Provides
        fun provideRetrofit(gson: Gson): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(NoInternetInterceptor())
                .build()
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

        @Singleton
        @Provides
        fun provideGson(): Gson = GsonBuilder().create()

        @Singleton
        @Provides
        fun provideFlickerRemoteDataSource(api: FlickerDataSource) = FlickerDataSourceImp(api)


        @Provides
        @Singleton
        fun provideFlickerApi(retrofit: Retrofit): FlickerDataSource {
            return retrofit.create(FlickerDataSource::class.java)
        }
    }
}