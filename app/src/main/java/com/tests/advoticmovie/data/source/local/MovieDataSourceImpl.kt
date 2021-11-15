package com.tests.advoticmovie.data.source.local

import com.tests.advoticmovie.data.entity.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieDataSource{

    override fun getAll(): Flow<List<Movie>> = movieDao.getAllMovie()

    override suspend fun addAllMovie(movie: List<Movie>) {
        movieDao.insertAll(movie)
    }

    override suspend fun addMovie(movie: Movie) {
        movieDao.insert(movie)
    }

    override suspend fun delete() {
        movieDao.deleteAllMovie()
    }
}