package com.tests.advoticmovie.data.repository

import com.tests.advoticmovie.data.entity.Movie
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

    override suspend fun getFavorites() = movieDataSource.getAll().flowOn(ioDispatcher)
    override suspend fun getPopular(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovies(): Flow<ResultData<List<Movie>>> = flow {
        emit(ResultData.Loading())
        movieRemoteDataSource.getAllMovie().collect {
            emit(ResultData.Success(it))
        }
    }.catch {
        emit(ResultData.Failed(it.message))
    }.flowOn(ioDispatcher)

    override suspend fun AddAllMovie(movies: List<Movie>) {
        movieDataSource.addAllMovie(movies)
    }
}