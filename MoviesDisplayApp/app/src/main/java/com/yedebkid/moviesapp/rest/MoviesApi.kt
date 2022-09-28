package com.yedebkid.moviesapp.rest

import com.yedebkid.moviesapp.model.Result
import org.intellij.lang.annotations.Language
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET(POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") apikey: String = API_KEY

    ): Response<List<Result?>?>

    companion object{

        const val BASE_URL = "https://api.themoviedb.org/3/movie"
        private const val API_KEY = "26a7d094a77ed76d84964e9bed095b84"
        private const val POPULAR = "popular"
    }









//    now_palying
//    https://api.themoviedb.org/3/movie/now_playing?api_key=26a7d094a77ed76d84964e9bed095b84
//
//    Popular
//    https://api.themoviedb.org/3/movie/popular?api_key=26a7d094a77ed76d84964e9bed095b84

//    Upcoming
//    https://api.themoviedb.org/3/movie/upcoming?api_key=26a7d094a77ed76d84964e9bed095b84
}