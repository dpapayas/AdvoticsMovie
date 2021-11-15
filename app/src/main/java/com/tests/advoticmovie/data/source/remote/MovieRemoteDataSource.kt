package com.tests.advoticmovie.data.source.remote

import android.content.Context
import com.tests.advoticmovie.data.source.local.MovieDao
import com.tests.advoticmovie.data.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun getAllMovie(): Flow<List<Movie>>
}