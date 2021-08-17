package com.example.movieapp.data.locale

import android.util.Log
import com.example.movieapp.application.AppConstants
import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieList

class LocaleMovieDataSource(private val movieDao: MovieDao) {
    suspend fun getUpcomingMovies(): MovieList {

        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {

        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {

        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity){

        try {

            Log.d("MovieSaved" , "Guardando Película ${movie.title} con m_t : ${movie.movie_type}")
            movieDao.saveMovie(movie)
        }catch (e : Exception){

            Log.d("MovieSavedError" , "No se pudo guardar Película ${movie.title} con m_t : ${movie.movie_type}")
            return
        }
    }


}