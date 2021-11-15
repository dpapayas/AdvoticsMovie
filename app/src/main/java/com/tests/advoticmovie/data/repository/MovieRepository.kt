package com.tests.advoticmovie.data.repository

import com.tests.advoticmovie.data.entity.Movie
import com.tests.advoticmovie.data.response.ResultData
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getFavorites(): Flow<List<Movie>>

    suspend fun getPopular(): Flow<List<Movie>>

    suspend fun getMovies(): Flow<ResultData<List<Movie>>>

    suspend fun AddAllMovie(movies: List<Movie>)

}