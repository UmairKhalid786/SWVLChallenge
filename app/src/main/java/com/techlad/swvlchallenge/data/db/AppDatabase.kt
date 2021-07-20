package com.techlad.swvlchallenge.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.techlad.swvlchallenge.data.responses.MoviesResponse
import com.techlad.swvlchallenge.data.typeconverters.MoviesConverters
import com.techlad.swvlchallenge.data.typeconverters.StringsConverters


/**
 * Created by Umair on 17,July,2021
 */

@Database(entities = [MoviesResponse.Data.Movie::class], version = 1, exportSchema = false)
@TypeConverters(MoviesConverters::class, StringsConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "movies_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}