package com.tests.advoticmovie.data.repository

import com.tests.advoticmovie.data.entity.Movie
import com.tests.advoticmovie.data.entity.PopularMovie
import com.tests.advoticmovie.data.response.ResultData
import com.tests.advoticmovie.data.source.local.MovieDataSource
import com.tests.advoticmovie.data.source.remote.MovieRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val movieDataSource: MovieDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getMovies(): Flow<ResultData<List<Movie>>> = flow {
        emit(ResultData.Loading())
        movieRemoteDataSource.getAllMovie().collect {
            emit(ResultData.Success(it))
        }
    }.catch {
        emit(ResultData.Failed(it.message))
    }.flowOn(ioDispatcher)

    override suspend fun getPopularMovies(): Flow<ResultData<List<PopularMovie>>> = flow {
        emit(ResultData.Loading())
        movieRemoteDataSource.getAllPopularMovie().collect {
            emit(ResultData.Success(it))
        }
    }.catch {
        emit(ResultData.Failed(it.message))
    }.flowOn(ioDispatcher)

    override suspend fun AddAllMovie(movies: List<Movie>) {
        movieDataSource.addAllMovie(movies)
    }

    override suspend fun AddAllPopularMovie(movies: List<PopularMovie>) {
        movieDataSource.addAllPopularMovie(movies)
    }

    override suspend fun getMoviesDB() = movieDataSource.getAllMovie().flowOn(ioDispatcher)
    override suspend fun getPopularMoviesDB() = movieDataSource.getAllMoviePopular().flowOn(ioDispatcher)
}