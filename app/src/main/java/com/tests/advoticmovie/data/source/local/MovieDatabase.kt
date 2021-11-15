package com.tests.advoticmovie.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tests.advoticmovie.data.entity.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}