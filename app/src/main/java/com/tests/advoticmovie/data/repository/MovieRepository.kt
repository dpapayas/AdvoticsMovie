package com.tests.advoticmovie.data.repository

import com.tests.advoticmovie.data.entity.Movie
import com.tests.advoticmovie.data.entity.PopularMovie
import com.tests.advoticmovie.data.response.ResultData
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(): Flow<ResultData<List<Movie>>>
    suspend fun getPopularMovies(): Flow<ResultData<List<PopularMovie>>>

    suspend fun AddAllMovie(movies: List<Movie>)
    suspend fun AddAllPopularMovie(movies: List<PopularMovie>)

    suspend fun getMoviesDB(): Flow<List<Movie>>
    suspend fun getPopularMoviesDB(): Flow<List<PopularMovie>>

}