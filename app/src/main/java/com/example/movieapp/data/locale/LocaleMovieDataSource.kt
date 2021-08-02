package com.example.movieapp.data.locale

import com.example.movieapp.application.AppConstants
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieList

class LocaleMovieDataSource(private val movieDao: MovieDao) {
    suspend fun getUpcomingMovies(): MovieList {

        return movieDao.getAllMovies().toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {

        return movieDao.getAllMovies().toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {

        return movieDao.getAllMovies().toMovieList()
    }

}