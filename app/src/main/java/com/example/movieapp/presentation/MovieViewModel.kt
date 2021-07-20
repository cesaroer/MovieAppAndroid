package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.movieapp.core.Resource
import com.example.movieapp.repository.MovieRespository
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import java.lang.Exception

class MovieViewModel(private val repo: MovieRespository): ViewModel() {

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {

        emit(Resource.Loading())

        try {
            emit(Resource.Success(Triple(repo.getUpcomingMovies(), repo.getTopRatedMovies(), repo.getPopularMovies())))
        }catch (e: Exception) {

            emit(Resource.Failure(e))
        }
    }


}

class MovieViewModelFactory(private val repo: MovieRespository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRespository::class.java).newInstance(repo)
    }
}