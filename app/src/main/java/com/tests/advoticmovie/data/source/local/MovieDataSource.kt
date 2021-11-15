package com.tests.advoticmovie.data.source.local

import com.tests.advoticmovie.data.entity.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface MovieDataSource {
    fun getAll(): Flow<List<Movie>>
    suspend fun addAllMovie(movie : List<Movie>)
    suspend fun addMovie(movie: Movie)
    suspend fun delete()
}