package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList

interface MovieRepository {
    // suspend es para crear corrutinas
    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies() : MovieList
}