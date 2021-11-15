package com.tests.advoticmovie.di

import com.tests.advoticmovie.data.repository.MovieRepository
import com.tests.advoticmovie.data.repository.MovieRepositoryImpl
import com.tests.advoticmovie.data.source.local.MovieDataSource
import com.tests.advoticmovie.data.source.local.MovieDataSourceImpl
import com.tests.advoticmovie.data.source.remote.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesMovieRepository(
        remoteDataSource: MovieRemoteDataSourceImpl,
        ioDispatcher: CoroutineDispatcher,
        movieDataSource: MovieDataSourceImpl
    ): MovieRepository =
        MovieRepositoryImpl(ioDispatcher, movieDataSource, remoteDataSource)


}
