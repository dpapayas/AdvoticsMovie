package com.tests.advoticmovie.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tests.advoticmovie.data.entity.Movie
import com.tests.advoticmovie.data.entity.PopularMovie

@Database(entities = [Movie::class, PopularMovie::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}