package com.tests.advoticmovie.data.entity

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = [("id")])
data class PopularMovie(
    var page: Int,
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String?,
    val id: Int,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float
) : Parcelable
