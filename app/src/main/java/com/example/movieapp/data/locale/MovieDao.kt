package com.example.movieapp.data.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.model.MovieEntity


@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

}