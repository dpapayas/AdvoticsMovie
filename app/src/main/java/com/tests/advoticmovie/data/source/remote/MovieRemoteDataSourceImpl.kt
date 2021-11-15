package com.tests.advoticmovie.data.source.remote

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import com.tests.advoticmovie.data.entity.Movie
import com.tests.advoticmovie.data.entity.PopularMovie
import com.tests.advoticmovie.network.API_KEY
import com.tests.advoticmovie.network.URL_MAIN
import com.tests.advoticmovie.network.VolleySingleton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSourceImpl @Inject constructor(private val volleySingleton: VolleySingleton) :
    MovieRemoteDataSource {

    @ExperimentalCoroutinesApi
    override suspend fun getAllMovie(): Flow<List<Movie>> = callbackFlow {
        val movieEntities: MutableList<Movie> = ArrayList()
        val url: String =  URL_MAIN + "list/1?page=1&api_key=" + API_KEY
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val movieList = response.getJSONArray("results")
                for (i in 0 until movieList.length()) {
                    val detailsOfMovieFromApi = movieList[i] as JSONObject
                    val gson = Gson()
                    val entity: Movie =
                        gson.fromJson(detailsOfMovieFromApi.toString(), Movie::class.java)
                    Log.d("EntityValues", "$entity")
                    movieEntities.add(entity)
                }
                offer(movieEntities)
            },
            {
                close(Exception(it.message))
            }
        )

        volleySingleton.addToRequestQueue(jsonObjectRequest)
        awaitClose { jsonObjectRequest.cancel() }
    }

    override suspend fun getAllPopularMovie(): Flow<List<PopularMovie>> = callbackFlow {
        val movieEntities: MutableList<PopularMovie> = ArrayList()
        val url: String =  URL_MAIN + "list/2?page=1&api_key=" + API_KEY
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val movieList = response.getJSONArray("results")
                for (i in 0 until movieList.length()) {
                    val detailsOfMovieFromApi = movieList[i] as JSONObject
                    val gson = Gson()
                    val entity: PopularMovie =
                        gson.fromJson(detailsOfMovieFromApi.toString(), PopularMovie::class.java)
                    Log.d("EntityValues", "$entity")
                    movieEntities.add(entity)
                }
                offer(movieEntities)
            },
            {
                close(Exception(it.message))
            }
        )

        volleySingleton.addToRequestQueue(jsonObjectRequest)
        awaitClose { jsonObjectRequest.cancel() }
    }
}