package com.techlad.swvlchallenge.data.responses

import android.os.Parcelable
import androidx.room.*
import com.techlad.swvlchallenge.data.typeconverters.MoviesConverters
import com.techlad.swvlchallenge.data.typeconverters.StringsConverters
import kotlinx.android.parcel.Parcelize


/**
 * Created by Umair on 17,July,2021
 */
data class MoviesResponse(val moviesString: String?) {

    data class Data(
        @TypeConverters(MoviesConverters::class)
        @Embedded
        val movies: List<Movie>
    ) {

        @Parcelize
        @Entity(tableName = "movies", indices = [Index(value = ["title"], unique = true)])
        data class Movie(
            @PrimaryKey(autoGenerate = true)
            val id: Int,
            val title: String,
            val year: Int,
            @TypeConverters(StringsConverters::class)
            val cast: List<String>?,
            @TypeConverters(StringsConverters::class)
            val genres: List<String>?,
            val rating: Int,
        ) : Parcelable {
            fun genresToString() = genres?.joinToString { it }
            fun castToString() = cast?.joinToString { it }
        }
    }
}