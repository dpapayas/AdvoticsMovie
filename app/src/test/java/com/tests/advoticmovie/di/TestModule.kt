package com.tests.advoticmovie.di

import com.tests.advoticmovie.data.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Repositories binding to use in tests.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class TestModule {
    @Singleton
    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepository): MovieRepository
}
