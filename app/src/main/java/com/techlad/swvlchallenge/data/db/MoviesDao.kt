package com.techlad.swvlchallenge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techlad.swvlchallenge.data.responses.MoviesResponse


/**
 * Created by Umair on 17,July,2021
 */
@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies limit :page")
    fun getAllMovies(page: Int): LiveData<List<MoviesResponse.Data.Movie>>

    @Query("SELECT * FROM movies limit :no")
    fun getMovieForPage(no: Int): LiveData<List<MoviesResponse.Data.Movie>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovie(id: Int): LiveData<MoviesResponse.Data.Movie>

//    @Query("SELECT * FROM movies  WHERE title LIKE '%' || :query || '%' GROUP BY year")
//    fun getMovies(query: String): LiveData<List<MoviesResponse.Data.Movie>>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    fun getMovies(query: String?): LiveData<List<MoviesResponse.Data.Movie>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(movies: List<MoviesResponse.Data.Movie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: MoviesResponse.Data.Movie)
}