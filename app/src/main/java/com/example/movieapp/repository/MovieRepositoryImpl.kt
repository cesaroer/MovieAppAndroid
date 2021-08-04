package com.example.movieapp.repository

import android.util.Log
import com.example.movieapp.data.locale.LocaleMovieDataSource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieEntity
import com.example.movieapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocale: LocaleMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        dataSourceRemote.getUpcomingMovies().results.forEach { movie ->

            Log.d("RetrievedMoviees", "Pelicula getUpcomingMovies recibida" + movie.toString())

            if(movie.backdrop_path != null){
                dataSourceLocale.saveMovie(movie.toMovieEntity("upcoming"))
            }
        }


        return dataSourceLocale.getUpcomingMovies()
        //return dataSourceRemote.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): MovieList {
        dataSourceRemote.getTopRatedMovies().results.forEach { movie ->

            Log.d("RetrievedMoviees", "Pelicula getTopRatedMovies recibida" + movie.toString())

            dataSourceLocale.saveMovie(movie.toMovieEntity("toprated"))
        }

        return dataSourceLocale.getTopRatedMovies()
        //return dataSourceRemote.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): MovieList {
        dataSourceRemote.getPopularMovies().results.forEach { movie ->

            Log.d("RetrievedMoviees", "Pelicula getPopularMovies recibida" + movie.toString())

            dataSourceLocale.saveMovie(movie.toMovieEntity("popular"))
        }

        return dataSourceLocale.getPopularMovies()
        //return dataSourceRemote.getPopularMovies()
    }
}