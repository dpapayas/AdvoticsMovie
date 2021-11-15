package com.tests.advoticmovie.data.source.local

import com.tests.advoticmovie.data.entity.Movie
import com.tests.advoticmovie.data.entity.PopularMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieDataSource{

    override fun getAllMovie(): Flow<List<Movie>> = movieDao.getAllMovie()
    override fun getAllMoviePopular(): Flow<List<PopularMovie>> = movieDao.getAllMoviePopular()

    override suspend fun addAllMovie(movie: List<Movie>) {
        movieDao.insertAll(movie)
    }

    override suspend fun addAllPopularMovie(movie: List<PopularMovie>) {
        movieDao.insertAllPopularMovie(movie)
    }

    override suspend fun addMovie(movie: Movie) {
        movieDao.insert(movie)
    }

    override suspend fun delete() {
        movieDao.deleteAllMovie()
    }
}