package com.punyacile.challenge_chapter6.network

import com.punyacile.challenge_chapter6.model.MoviePopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestfulAPI {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<MoviePopular>

    @GET("movie/{id}")
    fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<com.punyacile.challenge_chapter6.model.Result>
}