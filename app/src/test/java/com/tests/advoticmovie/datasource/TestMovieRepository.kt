package com.tests.advoticmovie.datasource

import com.tests.advoticmovie.data.entity.Movie
import com.tests.advoticmovie.data.entity.PopularMovie
import com.tests.advoticmovie.data.repository.MovieRepository
import com.tests.advoticmovie.data.response.ResultData
import com.tests.advoticmovie.data.response.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestMovieRepository @Inject constructor() : MovieRepository {
    var status = Status.LOADING

    override suspend fun getFavorites()= flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPopular(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovies(): Flow<ResultData<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularMovies(): Flow<ResultData<List<PopularMovie>>> {
        TODO("Not yet implemented")
    }

    override suspend fun AddAllMovie(movies: List<Movie>) {
        TODO("Not yet implemented")
    }

    override suspend fun AddAllPopularMovie(movies: List<PopularMovie>) {
        TODO("Not yet implemented")
    }

}