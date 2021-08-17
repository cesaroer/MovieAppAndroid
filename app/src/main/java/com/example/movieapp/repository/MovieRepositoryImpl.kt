package com.example.movieapp.repository

import android.util.Log
import com.example.movieapp.core.InternetCheck
import com.example.movieapp.data.locale.LocaleMovieDataSource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieEntity
import com.example.movieapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocale: LocaleMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {

        return if (InternetCheck.isNetworkAvailable()) {

            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->

                Log.d("RetrievedMoviees", "Pelicula getUpcomingMovies recibida" + movie.toString())

                if (movie.backdrop_path != null) {
                    dataSourceLocale.saveMovie(movie.toMovieEntity("upcoming"))
                }
            }

            dataSourceLocale.getUpcomingMovies()
            //return dataSourceRemote.getUpcomingMovies()
        } else {

            dataSourceLocale.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MovieList {

        return if (InternetCheck.isNetworkAvailable()) {

            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->

                Log.d("RetrievedMoviees", "Pelicula getTopRatedMovies recibida" + movie.toString())

                dataSourceLocale.saveMovie(movie.toMovieEntity("toprated"))
            }

            dataSourceLocale.getTopRatedMovies()
            //return dataSourceRemote.getTopRatedMovies()
        } else {

            dataSourceLocale.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {

        return if (InternetCheck.isNetworkAvailable()) {

            dataSourceRemote.getPopularMovies().results.forEach { movie ->

                Log.d("RetrievedMoviees", "Pelicula getPopularMovies recibida" + movie.toString())

                dataSourceLocale.saveMovie(movie.toMovieEntity("popular"))
            }

            dataSourceLocale.getPopularMovies()
            //return dataSourceRemote.getPopularMovies()
        } else {

            dataSourceLocale.getPopularMovies()
        }
    }
}