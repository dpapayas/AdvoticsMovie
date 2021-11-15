package com.tests.advoticmovie.data.source.local

import androidx.room.*
import com.tests.advoticmovie.data.entity.Movie
import com.tests.advoticmovie.data.entity.PopularMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPopularMovie(movies: List<PopularMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getAllMovie() : Flow<List<Movie>>

    @Query("DELETE FROM Movie")
    suspend fun deleteAllMovie()
}