package com.tests.advoticmovie.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tests.advoticmovie.data.entity.Movie
import com.tests.advoticmovie.data.entity.PopularMovie
import com.tests.advoticmovie.data.repository.MovieRepository
import com.tests.advoticmovie.data.response.ResultData
import com.tests.advoticmovie.data.source.local.MovieDao
import com.tests.advoticmovie.data.source.local.MovieDatabase
import com.tests.advoticmovie.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDao: MovieDao
) : BaseViewModel() {

    private val _getMovies = MutableLiveData<ResultData<List<Movie>>>()
    val getMovies: MutableLiveData<ResultData<List<Movie>>>
        get() = _getMovies

    private val _getPopularMovies = MutableLiveData<ResultData<List<PopularMovie>>>()
    val getPopularMovies: MutableLiveData<ResultData<List<PopularMovie>>>
        get() = _getPopularMovies

    fun getAllMovies(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMovies().collect {
                if (it.toData() != null) {
                    Log.d("test", "test")
                    insertMovieToRoomDB(it)
                    _getMovies.postValue(it)
                } else {
                    Log.d("test", "test")
                    getMovieFromRoomDB(context)
                }
            }

        }
    }

    fun getAllPopularMovies(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getPopularMovies().collect {
                if (it.toData() != null) {
                    Log.d("test", "test")
                    insertMoviePopularToRoomDB(it)
                    _getPopularMovies.postValue(it)
                } else {
                    Log.d("test", "test")
                    getMoviePopularFromRoomDB(context)
                }
            }

        }
    }

    private fun insertMovieToRoomDB(it: ResultData<List<Movie>>) {
        viewModelScope.launch(Dispatchers.IO) {
            _getMovies.postValue(it)
            movieRepository.AddAllMovie(it.toData()!!)
        }
    }

    private fun insertMoviePopularToRoomDB(it: ResultData<List<PopularMovie>>) {
        viewModelScope.launch(Dispatchers.IO) {
            _getPopularMovies.postValue(it)
            movieRepository.AddAllPopularMovie(it.toData()!!)
        }
    }

    private fun getMovieFromRoomDB(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMoviesDB()
        }
    }

    private fun getMoviePopularFromRoomDB(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getPopularMoviesDB()
        }
    }
}