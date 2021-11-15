package com.tests.advoticmovie.di

import android.content.Context
import androidx.room.Room
import com.tests.advoticmovie.data.source.local.MovieDao
import com.tests.advoticmovie.data.source.local.MovieDatabase
import com.tests.advoticmovie.network.VolleySingleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun volleySingleTon(@ApplicationContext appContext: Context): VolleySingleton =
        VolleySingleton.getInstance(appContext)

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MovieDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            MovieDatabase::class.java, "moviedb"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideAddressDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }
}
